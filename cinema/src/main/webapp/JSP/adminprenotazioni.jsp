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

  <body class="collage">
    <div class="card margin-admin">
      <div class="card-body overflow-card">
        GESTIONE DELLE PRENOTAZIONI
      </div>
    </div>
    <div class="card margin-admin">
      <div class="card-body">
        <table class="table  table-striped">
          <thead class="thead-dark">
            <tr>
              <th scope="col">ID spettacolo</th>
              <th scope="col">Spettacolo</th>
              <th scope="col">Sala</th>
              <th scope="col">Posti totali</th>
              <th scope="col">Posti occupati</th>
              <th scope="col">Posti disponibili</th>
              <th scope="col">Incasso Totale</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${requestScope.spettacolo}" var="spettacoloo">

              <c:set var="film" value="film${spettacoloo.getId()}" />
              <c:set var="postiTotali" value="postiTotali${spettacoloo.getId()}" />
              <c:set var="postiOccupati" value="postiOccupati${spettacoloo.getId()}" />
              <c:set var="incasso" value="incasso${spettacoloo.getId()}" />

              <c:set var="postiLiberi" value="${requestScope[postiTotali]-requestScope[postiOccupati]}" />

              <tr>
                <td>${spettacoloo.getId()}</td>
                <td><a href="./${spettacoloo.getId()}">${requestScope[film]} - (${spettacoloo.getDataOra()})</a></td> 
                <td>${spettacoloo.getSalaId()}</td>
                <td>${requestScope[postiTotali]}</td>
                <td>${requestScope[postiOccupati]}</td>
                <td>${postiLiberi}</td>
                <td>${requestScope[incasso]}</td>


              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

  </body>
  <jsp:include page='components/footer.jsp'/>
</html>
