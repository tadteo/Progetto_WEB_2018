<%--
 * Cinema Universe - Reservation System
 * Copyright (C) 2018 Domenico Stefani, Ivan Martini, Matteo Tadiello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See <http://www.gnu.org/licenses/>.
--%>
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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"  crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/yesOrNo.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/payments.css">
        
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Cinema-Homepage</title>
    </head>
    <body class="collage">

        <script>
            function setPrezzo(a, b) {
                var prezzo_intero = parseFloat($("#pass_dati").attr("intero"));
                var prezzo_ridotto = parseFloat($("#pass_dati").attr("ridotto"));

                if (document.getElementById("switch_left_" + a + b).checked) {
                    document.getElementById("prezzo_" + a + b).innerHTML = prezzo_intero.toFixed(2);
                } else {
                    document.getElementById("prezzo_" + a + b).innerHTML = prezzo_ridotto.toFixed(2);
                }
            }
        </script>

        <div id="pass_dati" intero="${requestScope.intero.getPrezzo()}" ridotto="${requestScope.ridotto.getPrezzo()}" id-intero="${requestScope.intero.getId()}" id-ridotto="${requestScope.ridotto.getId()}" />

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
                                    <input type="radio" posto_id="${posto.getId()}" id="switch_left_${posto.getRiga()}${posto.getPoltrona()}" name="switch_${posto.getRiga()}${posto.getPoltrona()}" value="no" checked onclick="setTotale();
                                            setPrezzo(${posto.getRiga()},${posto.getPoltrona()});"/>
                                    <label for="switch_left_${posto.getRiga()}${posto.getPoltrona()}">NO</label>
                                    <input type="radio" posto_id="${posto.getId()}" id="switch_right_${posto.getRiga()}${posto.getPoltrona()}" name="switch_${posto.getRiga()}${posto.getPoltrona()}" value="yes"  onclick="setTotale();
                                            setPrezzo(${posto.getRiga()},${posto.getPoltrona()})"/>
                                    <label for="switch_right_${posto.getRiga()}${posto.getPoltrona()}">SI</label>
                                </div>
                                <div class="form-group" onclick="alert('cisono')">
                                    <p>Prezzo: <b id="prezzo_${posto.getRiga()}${posto.getPoltrona()}">${requestScope.intero.getPrezzo()}</b> euro</p>
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

                <c:set var="listaPosti" value=""></c:set>
                <c:forEach var="posto" items="${requestScope.posti}">
                    <c:set var="listaPosti" value="${listaPosti} ${posto.getId()}"></c:set>
                </c:forEach>

                <div class="totale">
                    <p>Il totale e': <b id="prezzoTotale"></b></p> 
                    <form class="form-signin" action="${pageContext.request.contextPath}/acquistabiglietti/conferma" method="POST" onsubmit="setTotalePagato(this.totalePagato, this.prezzi)">
                        <input type="hidden" name="pageRequested" value="confirmationpage">
                        <input type="hidden" name="utente" value="${sessionScope.email}">				
                        <input type="hidden" name="posti" value="${listaPosti}">			
                        <input type='hidden' name='prezzi' value="">   <!-- Questi prezzi sono settti dalla funzione setTotalePaato-->
                        <input type="hidden" name="spettacolo" value="${requestScope.spettacolo}">
                        <input type='hidden' name='totalePagato' value="">         
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

                        function setTotale() {
                            var prezzo_intero = parseFloat($("#pass_dati").attr("intero"));
                            var prezzo_ridotto = parseFloat($("#pass_dati").attr("ridotto"));//CAZZO NO COSI' DA CAMBIARE ASSOLUTAMENTE; SERVONO I PREZZI DAL DB

                            var totale = 0;
                            $("[id^='switch_']").each(function () {
                                if (this.checked) {
                                    if (this.value === "yes") {
                                        totale += prezzo_ridotto;
                                    } else {
                                        totale += prezzo_intero;
                                    }
                                }
                            });
                            document.getElementById("prezzoTotale").innerHTML = totale + " euro";
                            return totale;
                        }

                        setTotale();
                        function setTotalePagato(tot, price_string) {
                            /*
                             * Questa Funzione controlla tutti i checkbox per sommare i prezzi dei biglietti, allo stesso tempo
                             * crea una stringa che contiene elementi del tipo id_posto_id_prezzo separati da spazi.
                             * Questo permette alla servlet in risposta di processare correttamente i dati e aggiugnere le prenotazioni
                             * al database
                             * 
                             */
                            var id_prezzo_intero = parseFloat($("#pass_dati").attr("id-intero"));
                            var id_prezzo_ridotto = parseFloat($("#pass_dati").attr("id-ridotto"));
                            var prezzo_intero = parseFloat($("#pass_dati").attr("intero"));
                            var prezzo_ridotto = parseFloat($("#pass_dati").attr("ridotto"));

                            var totale = 0;
                            var prezzi_string = "";

                            $("[id^='switch_']").each(function () {


                                if (this.checked) {
                                    if (this.value === "yes") {
                                        totale += prezzo_ridotto;
                                        prezzi_string += " " + $(this).attr("posto_id") + "_" + id_prezzo_ridotto;
                                    } else {
                                        totale += prezzo_intero;
                                        prezzi_string += " " + $(this).attr("posto_id") + "_" + id_prezzo_intero;
                                    }
                                }
                            });
                            tot.value = totale + " euro";
                            price_string.value = prezzi_string;
                        }


        </script>
    </body>
</html>

