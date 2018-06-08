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

import it.unitn.disi.cinema.common.MailSender;
import it.unitn.disi.cinema.common.PDFGenerator;
import it.unitn.disi.cinema.common.QRGenerator;
import it.unitn.disi.cinema.dataaccess.Beans.Posto;
import it.unitn.disi.cinema.dataaccess.Beans.Prenotazione;
import it.unitn.disi.cinema.dataaccess.Beans.Prezzo;
import it.unitn.disi.cinema.dataaccess.Beans.Spettacolo;
import it.unitn.disi.cinema.dataaccess.Beans.Utente;
import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.PostoDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PrenotazioneDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PrezzoDAO;
import it.unitn.disi.cinema.dataaccess.DAO.SpettacoloDAO;
import it.unitn.disi.cinema.dataaccess.DAO.UtenteDAO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author domenico
 */
public class ConfirmationPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/JSP/confirmationpage.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //DAOs
        UtenteDAO usd = DAOFactory.getUtenteDAO();
        PostoDAO psd = DAOFactory.getPostoDAO();
        PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();
        SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();
        PrezzoDAO pzd = DAOFactory.getPrezzoDAO();
        //<editor-fold defaultstate="collapsed" desc="Gestione stringa posti">

        String[] postiRaw = request.getParameter("posti").split(" ");
        ArrayList<Posto> posti = new ArrayList<>();

        int id;
        for (String posto : postiRaw) {
            try {
                id = Integer.parseInt(posto);

                try {
                    posti.add(psd.getPostoById(id));
                } catch (SQLException ex) {
                    System.err.println("ERRORE! Impossibile ottenere il posto con id: " + id);
                    ex.printStackTrace();
                }

            } catch (NumberFormatException nfe) {
            }
        }
        //</editor-fold>

        
        int spettacoloid = Integer.parseInt(request.getParameter("spettacolo"));
        Spettacolo spettacolo = null;
        try {
            spettacolo = spd.getSpettacoloById(spettacoloid);

            
            if(spettacolo != null){
                for(Posto posto: posti){

                        if(psd.isReserved(spettacolo.getId(),posto.getId())){
                            request.setAttribute("mmessage", "Siamo spiacenti, un utente ha prenotato uno dei suoi posti mentre lei completava l'acquisto,"
                                    + " la preghiamo di riprovare."
                                    + "(eventuali accrediti su carta di credito saranno rimborsati immediatamente)");

                            request.setAttribute("errorcode", "409");
                            request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
                        }

                    } 
            }
            
            
            
            
        } catch (SQLException ex) {
            System.err.println("Errore, impossibile ottenere info sullo spettacolo");
            ex.printStackTrace();
        }

        
        //<editor-fold defaultstate="collapsed" desc="gestione utente">
        Utente currentUser = null;

        try {
            currentUser = usd.getUtenteByEmail(request.getParameter("utente"));
        } catch (SQLException ex) {
            System.err.println("ERRORE! Impossibile ottenere l'utente con mail: " + request.getParameter("utente"));
            ex.printStackTrace();
        }

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="gestione prezzi">
        String[] prezziRaw = request.getParameter("prezzi").split(" ");

        HashMap<Integer, Integer> posto_prezzo = new HashMap<>();

        for (String tag_prezzo : prezziRaw) {
            tag_prezzo = tag_prezzo.trim();
            String[] split_prezzo = tag_prezzo.split("_");
            if ((split_prezzo.length > 2) || (split_prezzo.length > 2)) {
                response.setStatus(500);
            }

            try {
                posto_prezzo.put(Integer.parseInt(split_prezzo[0]), Integer.parseInt(split_prezzo[1]));

            } catch (NumberFormatException nfe) {
            }
        }

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="DEBUG PAGE">
        //DEBUG //PrintWriter out = response.getWriter();
        //DEBUG //out.println("Confirmation temp page");
        //DEBUG //out.println("Utente: " + request.getParameter("utente"));
        //DEBUG //out.println("Spettacolo: " + request.getParameter("spettacolo"));
        //DEBUG //out.println("Prezzi: \"" + request.getParameter("prezzi") + "\"");
        //DEBUG //out.println("Formatted Prezzi:");
        //DEBUG //for(int i : posto_prezzo.keySet()){
        //DEBUG //out.println("Posto: " + i + " Prezzo: " + posto_prezzo.get(i));
        //DEBUG //}
        //DEBUG //out.println("Posti:");
        //DEBUG //for(Posto posto : posti)
        //DEBUG //out.println(posto);
        //DEBUG //out.println("TotalePagato: " + request.getParameter("totalePagato"));
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Inserimento prenotazione">
        
        //retrieving current time
        long millis = System.currentTimeMillis();
        Timestamp now = new Timestamp(millis);

        //Creazione della cartella che conterra'i qrcode
        File appTempDir = (File) getServletContext().getAttribute(ServletContext.TEMPDIR);
        //Struttura per i path dei qrcode
        ArrayList<File> tmpPath = new ArrayList<File>(posto_prezzo.size());

        try {
            int i = 0;
            for (Posto posto : posti) {
                if (prd.isItAlreadyStored(currentUser.getId(), spettacoloid, posto.getId()) == false) {
                    prd.addPrenotazione(new Prenotazione(null, currentUser.getId(), spettacoloid, posto_prezzo.get(posto.getId()), posto.getId(), now));

                    //Creazione degli oggetti necessari per la creazione del qr code e creazione del qr code
                    Prezzo prezzo = pzd.getPrezzoById(posto_prezzo.get(posto.getId()));
                    File tmpFile = File.createTempFile("qr" + (i) + "_" + RandomStringUtils.randomAlphanumeric(8), ".png", appTempDir);
                    tmpPath.add(i, tmpFile); //PATH da inizializzare con la directory dove vengono salvati i QR CODE
                    QRGenerator.generaQR(tmpPath.get(i).toString(), currentUser.getEmail(), Float.toString(prezzo.getPrezzo()), prezzo.getTipo(), "" + posto.getRiga() + posto.getPoltrona(), spettacolo);

                    i++;
                } else {
                    System.err.println("Prenotazione già inserita!");
                }
            }
        } catch (SQLException ex) {
            System.err.println("ERRORE! Impossibile creare prenotazione");
            ex.printStackTrace();
        }

        //</editor-fold>
        //Creazione del pdf contenente i qrcode
        File p = File.createTempFile("Biglietti" + RandomStringUtils.randomAlphanumeric(8), ".pdf", appTempDir);
        PDFGenerator.generaPDF(request.getParameter("utente"), tmpPath, p);
        //Invio della mail di conferma
        try {
            MailSender.sendTickets(currentUser.getEmail(), p.toString());
        } catch (EmailException ex) {
            System.err.println("ERRORE! Impossibile inviare mail");
            ex.printStackTrace();
        }
        request.setAttribute("utente", request.getParameter("utente"));
//            request.setAttribute("posti" ,request.getParameter("posti"));

        //ELIMINAZIONE FILE TEMPORANEI 
        for (File tmp : tmpPath) {
            tmp.deleteOnExit();
            tmp.delete();
        }
        p.deleteOnExit();
        p.delete();

        String postiString = "";

        for (int i = 0; i < posti.size(); i++) {
            if (i != 0) {
                postiString += ",";
            }

            postiString += "(Riga:<b><i><u>" + posti.get(i).getRiga() + "</u></i></b> / Poltrona:<b><i><u>" + posti.get(i).getPoltrona() + "</u></i></b>)";
        }
        request.setAttribute("posti", postiString);
        request.setAttribute("totalePagato", request.getParameter("totalePagato"));

        request.setAttribute("pageCurrent", "confirmationpage");

//            request.getRequestDispatcher("/JSP/confirmationpage.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath() + request.getServletPath());

    }
}
