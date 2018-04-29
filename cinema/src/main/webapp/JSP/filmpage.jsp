<%-- 
    Document   : film
    Created on : Apr 19, 2018, 6:54:49 PM
    Author     : matteo
--%>
<%@page import="it.unitn.disi.cinema.dataaccess.Beans.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
    <body style="background-image: url('../images/collage.jpg')">
        <jsp:include page='components/header.jsp'/>

    <div class="container">
		<div class="jumbotron">
			<div class="row">
                <div class="col-3">
                    <img src="images${requestScope.film.getUrlLocandina()}" alt="" style="width:100%"/>
                </div>
				<div class="col-9">
					<h2><b>${requestScope.film.getTitolo()}</b></h2>
					<p><b>Genere:</b> ${requestScope.genere.getDescrizione()}</p>
					<p><b>Durata:</b> ${requestScope.film.getDurata()} minuti</p>
					<p><b>Trama:</b> ${requestScope.film.getTrama()}</p>
                    <p><b>Spettacoli:</b></p>
                    <c:forEach items="${requestScope.spettacoli}" var="spettacolo">
                        <p>${spettacolo.getDataOra()}</p>               <!--test -->     
                    </c:forEach>
                    
					<div class="videoWrapper">
						<iframe width="420" height="315" src="${film.getUrlTrailer()}" frameborder="0" allowfullscreen></iframe>
					</div>
				</div>	
			</div>
		</div>
    </div>
    <br />
    
    
	<jsp:include page='components/footer.jsp'/>


    </body>
</html>
