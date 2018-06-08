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
    Document   : admin
    Author     : ivan
--%>
<%@page import="it.unitn.disi.cinema.dataaccess.Beans.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@page import="java.util.StringTokenizer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"  crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Zona Admin - prenotazioni</title>
  </head>
  <jsp:include page='components/header.jsp'/>
  <c:set var="spettacolo" value="${requestScope.spettacolo}" />
  <c:set var="film" value="${requestScope.film}" />
  <c:set var="sala" value="${requestScope.sala}" />


  <body class="collage">
    <div class="card margin-admin">
      <div class="card-body">
        GESTIONE DELLE PRENOTAZIONI --- <b>Spettacolo:</b> ${spettacolo.getId()} - <b>Film:</b> ${film.getTitolo()} <b>(${sala.getDescrizione()})</b>
      </div>
    </div>
    <div class="card margin-admin">
      <div class="card-body">
        <table class="table  table-striped">
          <thead class="thead-dark">
            <tr>
              <th scope="col">ID prenotazione</th>
              <th scope="col">ID utente</th>
              <th scope="col">Email</th>
              <th scope="col">Posto</th>
              <th scope="col">Cancella</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${requestScope.prenotazione}" var="pren">

              <c:set var="utente" value="utenteID${pren.getId()}" />
              <c:set var="mail" value="utenteEm${pren.getId()}" />
              <c:set var="posto" value="posto${pren.getId()}" />

              <tr>
                <td>${pren.getId()}</td>
                <td>${requestScope[utente]}</td>
                <td>${requestScope[mail]}</td>  
                <td>${requestScope[posto]}</td>
                <td><form action="${pageContext.request.contextPath}/prenotazione/rimozione" method="post">
                    <input type="hidden" name="delete" value="${pren.getId()}">
                    <input type="hidden" name="redirect" value="${spettacolo.getId()}">
                    <button type="submit" class="btn btn-outline-danger" onclick="">Cancella</button>
                  </form></td>

              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </body>
  <jsp:include page='components/footer.jsp' />
</html>