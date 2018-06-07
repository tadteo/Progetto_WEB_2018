package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.dataaccess.Beans.PackagePosto;
import it.unitn.disi.cinema.dataaccess.Beans.Posto;
import it.unitn.disi.cinema.dataaccess.Beans.Prenotazione;
import it.unitn.disi.cinema.dataaccess.Beans.Sala;
import it.unitn.disi.cinema.dataaccess.Beans.Spettacolo;

import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.SpettacoloDAO;
import it.unitn.disi.cinema.dataaccess.DAO.SalaDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PrenotazioneDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PostoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author domenico
 */
@WebServlet(name = "AdminSituazioniServlet", urlPatterns = {"/situazione/*"})
public class AdminSituazioniServlet extends HttpServlet {

    private boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }  
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();
        SalaDAO sld = DAOFactory.getSalaDAO();
        PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();
        PostoDAO psd = DAOFactory.getPostoDAO();

        
        response.setContentType("text/html;charset=UTF-8");
        try {

            String idReq_str = request.getPathInfo(); //QUESTO PRENDE L'ULTIMO PARAMETRO DELL'URL
            if (idReq_str == null) {
                request.setAttribute("errorcode", "404");
                request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            }
            idReq_str = idReq_str.substring(1);
            if (idReq_str.equals("") || idReq_str.equals("film")) {
                request.setAttribute("errorcode", "404");
                request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            }

            if (isNumeric(idReq_str)) {
                //good

                Integer idReq = null;
                try {
                    idReq = Integer.parseInt(idReq_str);
                } catch (NumberFormatException nfe) {
                    request.setAttribute("errorcode", "400");
                    request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
                }

                Spettacolo spettacolo = spd.getSpettacoloById(idReq);

                if (spettacolo != null) {

                    Sala sala = sld.getSalaById(spettacolo.getSalaId());
                    List<Prenotazione> prenotazioni = prd.getBySpettacolo(spettacolo.getId());
                    
                    List<Posto> posti = psd.getPostoBySalaId(sala.getId());                    
                    
                    List<PackagePosto> tosend = new ArrayList<>();
                    
                    PackagePosto temp;
                    for(Posto posto : posti){
                        temp = new PackagePosto(posto, null);
                        for(Prenotazione prenotazione : prenotazioni){
                            if(prenotazione.getPostoId().equals(posto.getId())){
                                temp.setPrenotazione(prenotazione);
                            }
                        }
                        
                        tosend.add(temp);
                    }
                    
                    request.setAttribute("spettacolo", spettacolo);
                    request.setAttribute("sala", sala);
                    request.setAttribute("packageposti", tosend);
                    request.getRequestDispatcher("/JSP/adminsituazionespecifico.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorcode", "410");
                    request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
                }
            }          
        } catch (SQLException ex) {
            request.setAttribute("errorcode", "410");
            request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}