<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="it.unitn.disi.cinema.dataaccess.Beans.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@page import="java.util.StringTokenizer" %>



<c:set var="context" value="${pageContext.request.contextPath}" />


<link href="https://fonts.googleapis.com/css?family=Gugi" rel="stylesheet">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <a class="navbar-brand" href="${context}" style="font-family: 'Gugi', cursive;">CINEMA UNIVERSE</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${context}">Home <span class="sr-only">(current)</span></a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link" href="#">Info</a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link" href="#">Prezzi</a>
            </li>
        </ul>
            
            
        <ul class="navbar-nav my-2 my-lg-0">
            <c:choose>
                <c:when test="${sessionScope.email != null}">
                    <c:set var="emailParts" value="${fn:split(sessionScope.email, '@')}" />
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <b>${emailParts[0]}</b>(${sessionScope.ruolo})
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                            <form class="form-signin" action="/cinema/logout.do" method="POST">
                                <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Logout</button>
                            </form>
                        </div>
                    </li>   
                </c:when>
                <c:otherwise>
                    <li><form class="form-inline " action="/cinema/login.do" method="GET">
                          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Accedi</button>
                    </form></li>
                </c:otherwise>      
            </c:choose>
        </ul>
    </div>
</nav>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" crossorigin="anonymous"></script>