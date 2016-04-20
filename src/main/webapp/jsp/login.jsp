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
        <div class="vertical-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-md-offset-4" >
                        <form class="form-signin" role="form" action='/Sensor-Network-WEB/login' method="post">
                            <div class="form-group">
                                <label for="username">Login</label>
                                <input type="text" class="form-control" name="username">
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" name="password">
                            </div>
                            <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-success btn-block">Sign In</button>
                        </form>
                        <c:choose> 
                            <c:when test="${not empty error}">
                                <div class="alert alert-danger">
                                    <strong>Wrong login or password!</strong>
                                </div> 
                            </c:when>
                            <c:when test="${not empty logout}">
                                <div class="alert alert-success">
                                    <c:out value="${logout}"/>
                                </div> 
                            </c:when>
                        </c:choose>
                    </div>
                </div></div></div>
        <script src="javascript/jquery-1.11.2.js"></script>
        <script src="javascript/bootstrap.js"></script>
    </body>
</html>
