<%-- 
    Document   : film
    Created on : Apr 19, 2018, 6:54:49 PM
    Author     : matteo
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

        <title>Film</title>
    </head>
    <body>
		<div class="header container">
			<div class="row">
				<div class="col-6">
					<h1 class="text-center"><b>Cinema (homepage)</b></h1> 
				</div>
				<div class="col-6">
					<c:choose>
						<c:when test="${sessionScope.email != null}">
							<c:set var="emailParts" value="${fn:split(sessionScope.email, '@')}" />
							<h3>Welcome ${emailParts[0]} <b>(${sessionScope.ruolo})</b></h3>
							<h4>You are logged in</h4>

							<form class="form-signin" action="/cinema/logout.do" method="POST">
								<button class="btn btn-lg btn-primary btn-block" type="submit">Logout</button>
							</form>
						</c:when>
						<c:otherwise>
							<form class="form-signin" action="/cinema/login.do" method="GET">
								<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
						</c:otherwise>      
					</c:choose>
				</div>
			</div>
		</div>
		
		<div>
			<h2><b>${film.getTitolo()}</b></h2>
			<p><b>Genere:</b> ${film.getGenere()}</p>
			<p><b>Durata:</b> ${film.getDurata()} </p>
			<p><b>Trama:</b> ${film.getTrama()}</p>
			<iframe class="trailer" src="${film.getTrailer()}">
			</iframe>
		</div>
		

    </body>
</html>
