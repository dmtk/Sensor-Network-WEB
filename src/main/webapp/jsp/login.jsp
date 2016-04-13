<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign In to sensor network monitoring system</title>

        <!-- Bootstrap core CSS-->
        <link href="css/bootstrap.css" rel="stylesheet"> 

    </head>
    <body>
        <div class="container">
            <form class="form-signin" role="form" action='/Sensor-Network-WEB/j_spring_security_check' method="post">
                <div class="form-group">
                    <label for="j_username">Login</label>
                    <input type="text" class="form-control" name="j_username">
                </div>
                <div class="form-group">
                    <label for="j_password">Password</label>
                    <input type="password" class="form-control" name="j_password">
                </div>
                <button type="submit" class="btn btn-success">Sign In</button>
            </form>
            <c:choose> 
                <c:when test="${not empty error}">
                    <div class="alert alert-danger">
                        <strong>Wrong login or password!</strong>
                    </div> 
                </c:when>

            </c:choose>
        </div>
        <script src="javascript/jquery-1.11.2.js"></script>
        <script src="javascript/bootstrap.js"></script>


    </body>
</html>
