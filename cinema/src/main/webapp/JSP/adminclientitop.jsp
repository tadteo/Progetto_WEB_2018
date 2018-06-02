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

        <title>Zona Admin - clienti top</title>
    </head>
	
    <body class="collage">
        <jsp:include page='components/header.jsp'/>
        
        <div class="card margin-admin">
          <div class="card-body">
            LISTA DEI CLIENTI TOP
          </div>
        </div>
        <div class="card margin-admin">
          <div class="card-body">
            <table class="table  table-striped">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Utente</th>
                  <th scope="col">Prenotazioni Effettuate</th>
                  <th scope="col">Soldi spesi</th>
                </tr>
              </thead>
              <tbody>
                <c:set var="count" value="0" />
                <c:forEach items="${requestScope.utenti}" var="utente">
                  
                  <c:set var="tot" value="tot${utente.getId()}" />
                  <c:set var="totPren" value="totPren${utente.getId()}" />
                  <c:set var="count" value="${count + 1}" />
                  <c:set var="emailParts" value="${fn:split(utente.getEmail(), '@')}" />

                  <tr>
                  <td scope="row">${count}</td>
                  <td>${utente.getEmail()}</td>
                  <td>${requestScope[totPren]}</td>
                  <td>${requestScope[tot]}</td>
                  </tr>

                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>

        <jsp:include page='components/footer.jsp'/>
    </body>
</html>
