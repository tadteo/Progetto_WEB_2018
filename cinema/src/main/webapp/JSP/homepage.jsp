<%-- 
    Document   : homepage
    Created on : 23-mar-2018, 16.58.41
    Author     : domenico
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
        <title>Cinema-Homepage</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <div class="container">
            <div class="jumbotron">
                <h1 class="text-center"><b>Cinema (homepage)</b></h1>               
                
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
                
                <br/>
                <br/>
                <br/>
                
                
                <h2>TEST ZONE </h2>
                <div class="container">
                    <div class="row">
                        <div class="col-sm" >
                            <h6>Prezzi:</h6>
                            <table class="table" style="background-color: white; border-radius: 1rem;">
                                <tr>
                                    <th>Descrizione</th>
                                    <th>Importo</th>
                                </tr>
                                <c:forEach items="${requestScope.prezzi}" var="prezzo">
                                    <tr>
                                        <td>${prezzo.getTipo()}</td>
                                        <td>${prezzo.getPrezzo()} <b>€</b></td>
                                    </tr>
                                </c:forEach>


                            </table>
                        </div>
                        <div class="col-sm">
                            
                            <h6>Generi:</h6>
                            <table class="table" style="background-color: white; border-radius: 1rem;">
                                <tr>
                                    <th>Descrizione</th>
                                </tr>
                                <c:forEach  items="${requestScope.generi}" var="genere">
                                    <tr>
                                        <td>${genere.getDescrizione()}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                  </div>
                
                
                
                <br/>
                <br/>
                <br/>
                
                
                
                <div id="movies">
                    <!--Qui vanno messi i cinema disponibili-->
                    <table class="table">
                        <tr>
                            <th>#</th>
                            <th></th>
                            <th>Titolo</th> 
                            <th>Genere</th>
                            <th>Trailer</th>
                            <th>Durata (minuti)</th>
                            <th>Trama</th>
                        </tr>
                    <c:forEach  items="${requestScope.films}" var="film">
                        
                            <tr>
                                <th>${film.getId()}</th>
                                <td><img src="images${film.getUrlLocandina()}" style="max-width:10rem"/></td>
                                <td>${film.getTitolo()}</td> 
                                <td>    
                                    <!--E' importante non accedere al db dalle views quindi, per trovare il nome del genere
                                        facciamo un ciclo sui generi nel db che sono pochi (6)
                                        Non so se ci sia una soluzione migliore, forse chiedere il dato ad una nuova servlet
                                        che ha il solo scopo di interrogare il db per ottenere il genere-->
                                     <c:forEach  items="${requestScope.generi}" var="genere">
                                        <c:choose>
                                            <c:when test="${genere.getId() == film.getGenereId()}">
                                                
                                                ${genere.getDescrizione()}

                                            </c:when>     
                                        </c:choose>
                                     </c:forEach>
                                </td>
                                <td><a href="${film.getUrlTrailer()}">Youtube</a></td>
                                <td>${film.getDurata()}</td>
                                <td style="max-width: 18rem">${fn:substring(film.getTrama(), 0, 150)} ...</td>
                            </tr>
                    </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
