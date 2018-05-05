<%-- 
    Document   : bypage
    Created on : May 3, 2018, 3:00:45 PM
    Author     : matteo
--%>

<%@page import="it.unitn.disi.cinema.dataaccess.Beans.Prezzo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@ page import="java.util.StringTokenizer" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"  crossorigin="anonymous">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">

        <title>Cinema-Homepage</title>
    </head>
    <body>
        <br />
        <div class="header container">
            <div class="row">
                <div class="col-sm-12 col-md-6">
                    <h1 class="text-center"><b>Cinema Universe</b></h1>
                    
                    <form class="form-signin text-center" action="/cinema/" method="POST">
                        <h3><button class="astext" name="pageRequested" value="infopage"> Info cinema</button></h3>
                    </form>
                </div>
                <div class="col-sm-12 col-md-6 login">
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
                              <button class="btn btn-lg btn-primary btn-block" type="submit">Login/Sign up</button>
                            </form>
                      </c:otherwise>      
                    </c:choose>
                </div>
            </div>
        </div>
        <br>
        <div class="container justify-content-center">
          <div class="jumbotron">
			  <ul>
					<c:forEach items="${requestScope.posti}" var="posto">
						<li><b>Poltrona N:${posto.getPoltrona()} alla riga N:${posto.getRiga()}</b> - E' per un cliente con tariffa ridotta? 
						</li>
					</c:forEach>
			  </ul>
			  

		  </div>
		</div>
			

        <footer class="container page-footer font-small blue pt-4 mt-4">
            <div class="row">
                <div class="col-lg-6 col-md-12">
                    <p><a href="${pageContext.request.contextPath}/JSP/infopage.jsp">Info:</a></p>
                    <p><b>Telefono:</b> +39 0123 123123</p>
                    <p><b>Indirizzo:</b> Via La Vita E Tutto Quanto, 42 (UNIVERSO)</p>
                    <p><b>Partita Iva: </b>01234561001<b> – C.F. </b>01234561001</p>

                </div>
                <div class="col-lg-6 col-md-12">
                    <br />
                    <p>Posted by: Magic Group Srl</p>
                    <p>Contact information: <a href="mailto:info@magicgroup.com">info@magicgroup.com</a>.</p>
                </div>

            </div>
            <br>
            <p style="font-size: 0.8rem;font-style: italic;" class="copyright">Copyright © 2018 · Tadiello Matteo - Stefani Domenico - Martini Ivan · all rights reserved.</p>
        </footer>
    </body>
</html>

