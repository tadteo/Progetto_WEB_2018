<%-- 
    Document   : info
    Created on : Apr 20, 2018, 12:29:28 AM
    Author     : matteo
--%>

<%@page import="it.unitn.disi.cinema.dataaccess.Beans.Prezzo"%>
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

        <title>Info page</title>
    </head>
	
    <body>
		<br />
    <div class="header container">
        <div class="row">
            <div class="col-6">
				<form class="form-signin text-center" action="/cinema/" method="GET">
					<button class="astext"><h1>Cinema Universe</h1></button>
				</form>
				<h3 class="text-center">(info page)</h3>
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
	<br />
    <div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-12 ">
				<h3>Costi:</h3>
				<p><b>Telefono:</b> +39 0123 123123</p>
				<p><b>Indirizzo:</b> Via La Vita E Tutto Quanto, 42 (UNIVERSO)</p>
			</div>
			<div class="col-lg-6 col-md-12 ">
				<div id="map"></div>
			</div>
		</div>
        
    </div>
	<footer class="container">
				<div class="row">
					<div class="col-lg-6 col-md-12">
						<p><a href="${pageContext.request.contextPath}/JSP/infopage.jsp">Info:</a></p>
						<p><b>Telefono:</b> +39 0123 123123</p>
						<p><b>Indirizzo:</b> Via La Vita E Tutto Quanto, 42 (UNIVERSO)</p>
						<p><b>Partita Iva: </b>01234561001<b> – C.F. </b>01234561001</p>

					</div>
					<div class="col-lg-6 col-md-12">
						<br />
						<p>Posted by: Magic Group Srl</p>
						<p>Contact information: <a href="mailto:info@magicgroup.com">info@magicgroup.com</a>.</p>
					</div>
						
				</div>
				<p class="copyright">Copyright © 2018 · Tadiello Matteo - Stefani Domenico - Martini Ivan · all rights reserved.</p>
			</footer>
		
	<script>
      function initMap() {
        var olimpo = {lat: 40.088, lng: 22.358};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 6,
          center: olimpo
        });
        var marker = new google.maps.Marker({
          position: olimpo,
          map: map
        });
      }
    </script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCPY7IgJv-v0U6cYTLlr2WCjeq4m6CSbG8&callback=initMap"></script>
    </body>
</html>
