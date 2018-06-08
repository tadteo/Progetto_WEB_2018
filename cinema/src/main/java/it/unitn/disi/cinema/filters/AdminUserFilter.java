/*
 * Cinema Universe - Reservation System
 * Copyright (C) 2018 Domenico Stefani, Ivan Martini, Matteo Tadiello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See <http://www.gnu.org/licenses/>.
 */

package it.unitn.disi.cinema.filters;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author domenico
 */
public class AdminUserFilter implements Filter {
    
    public AdminUserFilter() {
    }    
    
    
    
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httprequest = (HttpServletRequest) req;
		HttpServletResponse httpresponse = (HttpServletResponse) res;
        
        HttpSession session = httprequest.getSession(false); 
        
        PrintWriter out = httpresponse.getWriter();
        out.println("Load page error");
        String email = (String)session.getAttribute("email");
        if(email == null){
            out.println("Requested is: " + httprequest.getRequestURI());
            session.setAttribute("filterSavedRequest", httprequest.getRequestURI());
            
            httprequest.getRequestDispatcher("/login.do").forward(httprequest, httpresponse);
        }else{
            if(session.getAttribute("ruolo").equals("admin")){
                chain.doFilter(req, res);
            }else{
                httprequest.setAttribute("errorcode", "401");
                httprequest.setAttribute("mmessage", "Questa pagina è riservata agli amministratori, non sei autorizzato.");
                httprequest.getRequestDispatcher("/JSP/errorpage.jsp").forward(httprequest, httpresponse);
            }
        }
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
