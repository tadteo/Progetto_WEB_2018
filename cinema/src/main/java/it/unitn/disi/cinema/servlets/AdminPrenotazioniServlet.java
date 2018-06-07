package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.dataaccess.Beans.Posto;
import it.unitn.disi.cinema.dataaccess.Beans.Prenotazione;

import it.unitn.disi.cinema.dataaccess.Beans.Spettacolo;
import it.unitn.disi.cinema.dataaccess.Beans.Utente;

import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.FilmDAO;
import it.unitn.disi.cinema.dataaccess.DAO.SpettacoloDAO;
import it.unitn.disi.cinema.dataaccess.DAO.SalaDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PrenotazioneDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PostoDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PrezzoDAO;
import it.unitn.disi.cinema.dataaccess.DAO.UtenteDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ivan
 */
@WebServlet(name = "AdminPrenotazioniServlet", urlPatterns = {"/prenotazione/*"})
public class AdminPrenotazioniServlet extends HttpServlet {

    private boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }  
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String idReq_str = request.getPathInfo(); //QUESTO PRENDE L'ULTIMO PARAMETRO DELL'URL
        if (idReq_str == null) {
            request.setAttribute("errorcode", "404");
            request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
        }
        idReq_str = idReq_str.substring(1);

        if (idReq_str.equals("")) {

            SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();
            PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();
            SalaDAO sld = DAOFactory.getSalaDAO();
            FilmDAO fld = DAOFactory.getFilmDAO();
            PrezzoDAO prz = DAOFactory.getPrezzoDAO();

            try {               

                long millis = System.currentTimeMillis();
                Timestamp now = new Timestamp(millis);

                for (Spettacolo spettc : spd.getAfter(now)) {
                    int postiOccupati = sld.getAvailablePostiCount(spettc.getId());
                    int postiTotali = sld.getPostiCount(spettc.getSalaId());

                    request.setAttribute("postiOccupati"+spettc.getId(),postiOccupati);
                    request.setAttribute("postiTotali"+spettc.getId(),postiTotali);
                    request.setAttribute("film"+spettc.getId(),fld.getFilmById(spettc.getFilmId()).getTitolo());
                
                    int tot = 0;
                    for (Prenotazione prenc: prd.getBySpettacolo(spettc.getId())) {
                        tot += prz.getPrezzoById(prenc.getPrezzoId()).getPrezzo();
                    }

                    request.setAttribute("incasso"+spettc.getId(),tot);
                }

                request.setAttribute("spettacolo",spd.getAfter(now));
                request.setAttribute("pageCurrent","adminprenotazioni");
                request.getRequestDispatcher("/JSP/adminprenotazioni.jsp").forward(request, response);
            } catch (SQLException ex) {
            request.setAttribute("errorcode", "405");
            request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            }      
        }

        if (isNumeric(idReq_str)) {
            //good, target                
            Integer idReq = null;
            
            try {
                idReq = Integer.parseInt(idReq_str);
            } catch (NumberFormatException nfe) {
                request.setAttribute("errorcode", "400");
                request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            }

            SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();
            FilmDAO fld = DAOFactory.getFilmDAO();
            SalaDAO sld = DAOFactory.getSalaDAO();
            PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();
            UtenteDAO utd = DAOFactory.getUtenteDAO();
            PostoDAO pst = DAOFactory.getPostoDAO();

            try {
                request.setAttribute("spettacolo",spd.getSpettacoloById(idReq));
                request.setAttribute("film",fld.getFilmById(spd.getSpettacoloById(idReq).getFilmId()));
                request.setAttribute("sala",sld.getSalaById(spd.getSpettacoloById(idReq).getSalaId()));

                for (Prenotazione pren: prd.getBySpettacolo(idReq)) {
                    Utente ute = utd.getUtenteById(pren.getUtenteId());
                    request.setAttribute("utenteID"+pren.getId(),ute.getId());
                    request.setAttribute("utenteEm"+pren.getId(),ute.getEmail());
                    
                    Posto pos = pst.getPostoById(pren.getPostoId());
                    request.setAttribute("posto"+pren.getId(),"Fila: " + pos.getRiga().toString() + ", Poltrona:" + pos.getPoltrona().toString());
                }

                request.setAttribute("prenotazione",prd.getBySpettacolo(idReq));
                request.getRequestDispatcher("/JSP/adminrimozione.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("errorcode", "410");
                request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            }


        }          
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}