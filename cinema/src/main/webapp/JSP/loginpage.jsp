<%-- 
    Document   : loginpage
    Created on : 26-mar-2018, 18.25.31
    Author     : domenico
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cinema Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"  crossorigin="anonymous">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Custom styles for this template -->
    </head>
    <body>
        <br/>
        <div class="container">
            <div class="row">
                <div class="col-3"></div>
                <div class="col-6">
                    <div class="jumbotron">
                                <form class="form-signin text-center" action="/cinema/login.do" method="POST">
                                    <h2 class="form-signin-heading">Accedi</h2>
                                    <br/>
                                    <span style="color: red">${sessionScope.errorMessage}</span>
                                    <br/><br/>
                                    <label for="email">Indirizzo e-mail</label>
                                    <input type="text" id="email" name="email" class="form-control" placeholder="Email" required autofocus>
                                    <br/>
                                    <label for="password">Password</label>
                                    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                                    <br/><br/>

                                    <button class="btn btn-lg btn-primary btn-block" type="submit">Accedi</button>
                                    <br />
                                    <p>Non sei ancora registrato? <a href="/cinema/signup.do">REGISTRATI ora!</a></p>
                                </form> 
                    </div>
                </div>
                <div class="col-3"></div>
            </div>
        </div> <!-- /container0 -->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </body>
</html>
