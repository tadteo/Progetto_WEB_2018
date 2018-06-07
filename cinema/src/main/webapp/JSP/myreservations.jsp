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

        <title>Prenotazioni effettuate</title>
    </head>
	
    <body class="collage">
        <jsp:include page='components/header.jsp'/>
        
        <div class="card margin-admin">
          <div class="card-body">
            <h3>PRENOTAZIONI EFFETTUATE</h3>
          </div>
        </div>
        <div class="card my-4">
          <div class="card-body">
            <table id="clientitable" class="table  table-striped">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Film</th>
                  <th scope="col">Posto</th>
                  <th scope="col">Ora Spettacolo</th>
                  <th scope="col">Ora Prenotazione</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${requestScope.packprenotazioni}" var="packprenotazione">
                  
                  <tr>
                    <td scope="row">${packprenotazione.getPrenotazione()}</td>
                    <td>${packprenotazione.getTitoloFilm()}</td>
                    <td>${packprenotazione.getPosto()}</td>
                    <td>${packprenotazione.getOraSpettacolo()}</td>
                    <td>${packprenotazione.getOraPrenotazione()}</td>
                  </tr>

                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>

        <jsp:include page='components/footer.jsp'/>
    
  
  </body>
</html>

