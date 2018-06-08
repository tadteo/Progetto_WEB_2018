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
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"  crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cinema-Modifica Sala</title>
  </head>
  <body class="collage" data-mappa="${requestScope.mappa}" data-reserved-list="${requestScope.reserved}">
    <jsp:include page='components/header.jsp'/>   

    <div class="container jumbotron reservation my-4">
      <h3 class="my-4 text-center">Sala: ${requestScope.sala.getDescrizione()} </h3>
        <div class="container">
          <div class="row">
            <div class="col-6">
              <div class="row">
                
              <c:set var="lastRow" value="0"/>
              <c:set var="firstId" value="${posti.get(0).getId()}"/>
              <c:forEach items="${posti}" var="posto">
                <c:choose>
                  <c:when test="${posto.getRiga() != lastRow}">
                    </div>
                    <div class="row">
                  </c:when>
                </c:choose>
                    
                <div class="col-1 padding-0 marginbtn">
                <c:choose>
                  <c:when test="${posto.getEsiste() == true}">
                     <form action="/cinema/admin/modificasala/remove/${posto.getId()}">
                  </c:when>
                  <c:otherwise>
                     <form action="/cinema/admin/modificasala/add/${posto.getId()}">
                  </c:otherwise>
                </c:choose>
                     
                  <c:set var="pst_id" value="${posto.getId() - firstId + 1}"/>
                  <c:choose>
                    <c:when test="${pst_id < 10}">
                      <c:set var="pst_id" value="${pst_id}"/>
                    </c:when>
                  </c:choose>

                  <c:choose>
                    <c:when test="${pst_id < 10}">
                      <c:set var="space" value="0" />   
                    </c:when>
                    <c:otherwise>
                      <c:set var="space" value="" />   
                    </c:otherwise>
                  </c:choose>

                  <c:choose>
                    <c:when test="${posto.getEsiste() == true}">
                       <button type="submit" class="btn exists" disabled>${space}${pst_id}</button>
                    </c:when>
                    <c:otherwise>
                       <button type="submit" class="btn notexists" disabled>${space}${pst_id}</button>
                    </c:otherwise>
                  </c:choose>
              </form>
              </div>     
              <c:set var="lastRow" value="${posto.getRiga()}"/>
          </c:forEach>
              </div>
            </div>
            <div class="col-6">
              <h2>Dettagli</h2>
              <div class="row my-2">
                <button class="btn btn-success ml-2" onclick="abilitaNonEsistenti()">Aggiungi</button>
                <button class="btn btn-danger ml-2"  onclick="abilitaEsistenti()">Rimuovi</button>
              </div>
              <div class="row my-2">
                <button id="btn-annulla" class="btn ml-2"  onclick="annulla()" disabled>Annulla</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      

    <jsp:include page='components/footer.jsp'/>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <script>
      
      function abilitaNonEsistenti(){
        $("#btn-annulla").prop("disabled", false); 
        $(".exists").prop("disabled", true); 
        $(".exists").removeClass("btn-danger");
        
        $(".notexists").prop("disabled", false); 
        $(".notexists").addClass("btn-success"); 
      }
      
      function abilitaEsistenti(){
        $("#btn-annulla").prop("disabled", false); 
        $(".notexists").prop("disabled", true); 
        $(".notexists").removeClass("btn-success");
        
        $(".exists").prop("disabled", false); 
        $(".exists").addClass("btn-danger"); 
      }
      
      function annulla(){
        $("#btn-annulla").prop("disabled", true); 
        
        $(".notexists").prop("disabled", true); 
        $(".notexists").removeClass("btn-success");
        
        $(".exists").prop("disabled", true); 
        $(".exists").removeClass("btn-danger"); 
      }
      
      
    </script>
    
  </body>
</html>