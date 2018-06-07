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

        <title>Zona Admin - programmazioni</title>
    </head>
    <jsp:include page='components/header.jsp'/>

    <body class="collage">
    <div class="card margin-admin">
      <div class="card-body">
        SITUAZIONE DELLE PROGRAMMAZIONI
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
              <td><a href="./situazione/${spettacoloo.getId()}">${requestScope[film]} - (${spettacoloo.getDataOra()})</a></td> 
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
