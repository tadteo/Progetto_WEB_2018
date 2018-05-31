/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.common;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author domenico
 */
public class MailSender {
    public static final String HOST_NAME = "smtp.gmail.com";
    public static final int PORT = 465;
    public static final String TEXT_PLAIN = "text/plain";
    
    
    public static void sendPassword(String userPassword, String recipient) throws IOException, EmailException {

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
    
    public static void sendTickets(String recipient,String file) throws IOException, EmailException {
        final String cinemaUsername = "cinema.universe.42@gmail.com";
        final String cinemaPassword = "Univers3";
        
        final String recipientEmailAddress = recipient;//"dodostefani@gmail.com";
        
        EmailAttachment biglietti = new EmailAttachment();
        biglietti.setPath(file);
        biglietti.setDisposition(EmailAttachment.ATTACHMENT);
        biglietti.setDescription("Tickets");
        biglietti.setName("Biglietti.pdf");
        
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(HOST_NAME);
        email.setSmtpPort(PORT);

        email.addTo(recipientEmailAddress);
        email.setFrom(cinemaUsername, "Cinema Universe", String.valueOf(StandardCharsets.UTF_8));
        email.setSubject("Biglietti - Cinema Universe");

        email.setMsg("Biglietti - Cinema Universe\n\n"
                    + "La prenotazione é avvenuta con successo in allegato puo' trovare i QRCode relativi ai biglietti");
        email.attach(biglietti);
        email.setSSLOnConnect(true);

        email.setAuthentication(cinemaUsername, cinemaPassword);
        email.send();
    }

}
