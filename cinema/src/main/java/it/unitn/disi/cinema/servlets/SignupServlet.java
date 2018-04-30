/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.dataaccess.Beans.Utente;
import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.RuoloDAO;
import it.unitn.disi.cinema.dataaccess.DAO.UtenteDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/signuppage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        if(session.getAttribute("email") != null)        
            response.setStatus(500);
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");

        
        UtenteDAO utd = DAOFactory.getUtenteDAO();
        RuoloDAO rud = DAOFactory.getRuoloDAO();
        try {
            if(utd.isUsed(email)){
                session.setAttribute("signupErrorMessage", "L'indirizzo mail è già utilizzato");
                response.sendRedirect(request.getContextPath() + "/signup.do");
            }else{
                if(!password.equals(passwordConfirm)){
                    session.setAttribute("signupErrorMessage", "Le password non coicidono");
                    response.sendRedirect(request.getContextPath() + "/signup.do");
                }else{
                    Utente currentUser = new Utente(null, 1 , email, password, new Float(0));
                    utd.addUtente(currentUser);
                    
                    session.setAttribute("email", email);
                    session.setAttribute("ruolo", rud.getRuoloById(currentUser.getRuoloId()).getRuolo());
                    session.removeAttribute("signupErrorMessage");
                    request.getRequestDispatcher("/JSP/successpage.jsp").forward(request, response);
                    
                }
            }
        } catch (SQLException ex) {
            System.err.println("ERRORE ACCESSO DATABASE");
            ex.printStackTrace();
        }
        
        
        
    }
}
