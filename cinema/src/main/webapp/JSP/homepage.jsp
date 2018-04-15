<%-- 
    Document   : homepage
    Created on : 23-mar-2018, 16.58.41
    Author     : domenico
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@ page import="java.util.StringTokenizer" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" crossorigin="anonymous">

        <title>Cinema-Homepage</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <div class="container">
            <div class="jumbotron">
                <h1 class="text-center display-1"><b>Cinema (homepage)</b></h1>

                <% //Commento// Si può fare senza usare la scriplet? con jstl?
                    String email = (String) session.getAttribute("email"); 
                    if (email != null){  
//                        StringTokenizer st = new StringTokenizer(searchFilter, "@");
                        out.println("<h3>Welcome <b>" + email.split("[@]")[0] + "</b></h3><br>");
                %>
                    <h4>You are logged in</h4>
                    
                        <form class="form-signin" action="/cinema/logout.do" method="POST">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Logout</button>
                    </form>
                <%
                    }else{
                        %>
                            <!--<a href="/cinema/login.do">Login</a>-->
                            <form class="form-signin" action="/cinema/login.do" method="GET">
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
                        <%
                    }
                %>
                
                <br/>
                
                <div id="movies">
                    <!--Qui vanno messi i cinema disponibili-->
                    <table class="table">
                        <tr>
                            <th>#</th>
                            <th></th>
                            <th>Titolo</th> 
                            <th>Trailer</th>
                            <th>Durata (minuti)</th>
                            <th>Trama</th>
                        </tr>
                    <c:forEach  items="${requestScope.films}" var="film">
                        
                            <tr>
                                <th>${film.getId()}</th>
                                <td><img src="images${film.getUrlLocandina()}" style="max-width:10rem"/></td>
                                <td>${film.getTitolo()}</td> 
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
