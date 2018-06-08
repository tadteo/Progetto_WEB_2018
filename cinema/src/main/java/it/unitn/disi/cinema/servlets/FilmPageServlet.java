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
import it.unitn.disi.cinema.dataaccess.Beans.Genere;
import it.unitn.disi.cinema.dataaccess.Beans.Spettacolo;
import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.FilmDAO;
import it.unitn.disi.cinema.dataaccess.DAO.GenereDAO;
import it.unitn.disi.cinema.dataaccess.DAO.SpettacoloDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author domenico
 */
public class FilmPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FilmDAO fld = DAOFactory.getFilmDAO();
        GenereDAO gnd = DAOFactory.getGenereDAO();
        SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();

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

            String[] parts_str = idReq_str.split("-");
            if (parts_str.length < 1) {
                request.setAttribute("errorcode", "404");
                request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
            } else {
                Integer idReq = null;
                try {
                    idReq = Integer.parseInt(parts_str[0]);
                } catch (NumberFormatException nfe) {
                    request.setAttribute("errorcode", "400");
                    request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
                }

                Film filmRequested = fld.getFilmById(idReq);

                if (filmRequested != null) {

                    Genere genere = gnd.getGenereById(filmRequested.getGenereId());

                    long millis = System.currentTimeMillis();
                    Timestamp now = new Timestamp(millis);

                    List<Spettacolo> spettacoliDisponibili = spd.getByFIlmAfter(filmRequested.getId(), now);

                    request.setAttribute("film", filmRequested);
                    request.setAttribute("genere", genere);
                    request.setAttribute("spettacoli", spettacoliDisponibili);
                    request.setAttribute("calltime", now);/**/

                    request.getRequestDispatcher("/JSP/filmpage.jsp").forward(request, response);
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
