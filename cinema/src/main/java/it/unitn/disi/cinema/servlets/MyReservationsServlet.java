/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.dataaccess.Beans.Film;
import it.unitn.disi.cinema.dataaccess.Beans.Genere;
import it.unitn.disi.cinema.dataaccess.Beans.PackagePrenotazione;
import it.unitn.disi.cinema.dataaccess.Beans.Posto;
import it.unitn.disi.cinema.dataaccess.Beans.Prenotazione;
import it.unitn.disi.cinema.dataaccess.Beans.Spettacolo;
import it.unitn.disi.cinema.dataaccess.Beans.Utente;
import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.FilmDAO;
import it.unitn.disi.cinema.dataaccess.DAO.GenereDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PostoDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PrenotazioneDAO;
import it.unitn.disi.cinema.dataaccess.DAO.SpettacoloDAO;
import it.unitn.disi.cinema.dataaccess.DAO.UtenteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author domenico
 */

public class MyReservationsServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        UtenteDAO utd = DAOFactory.getUtenteDAO();
        PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();
        SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();
        FilmDAO fld = DAOFactory.getFilmDAO();
        PostoDAO psd = DAOFactory.getPostoDAO();

        try {

            String idReq_str = request.getPathInfo(); //QUESTO PRENDE L'ULTIMO PARAMETRO DELL'URL
            if (idReq_str == null) {
                request.setAttribute("errorcode", "404");
                request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            }
            idReq_str = idReq_str.substring(1);
            if (idReq_str.equals("")) {
                request.setAttribute("errorcode", "404");
                request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            }
            
            System.out.println("idreq: " + idReq_str.trim());
            System.out.println("session: " + session.getAttribute("email"));

            
            if(idReq_str.trim().equals(session.getAttribute("email"))){
                Utente utente = utd.getUtenteByEmail(idReq_str);

                if (utente != null) {

                    List<PackagePrenotazione> packprenotazioni = new ArrayList<>();
                    
                    List<Prenotazione> prenotazioni = prd.getByUtenteReverseOrder(utente.getId());
                    
                    Posto posto;
                    Spettacolo spett;
                    Film film;
                    String postoString;
                    
                    for(Prenotazione prenotazione : prenotazioni){
                        posto = psd.getPostoById(prenotazione.getPostoId());
                        if(posto != null){
                            spett = spd.getSpettacoloById(prenotazione.getSpettacoloId());
                            if(spett != null){
                                film = fld.getFilmById(spett.getFilmId());
                                if(film != null){
                                    postoString = "<b>Riga:</b> " + posto.getRiga() + " <b>Poltrona:</b> " + posto.getPoltrona() ;
                                    
                                    packprenotazioni.add(new PackagePrenotazione(prenotazione.getId(),
                                            film.getTitolo(),
                                            prenotazione.getDataOraOperazione(),
                                            spett.getDataOra(),
                                            postoString));
                                }
                            }
                        }
                    }


                    request.setAttribute("packprenotazioni", packprenotazioni);
                    request.getRequestDispatcher("/JSP/myreservations.jsp").forward(request, response);

                }else{
                    request.setAttribute("errorcode", "401");
                    request.setAttribute("mmessage", "Questa pagina è relativa ad un altro utente, non sei autorizzato.");
                    request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorcode", "401");
                request.setAttribute("mmessage", "Questa pagina è relativa ad un altro utente, non sei autorizzato.");
                request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            request.setAttribute("errorcode", "410");
            request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
        }

    }
        
        
    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
