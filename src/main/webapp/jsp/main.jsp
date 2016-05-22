<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title><c:out value="${activePage}"/></title>
        <script src="javascript/jquery-1.11.2.js"></script>
        <script src="javascript/bootstrap.js"></script>
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
        
    </head>
    <body>
        <%@include file="jspf/navbar.jspf" %>
        <div class="container-fluid">
            <div class="main">
                <c:choose>
                    <c:when test="${activePage==\"overview\"}">
                        <%@include file="jspf/livecharts.jspf" %>
                    </c:when>
                    <c:when test="${activePage==\"charts\"}">
                        <%@include file="jspf/charts.jspf" %>
                    </c:when>
                    <c:when test="${activePage==\"export\"}">
                        <%@include file="jspf/export.jspf" %>
                    </c:when>
                    <c:when test="${activePage==\"sensors\"}">
                        <%@include file="jspf/sensors.jspf" %>
                    </c:when>
                    <c:when test="${activePage==\"sensornodes\"}">
                        <%@include file="jspf/sensorNodes.jspf" %>
                    </c:when>
                    <c:when test="${activePage==\"options\"}">
                        <%@include file="jspf/options.jspf" %>
                    </c:when>
                    <c:when test="${activePage==\"about\"}">
                        <%@include file="jspf/about.jspf" %>                        
                    </c:when>
                    <c:otherwise>
                        <%@include file="jspf/measurements.jspf" %> 
                    </c:otherwise>


                </c:choose>

            </div>
        </div>

    </body>
</html>