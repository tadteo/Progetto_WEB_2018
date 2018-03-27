/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.ecommerce.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        else
            response.setStatus(500);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if(session.getAttribute("username") != null)        
            response.setStatus(500);//Commento//Dovrebbe bastare questo per non avere problemi con utenti già loggati che accedono in post alla servlet
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //Commento//Semplice encoding MD5 da mandare al db ---------------------------------------|
            String hash = MD5(password);
        //----------------------------------------------------------------------------------------|   
//        
        if((username.equals("username"))&&(hash.equals("5f4dcc3b5aa765d61d8327deb882cf99"))){ //Commento//Questa è la hash MD5 di "password"
            session.setAttribute("username", username);

            Object pageRequested = session.getAttribute("pageRequested");

            username = null;
            
            if(pageRequested != null){
                response.sendRedirect(request.getContextPath() + pageRequested.toString());
            }else
                response.sendRedirect(request.getContextPath());
        }
        else{
            username = null;
            response.sendRedirect(request.getContextPath() + "/login.do");
        }
    }

    public String MD5(String md5) { //Commento//Forse bisogna aggiugere la codifica della stringa. Verificare
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
             e.printStackTrace();
         }
        return null;
    }
}
