/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.servlets;

import it.unitn.disi.cinema.dataaccess.Beans.Prezzo;
import it.unitn.disi.cinema.dataaccess.DAO.DAOFactory;
import it.unitn.disi.cinema.dataaccess.DAO.PrezzoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author domenico
 */
public class InfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrezzoDAO prd = DAOFactory.getPrezzoDAO();
        try {
            List<Prezzo> prezzi = prd.getAll();
            request.setAttribute("prezzi", prezzi);
            request.setAttribute("hideInfos", "true");
            request.setAttribute("pageCurrent", "infopage");
            request.getRequestDispatcher("JSP/infopage.jsp").forward(request, response);
        } catch (SQLException ex) {
            System.out.println("Errore, impossibile ottenere i prezzi");
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
