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
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;



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
                out.println("Ready to send message...");
                session.removeAttribute("restoreErrorMessage");
                sendPassword(utd.getUtenteByEmail(email).getPassword(),email);
                out.println("Message Send.....");
                
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
    
    public static final String HOST_NAME = "smtp.gmail.com";
    public static final int PORT = 465;
    public static final String TEXT_PLAIN = "text/plain";
    
    
    public void sendPassword(String userPassword, String recipient) throws IOException, EmailException {

        final String cinemaUsername = "cinema.universe.42@gmail.com";
        final String cinemaPassword = "Univers3";
        
        final String recipientEmailAddress = recipient;//"dodostefani@gmail.com";

        HtmlEmail email = new HtmlEmail();
        email.setHostName(HOST_NAME);
        email.setSmtpPort(PORT);
        email.setSSLOnConnect(true);

        email.setAuthentication(cinemaUsername, cinemaPassword);

        email.setSubject("Recupero Password - Cinema Universe");
        email.setFrom(cinemaUsername, "Cinema Universe", String.valueOf(StandardCharsets.UTF_8));
        email.addTo(recipientEmailAddress);
        email.setHtmlMsg("<h3>Recupero Password - Cinema Universe<br></h3>"
                + "Utente: " + recipient + "<br>"
                + "La tua password è: '" + userPassword + "'<br>");
        email.send();
    }
    
}

