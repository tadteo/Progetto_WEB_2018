/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.dataaccess.Beans.Posto;
import it.unitn.disi.cinema.dataaccess.Beans.Sala;
import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.PostoDAO;
import it.unitn.disi.cinema.dataaccess.DAO.SalaDAO;
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
public class ModSalaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SalaDAO sld = DAOFactory.getSalaDAO();
        PostoDAO psd = DAOFactory.getPostoDAO();
        
        String idReq_str = request.getPathInfo();
        if (idReq_str == null) {
            request.setAttribute("errorcode", "404");
            request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
        }
        idReq_str = idReq_str.substring(1);
        if (idReq_str.equals("") || idReq_str.equals("modificasala")) {
            request.setAttribute("errorcode", "404");
            request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
        }
        
        Integer idReq = null;
        try {
            idReq = Integer.parseInt(idReq_str);
            if(idReq <= 0)
                throw new NumberFormatException();
        } catch (NumberFormatException nfe) {
            request.setAttribute("errorcode", "400");
            request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
        }

        try {
            Sala sala = sld.getSalaById(idReq);
            if(sala != null){
                
                List<Posto> posti = psd.getPostoBySalaId(sala.getId());

                
                
                
               
                request.setAttribute("sala", sala);
                request.setAttribute("posti", posti);
                request.getRequestDispatcher("/JSP/modifysalapage.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            System.err.println("ERRORE! Impossibile ottenere la sala richiesta");
            request.setAttribute("errorcode", "400");
            request.getRequestDispatcher("/JSP/errorpage.jsp").forward(request, response);
        }
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
