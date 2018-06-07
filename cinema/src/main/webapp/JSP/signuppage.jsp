<%-- 
    Document   : signUp
    Created on : Apr 20, 2018, 12:53:17 AM
    Author     : matteo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cinema Sign Up</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"  crossorigin="anonymous">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/cinema.css">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Custom styles for this template -->
    </head>
    <body class="collage">
        <div class="container my-3">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-12 col-md-6">
                    <div class="jumbotron">
                        <form data-toggle="validator" role="form" class="form-signin text-center" action="/cinema/signup.do" method="POST">
                            <h2 class="form-signin-heading">Registrati</h2>
                            <p><span class="my-2" style="color: red">${sessionScope.signupErrorMessage}</span></p>
                            <label for="email">Indirizzo e-mail</label>
                            <input type="email" id="email" name="email" class="form-control mb-2" placeholder="Email" required autofocus>
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password" class="form-control mb-2" placeholder="Password" required>
                            <label for="password">Riscrivi la Password</label>
                            <input type="password" id="password" name="passwordConfirm" class="form-control mb-2" placeholder="Ripeti la password" required>

                            <br/><br/>

                            <button class="btn btn-lg btn-primary btn-block" type="submit">REGISTRATI</button>
                        </form>
                    </div>
                </div>            
            </div>
            <div class="col-md-3"></div>
        </div> <!-- /container -->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </body>
</html>
