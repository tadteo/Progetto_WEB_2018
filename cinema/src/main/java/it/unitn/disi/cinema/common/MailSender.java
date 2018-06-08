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
package it.unitn.disi.cinema.common;

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

    /**
     *
     * @param recipient
     * @param file
     * @throws IOException
     * @throws EmailException
     *
     * The function to send the mail of confermation with the pdf and the
     * tickets
     */
    public static void sendTickets(String recipient, String file) throws IOException, EmailException {
        final String cinemaUsername = "cinema.universe.42@gmail.com";
        final String cinemaPassword = "Univers3";

        final String recipientEmailAddress = recipient;

        //Creazione dell'allegato
        EmailAttachment biglietti = new EmailAttachment();
        biglietti.setPath(file);
        biglietti.setDisposition(EmailAttachment.ATTACHMENT);
        biglietti.setDescription("Tickets");
        biglietti.setName("Biglietti.pdf");

        //Creazione della mail e invio
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(HOST_NAME);
        email.setSmtpPort(PORT);
        email.addTo(recipientEmailAddress);
        email.setFrom(cinemaUsername, "Cinema Universe", String.valueOf(StandardCharsets.UTF_8));
        email.setSubject("Biglietti - Cinema Universe");
        email.setMsg("Biglietti - Cinema Universe\n\n" + "La prenotazione é avvenuta con successo in allegato puo' trovare i QRCode relativi ai biglietti");
        email.attach(biglietti);
        email.setSSLOnConnect(true);
        email.setAuthentication(cinemaUsername, cinemaPassword);
        email.send();
    }

}
