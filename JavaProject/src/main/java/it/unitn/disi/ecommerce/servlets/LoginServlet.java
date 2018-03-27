/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.ecommerce.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author domenico
 */
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if(session.getAttribute("username") == null)
            response.sendRedirect(request.getContextPath() + "/JSP/loginpage.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        PrintWriter out = response.getWriter();
        out.println("Login fatto");
        
        //Login part
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if((username.equals("username"))&&(password.equals("password"))){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            Object pageRequested = session.getAttribute("pageRequested");


            if(pageRequested != null){
                session.removeAttribute("pageRequested");
                response.sendRedirect(request.getContextPath() + pageRequested.toString());
            }else
                response.sendRedirect(request.getContextPath() + "/homepage");
//                request.getRequestDispatcher("/JSP").forward(request, response);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/login.do");
        }
//        //----------
        
    }


}
