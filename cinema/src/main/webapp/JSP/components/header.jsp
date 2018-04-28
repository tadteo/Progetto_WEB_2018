<!--

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>-->


<%@page import="it.unitn.disi.cinema.dataaccess.Beans.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@page import="java.util.StringTokenizer" %>

<div class="header container">
        <div class="row">
            <div class="col-6">
                <form class="form-signin text-center" action="/cinema/" method="GET">
					<h1><button class="astext">Cinema Universe</button></h1>
				</form>
				<form class="form-signin text-center" action="/cinema/" method="POST">
                  <h3>
                      <button class="astext" name="pageRequested" value="infopage">Info cinema</button>
                  </h3>
				</form>
            </div>
            <div class="col-6">
                <c:choose>
                    <c:when test="${sessionScope.email != null}">
                        <c:set var="emailParts" value="${fn:split(sessionScope.email, '@')}" />
                        <h3>Welcome ${emailParts[0]} <b>(${sessionScope.ruolo})</b></h3>
                        <h4>You are logged in</h4>

                        <form class="form-signin" action="/cinema/logout.do" method="POST">
                                <button class="btn btn-lg btn-primary btn-block" type="submit">Logout</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form class="form-signin" action="/cinema/login.do" method="GET">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Login/Sign Up</button>
                    </c:otherwise>      
                </c:choose>
            </div>
        </div>
    </div>