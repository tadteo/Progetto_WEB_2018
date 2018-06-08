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

import it.unitn.disi.cinema.dataaccess.Beans.*;
import it.unitn.disi.cinema.dataaccess.DAO.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author domenico
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if(session.getAttribute("email") == null)
            request.getRequestDispatcher("/JSP/loginpage.jsp").forward(request, response);
        else
            response.setStatus(500);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("email") != null) {
            response.setStatus(500);//Commento//Dovrebbe bastare questo per non avere problemi con utenti già loggati che accedono in post alla servlet
        }
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UtenteDAO utd = DAOFactory.getUtenteDAO();
        RuoloDAO rud = DAOFactory.getRuoloDAO();
        try {
            if (utd.isUsed(email)) {
                Utente currentUser = utd.getUtenteByEmail(email);
                
                /**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**/
                /**//**//**//**//* Qui ci va la creazione di un hash per le password  *//**//**//**//**//**//**/
                /**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**/
                

                if (password.equals(currentUser.getPassword())) {
                    session.setAttribute("email", email);
                    session.setAttribute("ruolo", rud.getRuoloById(currentUser.getRuoloId()).getRuolo());
                    session.setAttribute("credito", currentUser.getCredito());
                    session.removeAttribute("errorMessage");
                    
                    Object filterSavedRequestPage = session.getAttribute("filterSavedRequest");
                    if(filterSavedRequestPage != null){
                        try{
                            String fsrStr = (String)filterSavedRequestPage;
                            session.removeAttribute("filterSavedRequest");
                            
                            response.sendRedirect(fsrStr);
                        }catch(Exception e){
                            System.out.println("ERROR with filterSavedRequest in LoginServlet!!!!");
                        }
                    }else{
                        response.sendRedirect(request.getContextPath() + "/");
                    }
                }else{
                    session.setAttribute("errorMessage", "La combinazione email/password è sbagliata");
                    response.sendRedirect(request.getContextPath() + "/login.do");
                }
            } else {
                session.setAttribute("errorMessage", "L'indirizzo mail non è corretto");
                response.sendRedirect(request.getContextPath() + "/login.do");
            }
        } catch (SQLException ex) {
            System.err.println("ERRORE ACCESSO DATABASE");
            ex.printStackTrace();
        }
    }

}
