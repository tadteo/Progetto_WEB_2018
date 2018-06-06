<%-- 
    Document   : film
    Created on : Apr 19, 2018, 6:54:49 PM
    Author     : matteo
--%>
<%@page import="it.unitn.disi.cinema.dataaccess.Beans.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@page import="java.util.StringTokenizer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%--<c:set var="context" value="${pageContext.request.contextPath}" />--%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"  crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Film</title>
  </head>

  <body class="collage">
    <jsp:include page='components/header.jsp'/>
    <div class="container film-container">
      <div class="jumbotron">
        <div class="row  justify-content-center">
          <div class="col-12 col-md-3 ">
            <img class="locandina-filpage" src="${pageContext.request.contextPath}/images${requestScope.film.getUrlLocandina()}" alt="" style="width:100%"/>
          </div>
          <div class="col-12 col-md-5 justify-content-center">
            <h2><b>${requestScope.film.getTitolo()}</b></h2>
            <p><b>Genere:</b> ${requestScope.genere.getDescrizione()}</p>
            <p><b>Durata:</b> ${requestScope.film.getDurata()} minuti</p>
            <p><b>Trama:</b> ${requestScope.film.getTrama()}</p>
          </div>
          <br />
          <div class="col-12 col-md-4">
            <div class="videoWrapper">
              <!--width="420" height="315"-->
              <iframe  src="${film.getUrlTrailer()}" frameborder="0" allowfullscreen></iframe>
            </div>
          </div>
        </div>
			
        <div class="row">
          <div class="container-fluid text-center">
            <p><b>Spettacoli di oggi (<fmt:formatDate value="${calltime}" pattern="E dd/MM" />)</b></p>
            <div class="wrapper">
              <c:forEach items="${requestScope.spettacoli}" var="spettacolo">
                <c:choose>
                  <c:when test="${spettacolo.getDataOra().getDay() == calltime.getDay()}">  
                    <div class="form-signin my-1">
                      <c:set var="title" value="${film.getTitolo()}"/>
                      <c:set var="title" value="${fn:replace(title,' ', '')}"/>
                      <c:set var="title" value="${fn:toLowerCase(title)}"/>
                      <a href="../prenotaspettacolo/${spettacolo.getId()}-${title}" class="btn btn-lg btn-dark btn-block" >
                        <fmt:formatDate value="${spettacolo.getDataOra()}" pattern="HH:mm" />
                      </a>
                    </div>
                  </c:when>
                </c:choose>
              </c:forEach>      
            </div> 
          </div>
        </div>

        <div class="row">  
          <div id="hide-button" class="container-fluid text-center" >
            <button class="btn btn-outline-success" onclick="showHidden()">Mostra prossimi giorni</button>
          </div>
        </div>
            
        <div class="row">    
          <div id="hidden-shows" class="hidden container-fluid text-center">
              <p><b>Spettacoli dei prossimi giorni:</b></p>
              <div class="wrapper">
                  <c:forEach items="${requestScope.spettacoli}" var="spettacolo">
                      <c:choose>
                          <c:when test="${spettacolo.getDataOra().getDay() != calltime.getDay()}">
                              <div class="form-signin my-1">
                                <c:set var="title" value="${film.getTitolo()}"/>
                                <c:set var="title" value="${fn:replace(title,' ', '')}"/>
                                <c:set var="title" value="${fn:toLowerCase(title)}"/>
                                <a href="../prenotaspettacolo/${spettacolo.getId()}-${title}" class="btn btn-lg btn-dark btn-block" >
                                  <fmt:formatDate value="${spettacolo.getDataOra()}" pattern="HH:mm" />
                                  (<fmt:formatDate value="${spettacolo.getDataOra()}" pattern="E dd/MM" />)
                                </a>
                              </div>
                          </c:when>
                      </c:choose>
                  </c:forEach>
              </div>
          </div>
        </div>
      </div>
      <br />
    </div>
    <jsp:include page='components/footer.jsp'/>
    <script>
      function showHidden() {
        var hiddenShows = document.getElementById("hidden-shows");
        hiddenShows.classList.remove("hidden");

        var button = document.getElementById("hide-button");
        button.classList.add("hidden");
      }
    </script>
  </body>
</html>
