<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Network</title>
        <meta name="author" content="Dmytro Martynyuk"/>
        <meta name="description" content="Sensor network"/>
        <meta name="keywords" content="AJAX"/>
        <script src="javascript/jquery-1.11.2.js"></script>
        <script src="javascript/bootstrap.js"></script>
        <link rel="stylesheet" href="css/basic.css" media="screen" />
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="overview">Sub-1GHz sensor network</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a class="active" href="overview">Control panel</a></li>
                        <li><a href="settings">Settings</a></li>
                        <li><a href="about">About</a></li>
                        <li><a href="logout">Logout</a></li>
                    </ul>                    
                </div>
            </div>
        </nav>

        <br>
        <br>
        <br>

        <div class="container-fluid">
            <div class="row">
                <tr>
                    <td>
                        <div class="col-md-2 sidebar">
                            <ul class="nav nav-sidebar">
                                <li><a href="overview">Overview <span class="sr-only">(current)</span></a></li>
                                <li><a href="reports" >Reports</a></li>
                                <li><a href="analitics" >Analytics</a></li>
                                <li><a href="export" >Export</a></li>
                                <li><a href="graphics" >Export</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li><a href="map" >Map</a></li>
                                <li><a href="rawdata" >Raw data</a></li>
                                <li><a href="api" >API</a></li>
                            </ul>
                        </div>
                    </td><td>
                        <div class="col-md-10 main">
                            <c:set var="now" value="<%=new java.util.Date()%>" />

                            <p>Formatted Date (1): <fmt:formatDate type="time" 
                                            value="${now}" /></p>
                            <div class="row placeholders">
                                <div class="col-xs-6 col-sm-3 placeholder">
                                    <h4 >Sensor node 1</h4>
                                    <span class="text-muted" >Something else</span>
                                </div>
                                <div class="col-xs-6 col-sm-3 placeholder">
                                    <h4 >Sensor node 2</h4>
                                    <span class="text-muted" >Something else</span>
                                </div>
                                <div class="col-xs-6 col-sm-3 placeholder">
                                    <h4 >Sensor node 3</h4>
                                    <span class="text-muted" >Something else</span>
                                </div>
                                <div class="col-xs-6 col-sm-3 placeholder">
                                    <h4 >Sensor node 4</h4>
                                    <span class="text-muted" >Something else  1</span>
                                </div>
                            </div>

                            <h2 class="sub-header" >Section title 2</h2>
                            <div class="table-responsive" align="left">
                                <table class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="col-md-1">#</th>
                                            <th class="col-md-2">Type</th>
                                            <th class="col-md-1"># sensor node</th>
                                            <th class="col-md-2">Value</th>
                                            <th class="col-md-3">Measurement quantity</th>
                                            <th class="col-md-3">Note</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="sn" items="${sn}">
                                            <tr>
                                                <td class="col-md-1"><c:out value="${sn.id}"/></td>
                                                <td class="col-md-2"><c:out value="${sn.type}"/></td>
                                                <td class="col-md-1"><c:out value="${sn.id}"/></td>
                                                <td class="col-md-2"><c:out value="${sn.value}"/></td>
                                                <td class="col-md-3"><c:out value="${sn.measuredQuantity}"/></td>
                                                <td class="col-md-3"><c:out value="${sn.note}"/></td>
                                            </tr>
                                        </c:forEach>                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </td>
                </tr>
            </div>
        </div>
    </body>
</html>
