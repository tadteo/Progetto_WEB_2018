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
            LISTA DEI 10 TOP CLIENTI
          </div>
        </div>
        <div class="card margin-admin">
          <div class="card-body">
            <table id="clientitable" class="table  table-striped">
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
                    <td>${emailParts[0]}</td>
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
  <script>
    function sortTable() {
      var table, rows, switching, i, x, y, shouldSwitch;
      table = document.getElementById("clientitable");
      switching = true;
      /* Make a loop that will continue until
      no switching has been done: */
      while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("TR");
        /* Loop through all table rows (except the
        first, which contains table headers): */
        for (i = 1; i < (rows.length - 1); i++) {
          // Start by saying there should be no switching:
          shouldSwitch = false;
          /* Get the two elements you want to compare,
          one from current row and one from the next: */
          x = rows[i].getElementsByTagName("TD")[3];
          y = rows[i + 1].getElementsByTagName("TD")[3];
          // Check if the two rows should switch place:
          if (parseInt(x.innerHTML) < parseInt(y.innerHTML)) {
            // If so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        }
        if (shouldSwitch) {
          /* If a switch has been marked, make the switch
          and mark that a switch has been done: */
          for (var w = 1; w < 4; w++) {
            var tmp = rows[i].getElementsByTagName("TD")[w].innerHTML
            rows[i].getElementsByTagName("TD")[w].innerHTML = rows[i + 1].getElementsByTagName("TD")[w].innerHTML
            rows[i + 1].getElementsByTagName("TD")[w].innerHTML = tmp
          }
          switching = true;
        }
      }
    }

    function removeExcess() {
      var table, rows, switching, i, x, y, shouldSwitch;
      table = document.getElementById("clientitable");
      /* Make a loop that will continue until
      no switching has been done: */
        rows = table.getElementsByTagName("TR");
        /* Loop through all table rows (except the
        first, which contains table headers): */
        for (i = 11; i < (rows.length); i++) {
          // Start by saying there should be no switching:
          shouldSwitch = false;
          /* Get the two elements you want to compare,
          one from current row and one from the next: */
          rows[i].style.display = 'none'
        }
    }

    sortTable()
    removeExcess()
  </script>
</html>

