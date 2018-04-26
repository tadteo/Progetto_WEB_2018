/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.common.PrettyPrintFilmGenere;
import it.unitn.disi.cinema.dataaccess.Beans.*;
import it.unitn.disi.cinema.dataaccess.DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author domenico - matteo
 */
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*Accesso db*/    //Perchè questa soluzione non funziona? non posso leggere da homepage.jsp gli attributi di request
        FilmDAO fld = DAOFactory.getFilmDAO();
        GenereDAO gnd = DAOFactory.getGenereDAO();
        PrezzoDAO prd = DAOFactory.getPrezzoDAO();
        try {
            List<Prezzo> prezzi = prd.getAll();
			
            
            List<PrettyPrintFilmGenere> filmspp = new ArrayList<>();
            for( Film film : fld.getAll()){
                for(Genere genere : gnd.getAll()){
                    if(genere.getId() == film.getGenereId()){
                        filmspp.add(new PrettyPrintFilmGenere(film, genere));
                    }
                }
            }          
                        
            request.setAttribute("filmspp", filmspp);            
            request.setAttribute("prezzi", prezzi);		
            request.getRequestDispatcher("JSP/homepage.jsp").forward(request, response);			
        } catch (SQLException ex) {
            System.out.println("Errore, impossibile ottenere la lista dei film");
            ex.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//      response.setStatus(500);

//      PrintWriter out = response.getWriter();
//      out.println("Yeah\nParameter is: " + request.getParameter("pageRequested"));

        String pageRequested = request.getParameter("pageRequested");
        if(pageRequested.equals("filmpage")){
			String id_film;
			FilmDAO fld = DAOFactory.getFilmDAO();
			GenereDAO gnd = DAOFactory.getGenereDAO();
			PrezzoDAO prd = DAOFactory.getPrezzoDAO();
            SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();
            
			try {
				//Prende in input il numero del film richiesto e crea un bean con il film richiesto corrispondente
				Integer idFilmRichiesto = Integer.parseInt(request.getParameter("film"));
                
                Film film = fld.getFilmById(idFilmRichiesto);
                Genere genere = gnd.getGenereById(film.getGenereId());
                
                long millis = System.currentTimeMillis();
                Timestamp now = new Timestamp(millis);
                
                List<Spettacolo> spettacoliDisponibili = spd.getByFIlmAfter(idFilmRichiesto, now);
                
				request.setAttribute("film", film);
				request.setAttribute("genere", genere);
				request.setAttribute("spettacoli", spettacoliDisponibili);
                   

				request.setAttribute("message","La richiesta arriva dalla servlet");
                
				request.getRequestDispatcher("JSP/filmpage.jsp").forward(request, response);
			}catch(SQLException ex) {
				System.out.println("Errore, impossibile ottenere la lista dei film");
				ex.printStackTrace();
			}
        }else if(pageRequested.equals("infopage")){
			PrezzoDAO prd = DAOFactory.getPrezzoDAO();
			try {
				List<Prezzo> prezzi = prd.getAll();            
				request.setAttribute("prezzi", prezzi);		
				request.getRequestDispatcher("JSP/infopage.jsp").forward(request, response);			
			} catch (SQLException ex) {
				System.out.println("Errore, impossibile ottenere i prezzi");
				ex.printStackTrace();
			}
		}else if(pageRequested.equals("reservationpage")){
            Integer spettacolo_id = Integer.parseInt(request.getParameter("spettacolo_id"));
            
            PrintWriter out = response.getWriter();
            
            
            /*
            Beans necessari:
                x Spettacolo 1
                x Sala 1
                x Lista posti sala 1
                Prezzi
            */
            
            //DAOs
            SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();
            SalaDAO sld = DAOFactory.getSalaDAO();
            PostoDAO psd = DAOFactory.getPostoDAO();
            FilmDAO fld = DAOFactory.getFilmDAO();
            PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();
            
            try {
                Spettacolo spettacolo = spd.getSpettacoloById(spettacolo_id);
                Sala sala = sld.getSalaById(spettacolo.getSalaId());
                List<Posto> posti = psd.getPostoBySalaId(sala.getId());
                Film film = fld.getFilmById(spettacolo.getFilmId());
                List<Prenotazione> prenotazioni = prd.getBySpettacolo(spettacolo_id);
                
                request.setAttribute("spettacolo", spettacolo);
                request.setAttribute("sala", sala);
                request.setAttribute("film", film);
                request.setAttribute("posti", posti);
                request.setAttribute("prenotazioni", prenotazioni);
                
                
                
            out.println("ReservationPage");
            out.println("Film: " + film.getTitolo());
            out.println("Sala: " + sala.getDescrizione());
            out.println("Posti: " + posti.size());
                
                
                ArrayList<String> mappa = new ArrayList<>();
                //a posto disponibile
                //b posto prenotato
                //_ posto inesistente
                int currentRiga = 1;
                String currentStringa = new String();
                for(int j = 0; j<posti.size(); j++){
                    if(posti.get(j).getRiga() > currentRiga){
                        mappa.add(currentStringa);
                        currentStringa = new String();
                        out.println();
                        currentRiga++;
                    }
                    //non stampa l'ultima riga
                    //Non esegue l'if all'ultimo ciclo
                    //Non basta aggiungere la riga fuori
                    //Check
                    
                    char seatChar = '0';
                    if(posti.get(j).getEsiste() == false)
                        seatChar='_';
                    else if(psd.isReserved(spettacolo_id, posti.get(j).getId())){
                        //prenotato
                        seatChar='b';
                    }else{
                        seatChar='a';
                    }
                    
                    currentStringa = currentStringa + seatChar;
                    out.print(seatChar);
                }
                
                out.println("\n\nArraylist:");
                for(String s : mappa)
                    out.println(s);
                
                
                request.setAttribute("mappa", mappa);
                
                
                
				//request.getRequestDispatcher("JSP/reservationpage.jsp").forward(request, response);
                
            } catch (SQLException ex) {
                System.err.println("ERRORE: impossibile ottenere dati per request page");
                ex.printStackTrace();
            }
            
            
            
        }
    }

}
