/*
 * Cinema Universe - Reservation System
 * Copyright (C) 2018 Domenico Stefani, Ivan Martini, Matteo Tadiello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See <http://www.gnu.org/licenses/>.
 */

package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.common.PrettyPrintFilmGenere;
import it.unitn.disi.cinema.dataaccess.Beans.*;
import it.unitn.disi.cinema.dataaccess.DAO.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author domenico - matteo
 */
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        /*Accesso db*/    //Perchè questa soluzione non funziona? non posso leggere da homepage.jsp gli attributi di request
        FilmDAO fld = DAOFactory.getFilmDAO();
        GenereDAO gnd = DAOFactory.getGenereDAO();
        PrezzoDAO prd = DAOFactory.getPrezzoDAO();
        try {
            List<Prezzo> prezzi = prd.getAll();

            List<PrettyPrintFilmGenere> filmspp = new ArrayList<>();
            for (Film film : fld.getAll()) {
                for (Genere genere : gnd.getAll()) {
                    if (genere.getId() == film.getGenereId()) {
                        filmspp.add(new PrettyPrintFilmGenere(film, genere));
                    }
                }
            }

            request.setAttribute("filmspp", filmspp);
            request.setAttribute("prezzi", prezzi);
            request.setAttribute("pageCurrent", "homepage");
            request.getRequestDispatcher("JSP/homepage.jsp").forward(request, response);
        } catch (SQLException ex) {
            System.out.println("Errore, impossibile ottenere la lista dei film");
            ex.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pageRequested = request.getParameter("pageRequested");

        if (pageRequested.equals("adminsituazione")) {
    
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
                request.setAttribute("pageCurrent","adminsituazione");
                request.getRequestDispatcher("JSP/adminsituazione.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Errore, inpossibile ottenere la pagina degli admin");
                ex.printStackTrace();
            }      
        } else if (pageRequested.equals("adminincassi")) {
            
            SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();
            FilmDAO fld = DAOFactory.getFilmDAO();
            PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();
            PrezzoDAO prz = DAOFactory.getPrezzoDAO();

            try {               
                List<Prezzo> prezzo = prz.getAll();
                
                long millis = System.currentTimeMillis();
                Timestamp now = new Timestamp(millis);
                
                Calendar cal1 = Calendar.getInstance();
                cal1.setTimeInMillis(now.getTime());
                int today = cal1.get(Calendar.DAY_OF_YEAR);

                // incassi
                for (Film filmc: fld.getAll()) {
                    double tot = 0.0;
                    double totGiorno = 0.0;
                    
                    for (Spettacolo spettc: spd.getByFilm(filmc.getId())) {
                        for (Prenotazione prenc: prd.getBySpettacolo(spettc.getId())) {
                            tot += prz.getPrezzoById(prenc.getPrezzoId()).getPrezzo();
                            
                            Calendar cal2 = Calendar.getInstance();
                            cal2.setTimeInMillis(prenc.getDataOraOperazione().getTime());
                            int operation = cal2.get(Calendar.DAY_OF_YEAR);

                            if (operation == today) {
                                totGiorno += prz.getPrezzoById(prenc.getPrezzoId()).getPrezzo();
                            }
                        }
                    }
                    
                    request.setAttribute("tot" + filmc.getId(), tot);
                    request.setAttribute("totGiorno" + filmc.getId(), totGiorno);
                }

                request.setAttribute("film", fld.getAll());
                request.setAttribute("pageCurrent","adminincassi");
                request.getRequestDispatcher("JSP/adminincassi.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Errore, inpossibile ottenere la pagina degli admin");
                ex.printStackTrace();
            }      
        } else if (pageRequested.equals("adminclientitop")) {

            PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();
            PrezzoDAO prz = DAOFactory.getPrezzoDAO();
            UtenteDAO utd = DAOFactory.getUtenteDAO();

            try {    
                List<Prezzo> prezzo = prz.getAll();

                //utenti
                for (Utente utentec: utd.getAll()) {
                    double tot = 0.0;
                    int totPren = 0;

                    for (Prenotazione prenc: prd.getByUtente(utentec.getId())) {
                        tot += prz.getPrezzoById(prenc.getPrezzoId()).getPrezzo();
                        totPren += 1;
                    }

                    request.setAttribute("tot" + utentec.getId(), tot);
                    request.setAttribute("totPren" + utentec.getId(), totPren);
                }

                request.setAttribute("utenti", utd.getAll());     
                request.setAttribute("pageCurrent","adminclientitop");
                request.getRequestDispatcher("JSP/adminclientitop.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Errore, inpossibile ottenere la pagina degli admin");
                ex.printStackTrace();
            }      
        }
    }
}

