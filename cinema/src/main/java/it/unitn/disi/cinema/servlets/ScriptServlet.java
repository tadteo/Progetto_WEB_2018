/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.dataaccess.Beans.Film;
import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.FilmDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
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
            
            Instant now = Instant.now();
//            out.println(now); // prints 2017-03-14T06:16:32.621Z
            Timestamp current = Timestamp.from(now);
            out.println("Current Time:" + current); // 2017-03-14 08:16:32.
            
            out.println();

            
            try {
                FilmDAO fld = DAOFactory.getFilmDAO();
                List<Film> films = fld.getAll();
                for(Film film : films){
                    out.println(film.getTitolo() + ":");
                    
                    Random rnd = new Random();
                    int base = rnd.nextInt(durata);
                    
                    Timestamp timeIterator = addMinutesToTimestamp(base, current);
                    for (int i = 1; i < numeroProiezioni; i++) {
                        out.print(printTime(timeIterator) + " ");
                        timeIterator = addMinutesToTimestamp(durata, timeIterator);
                    }


                    out.println();
                }
            } catch (SQLException ex) {
                System.out.println("Impossibile ottenere la lista dei film");
                ex.printStackTrace();
            }
        }
    }
    
    
    private static Timestamp addMinutesToTimestamp(int minutes, Timestamp beforeTime){
        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

        long currentMillis = beforeTime.getTime();
        Timestamp res = new Timestamp(currentMillis + (minutes * ONE_MINUTE_IN_MILLIS));
                
        return res;
    }
    
    private String printTime(Timestamp timestamp){
        if((new SimpleDateFormat("dd").format(new Date(timestamp.getTime()))).equals(new SimpleDateFormat("dd").format(new Date())))
            return new SimpleDateFormat("HH.mm").format(new Date(timestamp.getTime()));
        return "";
    }


}
