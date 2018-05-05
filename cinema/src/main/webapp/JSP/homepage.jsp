<%-- 
    Document   : homepage
    Created on : 23-mar-2018, 16.58.41
    Author     : Domenico Stefani - Matteo Tadiello
--%>
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
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"  crossorigin="anonymous">
		
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
					<h1>Cosa vuoi andare a vedere?</h1>
				</div>
			  <%--Qui vanno messi i cinema disponibili--%>
				<c:forEach  items="${requestScope.filmspp}" var="filmpp">
                    <div class="onefilm">
                        <div class="row justify-content-center films">
                          <div class="col-sm-12 col-md-4">
                            <form class="form-signin" action="/cinema/" method="POST">
                              <input type="hidden" name="pageRequested" value="filmpage">
                              <input type="hidden" name="film" value="${filmpp.getFilm().getId()}"> 
                              <input type="image" src="images${filmpp.getFilm().getUrlLocandina()}" class="locandina" />
                            </form>   
                          </div>
                          <div class="col-sm-12 col-md-8">
                              <form class="form-signin" action="/cinema/" method="POST">
                              <input type="hidden" name="pageRequested" value="filmpage">
                              <input type="hidden" name="film" value="${filmpp.getFilm().getId()}"> 
                              <h5><button class="astext" name="film" ><b>${filmpp.getFilm().getTitolo()}</b></button></h5>
                            </form>
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
