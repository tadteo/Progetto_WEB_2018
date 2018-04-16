/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.dataaccess.Beans.*;
import it.unitn.disi.cinema.dataaccess.DAO.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author domenico
 */
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*Accesso db*/    //Perchè questa soluzione non funziona? non posso leggere da homepage.jsp gli attributi di request
        FilmDAO fld = DAOFactory.getFilmDAO();
        GenereDAO gnd = DAOFactory.getGenereDAO();
        try {
            List<Film> films = fld.getAll();
            request.setAttribute("films", films);
            
            List<Genere> generi = gnd.getAll();
            request.setAttribute("generi", generi);

            request.getRequestDispatcher("JSP/homepage.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Errore, impossibile ottenere la lista dei film");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(500);
    }

}
