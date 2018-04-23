/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.dataaccess.Beans.Film;
import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author domenico
 */
public class ScriptServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("JSP/scriptpage.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("durata") != null){
            PrintWriter out = response.getWriter();
            
            int durata = Integer.parseInt(request.getParameter("durata"));
            int numeroProiezioni = 10;
            if(request.getParameter("proiezioni") != null)
                numeroProiezioni = Integer.parseInt(request.getParameter("proiezioni"));
            
            out.println("Richieste " + numeroProiezioni + " proiezioni per film, ognuna di durata " + durata + " minuti");
            Calendar rightNow = Calendar.getInstance();
            
            try {
                List<Film> films = DAOFactory.getFilmDAO().getAll();
                for(Film film : films){
                    String tag = "";
                    String fill = "";
                    System.out.print(film.getId() + " - ");
                    
                    int hour = rightNow.get(Calendar.HOUR_OF_DAY);
                    int minute = rightNow.get(Calendar.MINUTE);
                    
                    Random rnd = new Random();
                    int base = rnd.nextInt(durata);
                    
                    minute += base;
                    while(minute > 60){
                        minute -= 60;
                        hour += 1;
                    }
                    while(hour > 24){
                        hour -= 24;
                        tag = " (nextdays)";
                    }
                    
                    out.print(((hour < 10)?"0":"") + hour + ":" + ((minute < 10)?"0":"") + minute + tag);
                    
                    for (int i = 1; i < numeroProiezioni; i++) {
                        minute += durata;
                        while(minute > 60){
                            minute -= 60;
                            hour += 1;
                        }
                        while(hour > 24){
                            hour -= 24;
                            tag = " (nextdays)";
                        }
                        out.print(", " + ((hour < 10)?"0":"") + hour + ":" + ((minute < 10)?"0":"") + minute + tag);
                    }
                    out.println();
                }
            } catch (SQLException ex) {
                System.out.println("Impossibile ottenere la lista dei film");
            }
        }
    }


}
