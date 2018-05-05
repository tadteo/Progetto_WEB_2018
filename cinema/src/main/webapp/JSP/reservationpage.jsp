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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cinema-Homepage</title>
    </head>
    <body class="collage" data-mappa="${requestScope.mappa}" data-reserved-list="${requestScope.reserved}">
        <jsp:include page='components/header.jsp'/>   
      
        <br>
        
        <div class="container jumbotron reservation">
            <h3 class="my-2 text-center">${requestScope.film.getTitolo()} - ${requestScope.sala.getDescrizione()} - Proiezione delle: <span><fmt:formatDate value="${spettacolo.getDataOra()}" pattern="HH:mm (dd/MM/yyyy)" /></span></h3>
			<h4 class="my-2 text-center">Ricordarsi di loggare prima di selezionare i posti</h4>
            <!--
            <c:forEach items="${requestScope.mappa}" var="riga">
                <p>${riga}</p>
            </c:forEach>
            -->
                
            
            <div class="container">
                <div class="row">
                  <div class="col">
                    <div id="seat-map">
                      <div class="front-indicator">Schermo</div>
                    </div>
                  </div>
                  <div class="col">
                    <div class="booking-details">
                      <h2>Dettagli</h2>
                      <h3> Posti selezionati (<span id="counter">0</span>):</h3>
                      <ul id="selected-seats">
                      </ul>
					  <c:choose>
						  <c:when test="${sessionScope.email != null}">
						  <c:set var="emailParts" value="${fn:split(sessionScope.email, '@')}" />
						<form class="form-signin" action="/cinema/" method="POST" onsubmit="setValue(this.posti)">
                            <input type="hidden" name="pageRequested" value="buypage">
							<input type="hidden" name="sala" value="${requestScope.sala.getId()}">	
							<input type="hidden" name="posti" value="">				
                            <button class="btn btn-lg btn-primary btn-block btn-outline-primary my-2" type="submit">Acquisto &raquo;</button>
						</form>
					  </c:when>
					  <c:otherwise>
						  <p> Per continuare con la prenotazione bisogna loggarsi </p>
						<form class="form-signin" action="/cinema/" method="POST" onsubmit="setValue(this.posti)">
                            <input type="hidden" name="pageRequested" value="buypage">
							<input type="hidden" name="sala" value="${requestScope.sala.getId()}">	
							<input type="hidden" name="posti" value="">				
                            <button class="btn btn-lg btn-primary btn-block btn-outline-primary my-2" type="submit" disabled>Acquisto &raquo;</button>
						</form>
					  </c:otherwise>
					  </c:choose>
					  
					  
                      
                      <div id="legend"></div>
                    </div>
                  </div>
                </div>
            </div>
        </div>
      
      
        <jsp:include page='components/footer.jsp'/>
            
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
                  category: 'Libero'
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
                  [ 'a', 'available',   'Posti disponibili' ],
//                  [ 'e', 'available',   'Economy Class'],
                  [ 'a', 'unavailable', 'Posti occupati']
                  ]
              },
              click: function () {
                if (this.status() == 'available') {
                  //let's create a new <li> which we'll add to the cart items
                  $('<li>Posto N: '+this.settings.label+' - '+this.data().category+'  <a href="#" class="cancel-cart-item">[annulla]</a></li>')
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
//			$('#selected-seats').animate({
//				scrollTop: $("#selected-seats").offsetHeight()+$("#selected-seats").offsetTop();
//		
//			});
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
			function setValue(hiddenInput){
				$("li[id^='cart-item']").each( function(){         
					hiddenInput.value += $(this).attr('id').substr(9,)+"!";
				});
			}
		</script>	
    </body>
</html>