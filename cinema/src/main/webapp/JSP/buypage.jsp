<%-- 
    Document   : bypage
    Created on : May 3, 2018, 3:00:45 PM
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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/yesOrNo.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
			<script src="${pageContext.request.contextPath}/JS/jquery.payments.js"></script>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/payments.css">
			<script type="text/javascript">$(document).ready(function() {$('#creditcardform').paymentForm();});</script>

        <title>Cinema-Homepage</title>
    </head>
    <body class="collage">
        <jsp:include page='components/header.jsp'/>

        <br>
        <div class="container justify-content-center">
          <div class="jumbotron">
			  <h3>Le poltrone selezionate sono:</h3>
			  <ul>
					<c:forEach items="${requestScope.posti}" var="posto">
						<li><b>Poltrona N:${posto.getPoltrona()} alla riga N:${posto.getRiga()}</b> - E' per un cliente con tariffa ridotta? 
							<form class="form-inline">
								<div class="form-group switch-field">
								  <input type="radio" id="switch_left_${posto.getRiga()}${posto.getPoltrona()}" name="switch_${posto.getRiga()}${posto.getPoltrona()}" value="no" checked onclick="setTotale(); setPrezzo(${posto.getRiga()},${posto.getPoltrona()});"/>
								  <label for="switch_left_${posto.getRiga()}${posto.getPoltrona()}">NO</label>
								  <input type="radio" id="switch_right_${posto.getRiga()}${posto.getPoltrona()}" name="switch_${posto.getRiga()}${posto.getPoltrona()}" value="yes"  onclick="setTotale(); setPrezzo(${posto.getRiga()},${posto.getPoltrona()})" />
								  <label for="switch_right_${posto.getRiga()}${posto.getPoltrona()}">SI</label>
								</div>
								<div class="form-group">
									<p>Prezzo: <b id="prezzo_${posto.getRiga()}${posto.getPoltrona()}">10</b> euro</p>
								</div>
							</form>
						</li>
					</c:forEach>
			  </ul>

<br /><br />





			  <div class="totale">
				  <p>Il totale e': <b id="prezzoTotale"></b></p> 
				  <form class="form-signin" action="/cinema/" method="POST">
							<input type="hidden" name="posti" value="">				
							<input type="hidden" name="posti" value="">				
                            <button class="btn btn-lg btn-primary btn-block btn-outline-primary my-2" type="submit">Acquisto &raquo;</button>
                  </form>
			  </div>

		  </div>
		</div>
		<jsp:include page='components/footer.jsp'/>
		
		<script>
			function setTotale(){
				var totale = 0;
				$("[id^='switch_']").each( function(){
					if( this.checked ){
						if(this.value=="yes"){
							totale+=7;
						}else{
							totale+=10;
						}
					}
				});
				document.getElementById("prezzoTotale").innerHTML= totale+" euro";
			}
			setTotale();
			
			function setPrezzo(a,b){
				if(document.getElementById("switch_left_"+a+b).checked ){
					document.getElementById("prezzo_"+a+b).innerHTML="10";
				}else{
					document.getElementById("prezzo_"+a+b).innerHTML= "7";
				}
			}
		</script>
    </body>
</html>

