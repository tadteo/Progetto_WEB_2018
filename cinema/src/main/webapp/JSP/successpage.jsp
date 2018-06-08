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
    Document   : signUp
    Created on : Apr 20, 2018, 12:53:17 AM
    Author     : matteo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cinema Sign Up</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"  crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">

        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body class="collage">
        <div class="layer">
            <div class="container">
                <div  class="jumbotron text-center my-4 bg-light">
                    <h1 class="display-3">Sei registrato con successo!</h1>
                    <p class="lead"><strong>Ora puoi iniziare ad acquistare i biglietti per i tuoi spettacoli preferiti</strong></p>
                    <hr>
                    <p>
                        Hai difficoltà con la prenotazione? <a href="${pageContext.request.contextPath}/info">Contattaci</a>
                    </p>
                    <p class="lead">
                        <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}" role="button">Vai alla homepage</a>
                    </p>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </body>
</html>

