<%-- 
    Document   : homepage
    Created on : 23-mar-2018, 16.58.41
    Author     : Domenico Stefani - Matteo Tadiello
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="it.unitn.disi.cinema.dataaccess.Beans.Prezzo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@ page import="java.util.StringTokenizer" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"  crossorigin="anonymous">
		
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Cinema-Homepage</title>
    </head>
<!--    <body  style="background-image: url('/cinema/images/collage.jpg');">-->
    <body class="collage">
        <jsp:include page='components/header.jsp'/>
        <div class="row justify-content-center">
          <div class="col-md-10">             
			<br/>
			<div id="movies">
				<div class="text-center onefilm">
          <h2><b>Questa settimana al cinema :</b></h2>
				</div>
			  <%--Qui vanno messi i cinema disponibili--%>
				<c:forEach  items="${requestScope.filmspp}" var="filmpp">
          <c:set var="title" value="${filmpp.getFilm().getTitolo()}"/>
          <c:set var="title" value="${fn:replace(title,' ', '')}"/>
          <c:set var="title" value="${fn:toLowerCase(title)}"/>
          
            <div class="onefilm"> 
                <div class="row justify-content-center films">
                  <div class="col-sm-12 col-md-4">
                    <a href="film/${filmpp.getFilm().getId()}-${title}">

                      <img class="locandina" src="images${filmpp.getFilm().getUrlLocandina()}"/>
                    </a>

                  </div>
                  <div class="col-sm-12 col-md-8">
                    <a class="btn btn-outline-primary my-3" href="film/${filmpp.getFilm().getId()}-${title}">
                      <b>${filmpp.getFilm().getTitolo()}</b>
                    </a>
                    <p>Genere: ${filmpp.getGenere().getDescrizione()}</p>
                    <p>Durata: ${filmpp.getFilm().getDurata()}</p>
                    <p>${fn:substring(filmpp.getFilm().getTrama(),0,200)}..</p>

                  </div>
                </div>
            </div>
				</c:forEach>
			</div>
		  </div>
        </div>
        <jsp:include page='components/footer.jsp'/>
    </body>
</html>
