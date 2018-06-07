<%-- 
    Document   : film
    Created on : Apr 19, 2018, 6:54:49 PM
    Author     : matteo
--%>
<%@page import="it.unitn.disi.cinema.dataaccess.Beans.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@page import="java.util.StringTokenizer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<c:set var="code" value="${errorcode}"/>

<c:if test="${ empty requestScope.errorcode}">
    <c:set var="code" value="400"/>
</c:if>

<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"  crossorigin="anonymous">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Film</title>
    </head>

    <body class="collage">
        <jsp:include page='components/header.jsp'/>
        <br />
        <div class="container">
            <div class='jumbotron'>
                <div class="row">
                    <div class="col-5">

                        <image style="width:100%; height: auto" src="https://http.cat/${code}.jpg" />
                    </div>
                    <div class="col-7 justify-content-center">
                        <div style="text-align: center">
                            <h1><b>Errore ${code}</b></h1>
                            
                            <p class="my-4">${requestScope.mmessage}</p>
                            
                            <c:choose>
                              <c:when test="${empty requestScope.mmessage}">
                                
                                <p>La pagina che stai cercando non è disponibile.</p>
                                <p>Nessun problema, puoi ripartire dalla nostra homepage...</p>
                              </c:when>
                            </c:choose>
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}">Vai alla homepage</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>    
        <jsp:include page='components/footer.jsp'/>

    </body>
</html>
