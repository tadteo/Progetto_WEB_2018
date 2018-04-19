/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.dataaccess.Beans.*;
import it.unitn.disi.cinema.dataaccess.DAO.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
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
            List<Film> films = fld.getAll(); //Riempio i bean con le istanze del database
            List<Genere> generi = gnd.getAll();
			List<Prezzo> prezzi = prd.getAll();
			
            request.setAttribute("generi", generi);
            
			//Associo ad ogni film la stringa corrispondente al suo genere
			for( Film flm:films){
				for(Genere gnr:generi){
					if(Objects.equals(gnr.getId(), flm.getGenereId())){
						flm.setGenere(gnr.getDescrizione());
					}
				}
			}
			
            request.setAttribute("films", films);            
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
        response.setStatus(500);
    }

}
