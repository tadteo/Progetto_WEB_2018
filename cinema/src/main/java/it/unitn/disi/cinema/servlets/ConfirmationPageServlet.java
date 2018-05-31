/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            for(String posto : postiRaw){
                try{
                    id = Integer.parseInt(posto);
                    
                    try{
                        posti.add(psd.getPostoById(id));
                    }catch(SQLException ex){
                        System.err.println("ERRORE! Impossibile ottenere il posto con id: " + id);
                        ex.printStackTrace();
                    }
                    
                }catch(NumberFormatException nfe){}
            }
            //</editor-fold>

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
           
            HashMap<Integer,Integer> posto_prezzo = new HashMap<>();
            
            for(String tag_prezzo : prezziRaw){
                tag_prezzo = tag_prezzo.trim();
                String[] split_prezzo = tag_prezzo.split("_");
                if((split_prezzo.length > 2)||(split_prezzo.length > 2))
                    response.setStatus(500);
                
                try{
                    posto_prezzo.put(Integer.parseInt(split_prezzo[0]), Integer.parseInt(split_prezzo[1]));
                    
                }catch(NumberFormatException nfe){}
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
            
            int spettacoloid = Integer.parseInt(request.getParameter("spettacolo"));
            Spettacolo spettacolo = null;
            try {
                spettacolo = spd.getSpettacoloById(spettacoloid);
                
            } catch (SQLException ex) {
                System.err.println("Errore, impossibile ottenere info sullo spettacolo");
                ex.printStackTrace();            
            }
            
            //retrieving current time
            long millis = System.currentTimeMillis();
            Timestamp now = new Timestamp(millis);
            
            //creating structure for multiple path 
            ArrayList<String> path = new ArrayList<String>(posto_prezzo.size());
            
            try{
                int i=0;
                for(Posto posto : posti){
                    if(prd.isItAlreadyStored(currentUser.getId(), spettacoloid, posto.getId()) == false){
                        prd.addPrenotazione(new Prenotazione(null,currentUser.getId(),spettacoloid,posto_prezzo.get(posto.getId()),posto.getId(),now));
                        
                        Prezzo prezzo = pzd.getPrezzoById(posto_prezzo.get(posto.getId()));
                        
                        path.add(i, ""+getServletContext().getRealPath("/")+"/qr"+(i)+".png"); //PATH da inizializzare con la directory dove vengono salvati i QR CODE
                        System.out.println("DEBUG## Path is \"" + path.get(i) + "\"");
                        System.out.println("DEBUG## Calling generaQR");
                        QRGenerator.generaQR(path.get(i), currentUser.getEmail(), Float.toString(prezzo.getPrezzo()), prezzo.getTipo() , ""+posto.getRiga()+posto.getPoltrona() , spettacolo);
                        i++;
                    }else
                        System.err.println("Prenotazione già inserita!");
                }
            }catch(SQLException ex){
                System.err.println("ERRORE! Impossibile creare prenotazione");
                ex.printStackTrace();
            }
            
            //</editor-fold>
            
            File p = new File( getServletContext().getRealPath("/")+"/biglietti.pdf");
            PDFGenerator.generaPDF(request.getParameter("utente"), path , p);
            try {
                MailSender.sendTickets(currentUser.getEmail(), p.getCanonicalPath());
            } catch (EmailException ex) {
                System.err.println("ERRORE! Impossibile inviare mail");
                ex.printStackTrace(); 
            }
            request.setAttribute("utente" ,request.getParameter("utente"));
//            request.setAttribute("posti" ,request.getParameter("posti"));

            String postiString = "";
            
            for(int i = 0; i < posti.size(); i++){
                if(i != 0)
                    postiString += ",";
                
                postiString += "(Riga:<b><i><u>" + posti.get(i).getRiga() + "</u></i></b> / Poltrona:<b><i><u>" + posti.get(i).getPoltrona() + "</u></i></b>)";
            }
            request.setAttribute("posti" ,postiString);
            request.setAttribute("totalePagato" ,request.getParameter("totalePagato"));

            request.setAttribute("pageCurrent","confirmationpage");
            
//            request.getRequestDispatcher("/JSP/confirmationpage.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + request.getServletPath());
        
    }
}
