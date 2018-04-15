<%-- 
    Document   : homepage
    Created on : 23-mar-2018, 16.58.41
    Author     : domenico
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.StringTokenizer" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" crossorigin="anonymous">

        <title>Cinema-Homepage</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <div class="container">
            <div class="jumbotron">
                <h1 class="text-center display-1"><b>Cinema (homepage)</b></h1>

                <% //Commento// Si può fare senza usare la scriplet? con jstl?
                    String email = (String) session.getAttribute("email"); 
                    if (email != null){  
//                        StringTokenizer st = new StringTokenizer(searchFilter, "@");
                        out.println("<h3>Welcome <b>" + email.split("[@]")[0] + "</b></h3><br>");
                %>
                    <h4>You are logged in</h4>
                    
                        <form class="form-signin" action="/cinema/logout.do" method="POST">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Logout</button>
                    </form>
                <%
                    }else{
                        %>
                            <a href="/cinema/login.do">Login</a>
                        <%
                    }
                %>
                
                <div id="movies">
                    <!--Qui vanno messi i cinema disponibili
                    prima però bisogna fare gestire i dati da una servlet-->
                </div>
            </div>
        </div>
    </body>
</html>
