/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
public class RegularUserFilter implements Filter {
    
    public RegularUserFilter() {
    }    
    
    
    
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httprequest = (HttpServletRequest) req;
		HttpServletResponse httpresponse = (HttpServletResponse) res;
        
        HttpSession session = httprequest.getSession(false); 
        
        PrintWriter out = httpresponse.getWriter();
        out.println("I'm the almighty filter");
        String email = (String)session.getAttribute("email");
        if(email == null){
            out.println("Requested is: " + httprequest.getRequestURI());
            session.setAttribute("filterSavedRequest", httprequest.getRequestURI());
            
            httprequest.getRequestDispatcher("/login.do").forward(httprequest, httpresponse);
        }else{
            chain.doFilter(req, res);
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
