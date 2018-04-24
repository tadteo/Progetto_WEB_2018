<%-- 
    Document   : prenotation
    Created on : Apr 20, 2018, 12:28:54 AM
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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/jquery-seat-charts.css">
        <title>Cinema-Homepage</title>
    </head>
    <body>
      <div class="header container">
        <div class="row">
          <div class="col-6">
            <h1 class="text-center"><b>Cinema Universe (homepage)</b></h1>
			<form class="form-signin text-center" action="/cinema/" method="POST">
				<button class="astext"name="pageRequested" value="infopage"><h3>Info cinema</h3></button>
			</form>
          </div>
          <div class="col-6 login">
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
                  <button class="btn btn-lg btn-primary btn-block" type="submit">Login/Sign up</button>
                </form>
              </c:otherwise>      
            </c:choose>
          </div>
        </div>
      </div>
      <br>
        <div class="wrapper">
			<div class="container">             
                <div id="seat-map">
					<div class="front-indicator">Front</div>
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
	  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
	  <script src="${pageContext.request.contextPath}/CSS/jquery-seat-charts.js"></script>

	  <script>
			$(document).ready(function() {

			var sc = $('#seat-map').seatCharts({
				map: [
					'aaaaaaaaaaaa',
					'aaaaaaaaaaaa',
					'bbbbbbbbbb__',
					'bbbbbbbbbb__',
					'bbbbbbbbbbbb',
					'cccccccccccc'
				],
				seats: {
					a: {
						price   : 99.99,
						classes : 'front-seat' //your custom CSS class
					}

				},
				click: function () {
					if (this.status() == 'available') {
						//do some stuff, i.e. add to the cart
						return 'selected';
					} else if (this.status() == 'selected') {
						//seat has been vacated
						return 'available';
					} else if (this.status() == 'unavailable') {
						//seat has been already booked
						return 'unavailable';
					} else {
						return this.style();
					}
				}
			});

			//Make all available 'c' seats unavailable
			sc.find('c.available').status('unavailable');

			/*
			Get seats with ids 2_6, 1_7 (more on ids later on),
			put them in a jQuery set and change some css
			*/
			sc.get(['2_6', '1_7']).node().css({
				color: '#ffcfcf'
			});

			console.log('Seat 1_2 costs ' + sc.get('1_2').data().price + ' and is currently ' + sc.status('1_2'));

		});
		
		</script>
    </body>
</html>