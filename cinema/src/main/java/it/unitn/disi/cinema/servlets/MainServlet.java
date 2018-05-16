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
            request.setAttribute("pageCurrent","homepage");
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
				request.setAttribute("calltime", now);/**/

                
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
            final boolean DEBUG = false;
            final boolean DELIMITERS = false;
            
            PrintWriter out;
            if(DEBUG)
                out = response.getWriter();
            
            
            /*
            Beans necessari:
                x Spettacolo 1
                x Sala 1
                x Lista posti sala 1
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
                
                if(DEBUG){
                    out.println("ReservationPage");
                    out.println("Film: " + film.getTitolo());
                    out.println("Spettacolo: " + spettacolo.getId());
                    out.println("Sala: " + sala.getDescrizione() + " "+ sala.getId());
                    out.println("Posti: " + sld.getPostiCount(sala.getId()));
                    out.println("Posti Disponibili: " + (sld.getPostiCount(sala.getId()) - spd.getAvailablePostiCount(spettacolo_id)));
                }
                
                ArrayList<String> mappa = new ArrayList<>();
                //a posto disponibile
                //b posto prenotato
                //_ posto inesistente
                
                final char letterForAvailable = 'a';
                final char letterForReserved = 'b';
                final char letterForNotExists = '_';
                
                
                int currentRiga = 1;
                String currentStringa = new String();
                if(DELIMITERS)
                    currentStringa = currentStringa + "'";  //aggiunge delimitatore
                
                for(int j = 0; j<posti.size(); j++){
                    if(posti.get(j).getRiga() > currentRiga){
                        if(DELIMITERS)
                            currentStringa = currentStringa + "'";  //aggiunge delimitatore
                        mappa.add(currentStringa);
                        currentStringa = new String();
                        if(DELIMITERS)
                            currentStringa = currentStringa + "'";  //aggiunge delimitatore
                        currentRiga++;
                    }
                    
                    char seatChar = '0';
                    if(posti.get(j).getEsiste() == false)
                        seatChar=letterForNotExists;
                    else if(psd.isReserved(spettacolo_id, posti.get(j).getId())){
                        //prenotato
                        seatChar=letterForReserved;
                    }else{
                        //libero
                        seatChar=letterForAvailable;
                    }
                    currentStringa = currentStringa + seatChar;
                }
                
                if(DELIMITERS)
                    currentStringa = currentStringa + "'";  //aggiunge delimitatore
                mappa.add(currentStringa);
                
                /*--Reserved String--*/
                String reservedString = "";
                List<Posto> reserved = spd.getReservedPosti(spettacolo_id);
                Posto p;
                for(int i = 0; i < reserved.size(); i++){
                    p = reserved.get(i);
                    reservedString += (p.getRiga() + "_" + p.getPoltrona());
                    if(i != reserved.size()-1)
                        reservedString += ",";
                }
                if(DEBUG)
                    out.println("\nPosti Non disponibili: \n" + reservedString);
                
                if(DEBUG){
                    out.println("\n\nArraylist:");
                    for(String s : mappa)
                        out.println(s);
                    out.println("");
                }
                
                if(!DEBUG){
                    request.setAttribute("mappa", mappa);
                    request.setAttribute("reserved", reservedString);
                    request.getRequestDispatcher("JSP/reservationpage.jsp").forward(request, response);
                }
                
            } catch (SQLException ex) {
                System.err.println("ERRORE: impossibile ottenere dati per request page");
                ex.printStackTrace();
            }
            
            
            
        } else if(pageRequested.equals("buypage")){
			//DAOs
            SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();
            SalaDAO sld = DAOFactory.getSalaDAO();
            PostoDAO psd = DAOFactory.getPostoDAO();
            FilmDAO fld = DAOFactory.getFilmDAO();
            PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();
			PrezzoDAO prz = DAOFactory.getPrezzoDAO();
            
			try {
				List<Prezzo> prezzi = prz.getAll();
				
				String selezionati = request.getParameter("posti"); // Richiedo tutti i posti selezionati dalla requestpage sotto forma di stringa del tipo -Riga_Colonna!-Riga_Colonna!-etc_etc!
				// Variabili e oggetti che mi servono per "parsare" la stringa Posti in due liste di interi
				List<Integer> Riga= new ArrayList();
				List<Integer> Colonna= new ArrayList();
				String riga="";
				String colonna="";
				Boolean RoC = true; //Indica se stiamo creando la string per la colonna o per la riga: true indica la riga, false indica la colonna
				
				//Creo le due liste di interi
				for(char c:selezionati.toCharArray()){
					if(c == '-') {
						RoC=true;
						riga="";
						colonna="";
					} else if(Character.isDigit(c) && RoC ) {
						riga+=c;
					} else if(Character.isDigit(c) && !RoC) {
						colonna+=c;
					} else if( c== '_'){
						RoC = false;
					} else if( c== '!'){
						Riga.add(Integer.parseInt(riga));
						Colonna.add(Integer.parseInt(colonna));
					}
				}
				Integer sala = Integer.parseInt(request.getParameter("sala"));
				//Ciclo sulle rige per creare i bean dei posti necessari alla buypage
				List<Posto> posti= new ArrayList();
				for(int i=0; i<Riga.size() ; i++){
					Posto p = psd.getPostoBySalaRigaPoltrona( sala , Riga.get(i) , Colonna.get(i));
					posti.add(p);
				}
				
				
				request.setAttribute("selezionati", selezionati);
				request.setAttribute("prezzi", prezzi);
				request.setAttribute("posti", posti);

				request.setAttribute("pageCurrent","buypage");
				request.getRequestDispatcher("JSP/buypage.jsp").forward(request, response);			
			} catch (SQLException ex) {
				System.out.println("Errore, impossibile ottenere la lista dei film");
				ex.printStackTrace();
			}
		} else if(pageRequested.equals("confermationpage")){
			//DAOs
            SpettacoloDAO spd = DAOFactory.getSpettacoloDAO();
            SalaDAO sld = DAOFactory.getSalaDAO();
            PostoDAO psd = DAOFactory.getPostoDAO();
            FilmDAO fld = DAOFactory.getFilmDAO();
            PrenotazioneDAO prd = DAOFactory.getPrenotazioneDAO();
			PrezzoDAO prz = DAOFactory.getPrezzoDAO();
            
			try {
				request.setAttribute("utente" ,request.getParameter("utente"));
				request.setAttribute("posti" ,request.getParameter("posti"));
				request.setAttribute("totalePagato" ,request.getParameter("totalePagato"));

				
				Sala sala= sld.getSalaById(1);
				request.setAttribute("pageCurrent","confermationpage");
				request.getRequestDispatcher("JSP/confermationpage.jsp").forward(request, response);			
			} catch (SQLException ex) {
				System.out.println("Errore, impossibile ottenere la lista dei film");
				ex.printStackTrace();
			}
        } else if (pageRequested.equals("adminpage")) {
            request.getRequestDispatcher("JSP/adminpage.jsp").forward(request, response);
        }
    }

}
