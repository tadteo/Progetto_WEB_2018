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

import it.unitn.disi.cinema.dataaccess.Beans.Posto;
import it.unitn.disi.cinema.dataaccess.Beans.Prezzo;
import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.PostoDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PrenotazioneDAO;
import it.unitn.disi.cinema.dataaccess.DAO.PrezzoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author domenico
 */
public class BuyPageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String spettacolo = request.getParameter("spettacolo");
        String selezionati = request.getParameter("posti");
        Integer sala = Integer.parseInt(request.getParameter("sala"));

        //DAOs
        PostoDAO psd = DAOFactory.getPostoDAO();
        PrezzoDAO pzd = DAOFactory.getPrezzoDAO();
        PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();

        try {
            List<Prezzo> prezzi = pzd.getAll();

            // Variabili e oggetti che mi servono per "parsare" la stringa Posti in due liste di interi
            List<Integer> Riga = new ArrayList();
            List<Integer> Colonna = new ArrayList();
            String riga = "";
            String colonna = "";
            Boolean RoC = true; //Indica se stiamo creando la string per la colonna o per la riga: true indica la riga, false indica la colonna

            //Creo le due liste di interi
            for (char c : selezionati.toCharArray()) {
                if (c == '-') {
                    RoC = true;
                    riga = "";
                    colonna = "";
                } else if (Character.isDigit(c) && RoC) {
                    riga += c;
                } else if (Character.isDigit(c) && !RoC) {
                    colonna += c;
                } else if (c == '_') {
                    RoC = false;
                } else if (c == '!') {
                    Riga.add(Integer.parseInt(riga));
                    Colonna.add(Integer.parseInt(colonna));
                }
            }
            //Ciclo sulle rige per creare i bean dei posti necessari alla buypage
            List<Posto> posti = new ArrayList();
            for (int i = 0; i < Riga.size(); i++) {
                Posto p = psd.getPostoBySalaRigaPoltrona(sala, Riga.get(i), Colonna.get(i));
                posti.add(p);
            }

            Prezzo intero = null;
            Prezzo ridotto = null;

            List<Prezzo> allprz = pzd.getAll();
            if (allprz.size() < 2) {
                System.err.println("ERRORE DATABASE! Ci sono meno di due prezzi");
            } else {
                if (allprz.get(0).getPrezzo() < allprz.get(1).getPrezzo()) {
                    intero = allprz.get(1);
                    ridotto = allprz.get(0);
                } else {
                    intero = allprz.get(0);
                    ridotto = allprz.get(1);
                }
            }
            
            
            
            Integer spettacolo_id;
            try{
                spettacolo_id = Integer.parseInt(spettacolo.trim());
                
                

                
                for(Posto posto: posti){

                    if(psd.isReserved(spettacolo_id,posto.getId())){
                        request.setAttribute("mmessage", "Siamo spiacenti, un utente ha prenotato uno dei suoi posti mentre lei li stava scegliendo, la preghiamo di riprovare.");
                        
                        request.setAttribute("errorcode", "409");
                        request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
                    }

                }   
                
                
                request.setAttribute("selezionati", selezionati);
                request.setAttribute("spettacolo", spettacolo);
                request.setAttribute("prezzi", prezzi);
                request.setAttribute("posti", posti);

                request.setAttribute("intero", intero);
                request.setAttribute("ridotto", ridotto);

                request.setAttribute("pageCurrent", "buypage");
                
                
                request.getRequestDispatcher("JSP/buypage.jsp").forward(request, response);
                
            }catch(NumberFormatException nfe){
                System.err.println("ERRORE! Impossibile convertire in int la stringa spettacolo");
            }
            
        } catch (SQLException ex) {
            System.out.println("Errore, impossibile ottenere la lista dei film");
            ex.printStackTrace();
        }

    }

}
