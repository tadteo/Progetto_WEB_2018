/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.UtenteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author domenico
 */
public class RestoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/restorepage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
                
        PrintWriter out = response.getWriter();
        out.println("Ciao");
        
        String email = request.getParameter("email");
        
        UtenteDAO utd = DAOFactory.getUtenteDAO();
        try {
            if((email != null)&&(utd.isUsed(email))){
                session.removeAttribute("restoreErrorMessage");
                sendPassword(utd.getUtenteByEmail(email).getPassword());
                request.getRequestDispatcher("/JSP/restoresuccesspage.jsp").forward(request, response);
            }else{
                session.setAttribute("restoreErrorMessage", "Errore, la mail specificata non risulta iscritta");
                response.sendRedirect(request.getContextPath() + "/restore.do");
            }
        } catch (SQLException ex) {
            System.err.println("Errore, impossibile ottenere info sull'utente");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Errore, impossibile inviare la password");
            ex.printStackTrace();
        }
    }
    
    private boolean sendPassword(String password) throws Exception{
        

        String host = "192.168.10.205";
        String from = "test@localhost";
        String to = "dodostefani@gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set the RFC 822 "From" header field using the 
        // value of the InternetAddress.getLocalAddress method.
        message.setFrom(new InternetAddress(from));

        // Add the given addresses to the specified recipient type.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));


        // Set the "Subject" header field.
        message.setSubject("hi..!");

        // Sets the given String as this part's content,
        // with a MIME type of "text/plain".
        message.setText("Hi ......");

        // Send message
        Transport.send(message);

        System.out.println("Message Send.....");
        
        
        return true;
    }
}

