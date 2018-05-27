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
<%--Associo il film a quello passato via url
<%! Film film; %>
<% for( Film flm:films){
	
}%>--%>
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
		<br />
		<div class="container">
			<div class='jumbotron'>
        <p><i>Gentile utente <b>${requestScope.utente}</b></i></p>
				<p>I suoi posti ${requestScope.posti} sono stati prenotati</p>
				<p>L'acquisto dei biglietti <!--pagato ${requestScope.totalePagato}--> é andato a buon fine.</p>
        <p>I suoi biglietti sono stati inviati al suo indirizzo email, la preghiamo di stamparli e portarli con se alla proiezione.</p>
			</div>
		</div>    
		<jsp:include page='components/footer.jsp'/>

    </body>
</html>
