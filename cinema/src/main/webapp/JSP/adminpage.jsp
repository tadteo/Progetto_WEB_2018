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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"  crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Admin control panel</title>
    </head>
	
    <body class="collage">
        <jsp:include page='components/header.jsp'/>

            <div class="accordion accordion-margin accordion-marginTop" id="accordionProgrammazioni">
                <div class="card">
                  <div class="card-header" id="headingProgrammazioni">
                    <h5 class="mb-0">
                      <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseProgrammazioni" aria-expanded="true" aria-controls="collapseProgrammazioni">
                        Situazione delle programmazioni
                      </button>
                    </h5>
                  </div>
                  <div id="collapseProgrammazioni" class="collapse" aria-labelledby="headingProgrammazioni" data-parent="#accordionProgrammazioni">
                    <div class="card-body">
                      <!--Gestione programmazioni-->
                    </div>
                  </div>
                </div>
              </div>

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
                    <div class="card-body">
                      <!--Gestione Incassi-->
                      <table class="table  table-striped">
                        <thead class="thead-dark">
                          <tr>
                            <th scope="col">Film</th>
                            <th scope="col">Incasso giornaliero</th>
                            <th scope="col">Incasso totale</th>
                          </tr>
                        </thead>
                        <tbody>
                      <c:forEach items="${requestScope.film}" var="film">
                          <!-- tutti i film>-->
                          <c:set var = "tot" value = "${0}"/>
                          <c:set var = "totGiorno" value = "${0}"/>
                          <tr>
                          <td>${film.getTitolo()}</td>
                         
                          <c:forEach items="${requestScope.spettacolo}" var="spettacolo">  
                            <!--Per ogni film, prendo solo gli spettacoli legati a quel film-->
                              <c:choose>        
                                <c:when test="${spettacolo.getFilmId() == film.getId()}">                                
                                  <c:forEach items="${requestScope.prenotazione}" var="prenotazione">
                                    <!--Per ogni spettacolo, prendo solo le prenotazioni relative a quello spettacolo-->
                                    <c:choose>
                                        <c:when test="${prenotazione.getSpettacoloId() == spettacolo.getId()}">
                                          <c:set var="costo" value="prezzo${prenotazione.getPrezzoId()}" />
                                          <c:set var = "tot" value = "${tot+requestScope[costo]}"/>
                                          
                                          <c:choose>
                                              <c:when test="${prenotazione.getDataOraOperazione().getDay() == calltime.getDay()}">
                                                  <c:set var="costo" value="prezzo${prenotazione.getPrezzoId()}" />
                                                  <c:set var="totGiorno" value = "${totGiorno+requestScope[costo]}"/>
                                              </c:when>
                                          </c:choose>                         
                                        </c:when>
                                    </c:choose>
                                  </c:forEach>     
                                </c:when>                      
                              </c:choose>                             
                          </c:forEach>                                                         
    
                        <td>${totGiorno}</td>
                        <td>${tot}</td>
                        </tr>
                      </c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>

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
                    <div class="card-body">
                    <!--Gestione Clienti-->
                    </div>
                  </div>
                </div>
              </div>
        
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
                    <div class="card-body">
                      <!--Gestione Prenotazioni-->
                    </div>
                  </div>
                </div>
</div>
    
	<jsp:include page='components/footer.jsp'/>
    </body>
</html>
