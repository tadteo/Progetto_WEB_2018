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

import it.unitn.disi.cinema.dataaccess.Beans.Film;
import it.unitn.disi.cinema.dataaccess.Beans.Posto;
import it.unitn.disi.cinema.dataaccess.Beans.Prenotazione;
import it.unitn.disi.cinema.dataaccess.Beans.Sala;
import it.unitn.disi.cinema.dataaccess.Beans.Spettacolo;
import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.FilmDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PostoDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PrenotazioneDAO;
import it.unitn.disi.cinema.dataaccess.DAO.SalaDAO;
import it.unitn.disi.cinema.dataaccess.DAO.SpettacoloDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author domenico
 */
public class ReservationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final boolean DEBUG = false;
        final boolean DELIMITERS = false;

        PrintWriter out;
        if (DEBUG) {
            out = response.getWriter();
        }

        SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();
        SalaDAO sld = DAOFactory.getSalaDAO();
        PostoDAO psd = DAOFactory.getPostoDAO();
        FilmDAO fld = DAOFactory.getFilmDAO();
        PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();

        try {
            sld.getAll();

            String idReq_str = request.getPathInfo(); //QUESTO PRENDE L'ULTIMO PARAMETRO DELL'URL
            if (idReq_str == null) {
                request.setAttribute("errorcode", "404");
                request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            }
            idReq_str = idReq_str.substring(1);
            if (idReq_str == null || idReq_str.equals("") || idReq_str.equals("prenotaspettacolo")) {
                request.setAttribute("errorcode", "404");
                request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            }

            String[] parts_str = idReq_str.split("-");
            if (parts_str.length < 1) {
                request.setAttribute("errorcode", "404");
                request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            } else {
                Integer idReq = null;
                try {
                    if (DEBUG) {
                        out.println("trying to decode \"" + parts_str[0] + "\"");
                    }
                    idReq = Integer.parseInt(parts_str[0]);
                    if (DEBUG) {
                        out.println("decoded int(" + idReq + ")");
                    }
                } catch (NumberFormatException nfe) {
                    request.setAttribute("errorcode", "401");
                    request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
                    System.err.println("ERRORE! impossibile fare il parse ad integer di \"" + parts_str[0] + "\"");
                    nfe.printStackTrace();
                }

                Spettacolo spettacolo = spd.getSpettacoloById(idReq);

                if (spettacolo != null) {
                    Sala sala = sld.getSalaById(spettacolo.getSalaId());
                    List<Posto> posti = psd.getPostoBySalaId(sala.getId());
                    Film film = fld.getFilmById(spettacolo.getFilmId());
                    List<Prenotazione> prenotazioni = prd.getBySpettacolo(spettacolo.getId());

                    if (DEBUG) {
                        out.println("ReservationPage");
                        out.println("Film: " + film.getTitolo());
                        out.println("Spettacolo: " + spettacolo.getId());
                        out.println("Sala: " + sala.getDescrizione() + " " + sala.getId());
                        out.println("Posti: " + sld.getPostiCount(sala.getId()));
                        out.println("Posti Disponibili: " + (sld.getPostiCount(sala.getId()) - spd.getAvailablePostiCount(spettacolo.getId())));
                    }

                    ArrayList<String> mappa = new ArrayList<>();
                    //a posto disponibile
                    //b posto prenotato
                    //_ posto inesistente

                    final char letterForAvailable = 'a';
                    final char letterForReserved = 'b';
                    final char letterForNotExists = '_';

                    int currentRiga = 1;
                    String currentStringa = new String();
                    if (DELIMITERS) {
                        currentStringa = currentStringa + "'";  //aggiunge delimitatore
                    }
                    for (int j = 0; j < posti.size(); j++) {
                        if (posti.get(j).getRiga() > currentRiga) {
                            if (DELIMITERS) {
                                currentStringa = currentStringa + "'";  //aggiunge delimitatore
                            }
                            mappa.add(currentStringa);
                            currentStringa = new String();
                            if (DELIMITERS) {
                                currentStringa = currentStringa + "'";  //aggiunge delimitatore
                            }
                            currentRiga++;
                        }

                        char seatChar = '0';
                        if (posti.get(j).getEsiste() == false) {
                            seatChar = letterForNotExists;
                        } else if (psd.isReserved(spettacolo.getId(), posti.get(j).getId())) {
                            //prenotato
                            seatChar = letterForReserved;
                        } else {
                            //libero
                            seatChar = letterForAvailable;
                        }
                        currentStringa = currentStringa + seatChar;
                    }

                    if (DELIMITERS) {
                        currentStringa = currentStringa + "'";  //aggiunge delimitatore
                    }
                    mappa.add(currentStringa);

                    /*--Reserved String--*/
                    String reservedString = "";
                    List<Posto> reserved = spd.getReservedPosti(spettacolo.getId());
                    Posto p;
                    for (int i = 0; i < reserved.size(); i++) {
                        p = reserved.get(i);
                        reservedString += (p.getRiga() + "_" + p.getPoltrona());
                        if (i != reserved.size() - 1) {
                            reservedString += ",";
                        }
                    }
                    if (DEBUG) {
                        out.println("\nPosti Non disponibili: \n" + reservedString);
                    }

                    if (DEBUG) {
                        out.println("\n\nArraylist:");
                        for (String s : mappa) {
                            out.println(s);
                        }
                        out.println("");
                    }

                    if (!DEBUG) {
                        request.setAttribute("spettacolo", spettacolo);
                        request.setAttribute("sala", sala);
                        request.setAttribute("film", film);
                        request.setAttribute("posti", posti);
                        request.setAttribute("prenotazioni", prenotazioni);
                        request.setAttribute("mappa", mappa);
                        request.setAttribute("reserved", reservedString);
                        request.getRequestDispatcher("/JSP/reservationpage.jsp").forward(request, response);
                    }

                } else {
                    //SORRY PAGE NOT AVAILABLE
                }
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
