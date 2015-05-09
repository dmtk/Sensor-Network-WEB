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
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/basic.css" rel="stylesheet">
        <script>$(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });</script>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="overview">

                        Sub-1GHz sensor network</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">

                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="settings">Settings</a></li>
                        <li><a href="about">About</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Admin</a></li>
                        <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
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
                                <li class="active"><a href="overview"><p><span class="glyphicon glyphicon-home"></span> Overview</p></a></li>
                                <li><a href="reports" ><p><span class="glyphicon glyphicon-th-list"></span> Reports</p></a></li>
                                <li><a href="analitics" ><p><span class="glyphicon glyphicon-stats"></span> Analytics</p></a></li>
                                <li><a href="export" ><p><span class="glyphicon glyphicon-export"></span> Export</p></a></li>
                                <li><a href="graphics" ><p><span class="glyphicon glyphicon-print"></span> Graphics</p></a></li>
                                <li><a href="map" data-toggle="tooltip" title="Hooray!" data-placement="right"><p><span class="glyphicon glyphicon-map-marker"></span> Map</p></a></li>
                                <li><a href="rawdata" ><p><span class="glyphicon glyphicon-flash"></span> Raw data</p></a></li>                                
                            </ul>
                        </div>
                    </td><td>
                        <div class="col-md-10 main">
                            <c:set var="now" value="<%=new java.util.Date()%>" />

                            <p>Formatted Date (1): <fmt:formatDate type="time" 
                                            value="${now}" /></p>
                            <div class="panel panel-default panel-success">
                                <div class="panel-heading">Panel Heading</div>
                                <div class="panel-body">
                                    <div class="row placeholders">
                                        <c:forEach var="nodes" items="${nodes}">

                                            <div class="col-xs-6 col-sm-3 placeholder">
                                                <div class="sensor" align="center">
                                                    <br>
                                                    <br>
                                                    <b><c:out value="${nodes.name}"/></b>
                                                    <span class="text-muted" ><c:out value="${nodes.value}"/></span>
                                                </div>
                                            </div>
                                        </c:forEach>

                                    </div></div>
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
                                            <th class="col-md-3">Label</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="sn" items="${sn}">
                                            <tr>
                                                <td class="col-md-1"><c:out value="${sn.number}"/></td>
                                                <td class="col-md-2"><c:out value="${sn.type}"/></td>
                                                <td class="col-md-1"><c:out value="${sn.number}"/></td>
                                                <td class="col-md-2"><c:out value="${sn.value}"/></td>
                                                <td class="col-md-3"><c:out value="${sn.measuredQuantity}"/></td>
                                                <td class="col-md-3"><span class="label label-danger"><c:out value="${sn.note}"/></span></td>
                                        </c:forEach> 
                                    </tbody>
                                </table>
                            </div>
                            <ul class="pagination">
                                <li><a href="#">1</a></li>
                                <li class="active"><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                            </ul>
                        </div>
                    </td>
                </tr>
            </div>
        </div>
    </body>
</html>
