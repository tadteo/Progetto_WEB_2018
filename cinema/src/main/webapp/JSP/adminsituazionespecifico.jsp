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
    		
    <div class="container jumbotron reservation my-4">
      <h3 class="my-4 text-center">Spettacolo <b>${requestScope.spettacolo.getId()} - ${requestScope.spettacolo.getDataOra()} </b></h3>
      <h3 class="my-4 text-center">Sala: ${requestScope.sala.getDescrizione()} </h3>
      <div class="container">
        <div class="row">
          
          <div class="col-3">
            
          </div>
          <div class="col-6">
            <div class="row">

            <c:set var="lastRow" value="0"/>
            <c:set var="firstId" value="${packageposti.get(0).getPosto().getId()}"/>
            <c:forEach items="${packageposti}" var="packageposto">
              <c:choose>
                <c:when test="${packageposto.getPosto().getRiga() != lastRow}">
                  </div>
                  <div class="row">
                </c:when>
              </c:choose>

              <div class="col-1 padding-0 marginbtn">
              
              <form>

                <c:set var="pst_id" value="${packageposto.getPosto().getId() - firstId + 1}"/>
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
                  <c:when test="${packageposto.getPosto().getEsiste() == true}">
                    <c:choose>
                      <c:when test="${packageposto.getPrenotazione() == null}">
                        <button type="submit" class="btn btn-success" disabled>${space}${pst_id}</button>
                      </c:when>
                      <c:otherwise>
                        <button type="submit" class="btn btn-danger" disabled>${space}${pst_id}</button>
                      </c:otherwise>
                    </c:choose>
                  </c:when>
                  <c:otherwise>
                     <button type="submit" class="btn" disabled>${space}${pst_id}</button>
                  </c:otherwise>
                </c:choose>
            </form>
            </div>     
            <c:set var="lastRow" value="${packageposto.getPosto().getRiga()}"/>
        </c:forEach>
            </div>
          </div>
          <div class="col-3">
            
          </div>
        </div>
      </div>
    </div>



        </body>
    <jsp:include page='components/footer.jsp'/>
</html>
