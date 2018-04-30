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
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"  crossorigin="anonymous">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Custom styles for this template -->
    </head>
    <body>
		<div class="header container">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-center"><b>Cinema (Sign up page)</b></h1> 
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <form class="form-signin" action="/cinema/login.do" method="POST">
                    <h2 class="form-signin-heading">Registrati</h2>
                    <br/>
                    <span style="color: red">${sessionScope.errorMessage}</span>
                    <br/><br/>
                    <label for="email">Indirizzo e-mail</label>
                    <input type="text" id="email" name="email" class="form-control" placeholder="Email" required autofocus>
                    <br/>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                    <label for="password">Riscrivi la Password</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                    
                    <br/><br/>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">REGISTRATI</button>
                </form>
            </div>
        </div> <!-- /container -->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </body>
</html>
