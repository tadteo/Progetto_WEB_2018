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
//				List<PrettyPrintFilmGenere> filmspp = new ArrayList<>();
//				for( Film film : fld.getAll()){
//					for(Genere genere : gnd.getAll()){
//						if(Objects.equals(genere.getId(), film.getGenereId())){
//							filmspp.add(new PrettyPrintFilmGenere(film, genere));
//						}
//					}
//				}
//
//				//Prende in input il numero del film richiesto e crea un bean con il film richiesto corrispondente
//				Integer idFilmRichiesto = Integer.parseInt(request.getParameter("film"));
//				PrettyPrintFilmGenere filmRichiesto = new PrettyPrintFilmGenere();
//				for( PrettyPrintFilmGenere flm:filmspp){
//					if(Objects.equals(flm.getFilm().getId(), idFilmRichiesto) ){
//						filmRichiesto = flm;
//					}
//				}
//				request.setAttribute("filmRichiesto", filmRichiesto);

                //Corretto:
                
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
            /*codice prenotazione*/
			PostoDAO pst = DAOFactory.getPostoDAO();
			try{
				List<Posto> posti = pst.getAll();
				
				int maxRiga=0;
				int maxPoltrona=0; //Poltrona corrisponde a colonna
				Integer sala = Integer.parseInt(request.getParameter("sala"));
				for(Posto posto:posti){
					if(Objects.equals(posto.getSalaId(), sala)){
						if(posto.getRiga() > maxRiga ){
							maxRiga = posto.getRiga();
						}
						if(posto.getPoltrona() > maxPoltrona ){
							maxPoltrona = posto.getPoltrona();
						}
					}
				}
				
				List<List<Posto>> postiSala = new ArrayList();
				for( int i=0; i<maxRiga; i++){
					postiSala.add( new ArrayList());
				}
				for(Posto posto:posti){
					if(Objects.equals(posto.getSalaId(), sala)){
						postiSala.get(posto.getRiga()).add(posto.getPoltrona(), posto);
					}
				}
				
				List<String> disposizione = new ArrayList();
				int counter=0;
				for(List<Posto> righe:postiSala){
					disposizione.add(counter,"");
					for(Posto colonne:righe){
						if(colonne.getEsiste()){
							 disposizione.get(counter).concat("a");
						} else{
							
						}
					}
					counter++;
				}
					
				
				
			} catch (SQLException ex) {
				System.out.println("Errore impossibile ottenere i posti");
				ex.printStackTrace();
			}
			
			
			
            
          request.getRequestDispatcher("JSP/reservationpage.jsp").forward(request, response);
        }
    }

}
