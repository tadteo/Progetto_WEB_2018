/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
