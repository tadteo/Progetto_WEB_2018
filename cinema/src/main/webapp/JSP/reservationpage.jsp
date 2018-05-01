<%-- 
    Document   : prenotation
    Created on : Apr 20, 2018, 12:28:54 AM
    Author     : matteo
--%>

<%@page import="it.unitn.disi.cinema.dataaccess.Beans.Prezzo"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <body data-mappa="${requestScope.mappa}" data-reserved-list="${requestScope.reserved}">
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
            <h3 class="my-2 text-center">${requestScope.film.getTitolo()} - ${requestScope.sala.getDescrizione()} - Proiezione delle: <span><fmt:formatDate value="${spettacolo.getDataOra()}" pattern="HH:mm (dd/MM/yyyy)" /></span></h3>
              
            <!--
            <c:forEach items="${requestScope.mappa}" var="riga">
                <p>${riga}</p>
            </c:forEach>
            -->
                
            
            <div class="container">
                <div class="row">
                  <div class="col">
                    <div id="seat-map">
                      <div class="front-indicator">Front</div>
                    </div>
                  </div>
                  <div class="col">
                    <div class="booking-details">
                      <h2>Booking Details</h2>
                      <h3> Selected Seats (<span id="counter">0</span>):</h3>
                      <ul id="selected-seats">
                      </ul>
                      Total: <b>$<span id="total">0</span></b>
                      <button class="checkout-button">Checkout &raquo;</button>
                      <div id="legend"></div>
                    </div>
                  </div>
                </div>
            </div>
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
            
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
        <script src="${pageContext.request.contextPath}/JS/jquery-seat-charts.js"></script>

        <script>
          var firstSeatLabel = 1;

          $(document).ready(function() {
              
            
            //Test Domenico Stefani
            var mappa = $("body").attr("data-mappa");
            var reserved = $("body").attr("data-reserved-list");
            //alert("La mappa è " + mappa + "\nE la lista è " + reserved);  
              
            var $cart = $('#selected-seats'),
              $counter = $('#counter'),
              $total = $('#total'),
              sc = $('#seat-map').seatCharts({
//              map: [
//                'aaa_aaaa_a',
//                'aaa_aaaaaa',
//                '_aaaaaaaab',
//                'babaaaaaaa',
//                'aaaaaaaaaa',
//                'aaaaaaaaaa',
//                'aaaaaaaaaa',
//                'aaaaaaaaaa',
//              ],    
              map: mappa.split(","),
              seats: {
                a: {
                  price   : 10,
                  classes : 'first-class', //your custom CSS class
                  category: 'First Class'
                },
                // e: {
                //   price   : 40,
                //   classes : 'economy-class', //your custom CSS class
                //   category: 'Economy Class'
                // }

              },
              naming : {
                top : false,
                getLabel : function (character, row, column) {
                  return firstSeatLabel++;
                },
              },
              legend : {
                node : $('#legend'),
                  items : [
                  [ 'f', 'available',   'First Class' ],
//                  [ 'e', 'available',   'Economy Class'],
                  [ 'f', 'unavailable', 'Already Booked']
                  ]
              },
              click: function () {
                if (this.status() == 'available') {
                  //let's create a new <li> which we'll add to the cart items
                  $('<li>'+this.data().category+' Seat # '+this.settings.label+': <b>$'+this.data().price+'</b> <a href="#" class="cancel-cart-item">[cancel]</a></li>')
                    .attr('id', 'cart-item-'+this.settings.id)
                    .data('seatId', this.settings.id)
                    .appendTo($cart);

                  /*
                   * Lets up<a href="https://www.jqueryscript.net/time-clock/">date</a> the counter and total
                   *
                   * .find function will not find the current seat, because it will change its stauts only after return
                   * 'selected'. This is why we have to add 1 to the length and the current seat price to the total.
                   */
                  $counter.text(sc.find('selected').length+1);
                  $total.text(recalculateTotal(sc)+this.data().price);

                  return 'selected';
                } else if (this.status() == 'selected') {
                  //update the counter
                  $counter.text(sc.find('selected').length-1);
                  //and total
                  $total.text(recalculateTotal(sc)-this.data().price);

                  //remove the item from our cart
                  $('#cart-item-'+this.settings.id).remove();

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
            

            //this will handle "[cancel]" link clicks
            $('#selected-seats').on('click', '.cancel-cart-item', function () {
              //let's just trigger Click event on the appropriate seat, so we don't have to repeat the logic here
              sc.get($(this).parents('li:first').data('seatId')).click();
            });

            //let's pretend some seats have already been booked //Modificato Domenico Stefani
            var reserved_split = reserved.split(",");
            sc.get(reserved_split).status('unavailable');
            
            
          });

          

          function recalculateTotal(sc) {
          var total = 0;

          //basically find every selected seat and sum its price
          sc.find('selected').each(function () {
            total += this.data().price;
          });

          return total;
          }
        </script>

        <script>
          // requires <a href="https://www.jqueryscript.net/tags.php?/jQuery UI/">jQuery UI</a>
          animate : false,

          // specify your own column and row labels as well as functions for generating seat ids and labels
          naming  : {
          top    : true,
          left   : true,
          getId  : function(character, row, column) {
          return row + '_' + column;
          },
          getLabel : function (character, row, column) {
          return column;
          }

          },

          // custom legend
          legend : {
          node   : null,
          items  : []
          },

          // click function
          click   : function() {

          if (this.status() == 'available') {
          return 'selected';
          } else if (this.status() == 'selected') {
          return 'available';
          } else {
          return this.style();
          }

          },

          // focus function
          focus  : function() {

          if (this.status() == 'available') {
          return 'focused';
          } else  {
          return this.style();
          }
          },

          // blur function
          blur   : function() {
          return this.status();
          },

          // seat map definition
          seats   : {}


        </script>

        <script>
//          var sc = $('#sc-container').seatCharts({
//            //...
//          });
//
//          //get 2_3 seat
//          sc.get('2_3');
//
//          //get 2_3 and 2_4 seats
//          sc.get(['2_3', '2_4']);
//
//          //find all a seats
//          sc.find('a');
//
//          //find all unavailable seats
//          sc.find('unavailable');
//
//          //find all available a seats
//          sc.find('a.available');
//
//          //find all seats in the first row
//          sc.find(/^1_[0-9]+/);
//
//          //find available seats within specified seat ids
//          sc.get(['1_2', '1_3', '1_4']).find('available');
//
//          //set status for one seat
//          sc.status('2_15', 'unvailable');
//
//          //set status for two seats
//          sc.status(['2_15', '2_10'], 'unvailable');
//
//          //make all unvailable seats available
//          sc.find('unavailable').status('available');
//
//          //make all unavailable seats disappear
//          sc.find('unavailable').node().fadeOut('fast');
//
//          //with callback
//          sc.find('a.unavailable').each(function(seatId) {
//            console.log(this.data()); //display seat data
//          });
//
//          //If status argument is set, it will be used as a new seat status, otherwise current status will be returned.
//          sc.status( [status] )
//
//          //Returns a reference to jQuery element.
//          sc.node( )
//
//          //Returns a reference to seat data.
//          sc.data( )
//
//          //Returns seat character.
//          sc.char( )
        </script>
    </body>
</html>