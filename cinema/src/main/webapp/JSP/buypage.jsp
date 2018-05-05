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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/payments.css">

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
<div class="creditCardForm">
            <div class="heading">
                <h4>Conferma il pagamento</h4>
				<h5>Inserisci i dati della carta</h5>
            </div>
	<br />
            <div class="payment">
                <form>
                    <div class="form-group owner">
                        <label for="owner">Proprietario</label>
                        <input type="text" class="form-control" id="owner">
                    </div>
                    <div class="form-group CVV">
                        <label for="cvv">CVV</label>
                        <input type="text" class="form-control" id="cvv">
                    </div>
                    <div class="form-group" id="card-number-field">
                        <label for="cardNumber">Numero di carta</label>
                        <input type="text" class="form-control" id="cardNumber">
                    </div>
                    <div class="form-group" id="expiration-date">
                        <label>Data di scadenza</label>
                        <select>
                            <option value="01">Gennaio</option>
                            <option value="02">Febbraio </option>
                            <option value="03">Marzo</option>
                            <option value="04">Aprile</option>
                            <option value="05">Maggio</option>
                            <option value="06">Giugno</option>
                            <option value="07">Luglio</option>
                            <option value="08">Agosto</option>
                            <option value="09">Settembre</option>
                            <option value="10">Ottobre</option>
                            <option value="11">Novembre</option>
                            <option value="12">Dicembre</option>
                        </select>
                        <select>
                            <option value="16"> 2018</option>
                            <option value="17"> 2019</option>
                            <option value="18"> 2020</option>
                            <option value="19"> 2021</option>
                            <option value="20"> 2022</option>
                            <option value="21"> 2023</option>
                        </select>
                    </div>
                    <div class="form-group" id="credit_cards">
                        <img src="${pageContext.request.contextPath}/images/cards/visa.jpg" id="visa">
                        <img src="${pageContext.request.contextPath}/images/cards/mastercard.jpg" id="mastercard">
                        <img src="${pageContext.request.contextPath}/images/cards/amex.jpg" id="amex">
                    </div>
                    <div class="form-group" id="pay-now">
                        <button type="submit" class="btn btn-default" id="confirm-purchase">Confirm</button>
                    </div>
                </form>
            </div>
        </div>





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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/JS/jquery.payform.min.js" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/JS/script.js"></script>
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

