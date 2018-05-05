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
        <title>Cinema Start Script</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"  crossorigin="anonymous">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Custom styles for this template -->
    </head>
    <body class="collage">
        <div class="header container">
            <br><br>
            <div class="row">
                <div class="col-12">
                    <h1 class="text-center"><b>Start Script</b></h1> 
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="container-fluid">
                    <form  class="signin" action="/cinema/startup.do" method="POST">
                        <br>
                        <label>Durata Programmazione(minuti) - (1-200)</label>
                        <input type="number" id="durata" name="durata" class="form-control" placeholder="Durata (minuti)" min="1" max="200" required autofocus>
                        <br/>
                        <label>Numero Proiezioni per film - (1-20)</label>
                        <input type="number" id="proiezioni" name="proiezioni" class="form-control" placeholder="Proiezioni" value="10" min="1" max="20" required autofocus>
                        <br/>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Start</button>
                    </form>
                </div> 
            </div>
            <div class="col-md-4"></div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </body>
</html>
