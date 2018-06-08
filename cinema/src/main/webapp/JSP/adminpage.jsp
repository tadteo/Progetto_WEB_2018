<%--
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
--%>
<%-- 
    Document   : admin
    Author     : ivan
--%>
<%@page import="it.unitn.disi.cinema.dataaccess.Beans.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@page import="java.util.StringTokenizer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--NON UTILIZZARE!-->
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"  crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Admin control panel</title>
    </head>
	
    <body class="collage">
        <jsp:include page='components/header.jsp'/>
        <div class="container">
            
            <div class="row">
                <div class="col-12 col-md-3"></div>
                <div class="col-12 col-md-6">
                    <div class="accordion accordion-margin accordion-marginTop" id="accordionProgrammazioni">
                <div class="card">
                  <div class="card-header" id="headingProgrammazioni">
                    <h5 class="mb-0">
                      <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseProgrammazioni" aria-expanded="true" aria-controls="collapseProgrammazioni">
                        Situazione delle programmazioni
                      </button>
                    </h5>
                  </div>
                  <div id="collapseProgrammazioni" class="collapse show" aria-labelledby="headingProgrammazioni" data-parent="#accordionProgrammazioni">
                    <div class="card-body overflow-card">
                      <!--Gestione programmazioni-->
                    </div>
                  </div>
                </div>
              </div>
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-12 col-md-3"></div>
                <div class="col-12 col-md-6">
                    <div class="accordion accordion-margin accordion-marginMiddle" id="accordionIncassi">
                <div class="card">
                  <div class="card-header" id="headingIncassi">
                    <h5 class="mb-0">
                      <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseIncassi" aria-expanded="false" aria-controls="collapseIncassi">
                        Lista incassi per film
                      </button>
                    </h5>
                  </div>
                  <div id="collapseIncassi" class="collapse" aria-labelledby="headingIncassi" data-parent="#accordionIncassi">
                    <div class="card-body overflow-card">
                      <!--Gestione Incassi-->
                    </div>
                  </div>
                </div>
              </div>
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-12 col-md-3"></div>
                <div class="col-12 col-md-6">
                    <div class="accordion accordion-margin accordion-marginMiddle" id="accordionClienti">
                <div class="card">
                  <div class="card-header" id="headingClienti">
                    <h5 class="mb-0">
                      <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseClienti" aria-expanded="false" aria-controls="collapseClienti">
                        Lista dei clienti top 
                      </button>
                    </h5>
                  </div>
                  <div id="collapseClienti" class="collapse" aria-labelledby="headingClienti" data-parent="#accordionClienti">
                    <div class="card-body overflow-card">
                    <!--Gestione Clienti-->
                    </div>
                  </div>
                </div>
              </div>
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-12 col-md-3"></div>
                <div class="col-12 col-md-6">
                    <div class="accordion accordion-margin accordion-marginBottom" id="accordionPrenotazioni">
                 <div class="card">
                  <div class="card-header" id="headingPrenotazioni">
                    <h5 class="mb-0">
                      <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapsePrenotazioni" aria-expanded="false" aria-controls="collapsePrenotazioni">
                        Gestione delle prenotazioni
                      </button>
                    </h5>
                  </div>
                  <div id="collapsePrenotazioni" class="collapse" aria-labelledby="headingPrenotazioni" data-parent="#accordionPrenotazioni">
                    <div class="card-body overflow-card">
                      <!--Gestione Prenotazioni-->
                    </div>
                  </div>
                </div>
</div>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
            

              

              
        
              
    
	<jsp:include page='components/footer.jsp'/>
    </body>

   <!--PAGE INUTILIZZATA-->

</html>
