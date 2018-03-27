<%-- 
    Document   : homepage
    Created on : 23-mar-2018, 16.58.41
    Author     : domenico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is the homepage</h1>
        
        <% //Commento// Si può fare senza usare la scriplet? con jstl?
            String username = (String) session.getAttribute("username"); 
            if (username != null){
                out.println("Welcome " + username);
        %>
            <form class="form-signin" action="/site/logout.do" method="POST">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Logout</button>
            </form>
        <%
            }else{
                %>
                    <a href="/site/login.do">Login</a>
                <%
            }
        %>
    </body>
</html>
