/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
