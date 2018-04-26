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
				<button class="astext" name="pageRequested" value="infopage"><h3>Info cinema</h3></button>
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
        
        
        <div class="container jumbotron">
            <h3>${requestScope.sala.getDescrizione()}</h3>
            <h3>${requestScope.film.getTitolo()}</h3>
                
            <c:forEach items="mappa" var="riga">
                <p>${riga}</p>
            </c:forEach>
                
            
            
            
            
            
            
<!--            <div class="demo">
                <div id="seat-map">
                    <div class="front">SCHERMO</div>					
                </div>
                <div class="booking-details">
                    <p>Movie: <span>${requestScope.film.getTitolo()}</span></p>
                    <p>Time: <span>${requestScope.spettacolo.getDataOra()}</span></p>
                    <p>Seat: </p>
                    <ul id="selected-seats"></ul>
                    <p>Tickets: <span id="counter">0</span></p>
                    <p>Total: <b>$<span id="total">0</span></b></p>

                    <button class="checkout-button">BUY</button>

                    <div id="legend"></div>
                </div>
                <div style="clear:both"></div>
            </div>-->
                
                
                
    <!--            <div class="wrapper">
                    <div class="container">             
                        <div id="seat-map">
                            <div class="front-indicator">Front</div>
                        </div>
                    </div>
                </div>-->
        </div>
      
      
      
	  <footer class="container">
				<div class="row">
					<div class="col-lg-6 col-md-12">
						<p><a href="JSP/infopage.jsp">Info:</a></p>
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
			var price = 10; //price
            $(document).ready(function() {
            var $cart = $('#selected-seats'), //Sitting Area
            $counter = $('#counter'), //Votes
            $total = $('#total'); //Total money

            var sc = $('#seat-map').seatCharts({
                map: [  //Seating chart
                    'aaaaaaaaaa',
                    'aaaaaaaaaa',
                    '__________',
                    'aaaaaaaa__',
                    'aaaaaaaaaa',
                    'aaaaaaaaaa',
                    'aaaaaaaaaa',
                    'aaaaaaaaaa',
                    'aaaaaaaaaa',
                    'aa__aa__aa'
                ],
                naming : {
                    top : false,
                    getLabel : function (character, row, column) {
                        return column;
                    }
                },
                legend : { //Definition legend
                    node : $('#legend'),
                    items : [
                        [ 'a', 'available',   'Option' ],
                        [ 'a', 'unavailable', 'Sold']
                    ]					
                },
                click: function () { //Click event
                    if (this.status() == 'available') { //optional seat
                        $('<li>R'+(this.settings.row+1)+' S'+this.settings.label+'</li>')
                            .attr('id', 'cart-item-'+this.settings.id)
                            .data('seatId', this.settings.id)
                            .appendTo($cart);

                        $counter.text(sc.find('selected').length+1);
                        $total.text(recalculateTotal(sc)+price);

                        return 'selected';
                    } else if (this.status() == 'selected') { //Checked
                            //Update Number
                            $counter.text(sc.find('selected').length-1);
                            //update totalnum
                            $total.text(recalculateTotal(sc)-price);

                            //Delete reservation
                            $('#cart-item-'+this.settings.id).remove();
                            //optional
                            return 'available';
                    } else if (this.status() == 'unavailable') { //sold
                        return 'unavailable';
                    } else {
                        return this.style();
                    }
                }
            });
        //sold seat
        sc.get(['1_2', '4_4','4_5','6_6','6_7','8_5','8_6','8_7','8_8', '10_1', '10_2']).status('unavailable');

    });
    //sum total money
    function recalculateTotal(sc) {
        var total = 0;
        sc.find('selected').each(function () {
            total += price;
        });

        return total;
    }

		});
		
		</script>
    </body>
</html>