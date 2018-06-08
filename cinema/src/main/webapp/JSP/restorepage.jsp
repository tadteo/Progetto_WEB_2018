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
      <div class="container my-2">
        <div class="row">
          <div class="col-md-3"></div>
          <div class="col-12 col-md-6">
            <div class="jumbotron">
              <form class="form-signin text-center" action="/cinema/restore.do" method="POST">
                <h2 class="form-signin-heading">Inserisci il tuo indirizzo email</h2>
                <br/>
                <span class="my-2" style="color: red">${sessionScope.restoreErrorMessage}</span><br/>
                <label class="my-2" for="email">Indirizzo e-mail</label>
                <input type="email" id="email" name="email" class="form-control my-2" placeholder="Email" required autofocus>

                <button class="btn btn-lg btn-primary my-2" type="submit">Recupera la password</button>
              </form>
            </div>
          </div>
          <div class="col-md-3"></div>
        </div>
      </div> <!-- /container0 -->
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
  </body>
</html>

