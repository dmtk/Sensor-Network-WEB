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
    </head>
    <body>
        <nav id="custom-bootstrap-menu" class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="overview">
                        TI Sub-1GHz sensor network</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="overview"><span class="glyphicon glyphicon-home"></span> Overview</a></li>
                        <li><a href="reports" ><span class="glyphicon glyphicon-th-list"></span> Reports</a></li>
                        <li><a href="analitics" ><span class="glyphicon glyphicon-stats"></span> Analytics</a></li>
                        <li><a href="export" ><span class="glyphicon glyphicon-export"></span> Export</a></li>
                        <li><a href="graphics" ><span class="glyphicon glyphicon-signal"></span> Graphics</a></li>
                        <li><a href="map"><span class="glyphicon glyphicon-map-marker"></span> Map</a></li>
                        <li><a href="rawdata" ><span class="glyphicon glyphicon-flash"></span> Raw data</a></li>                                
                        <li><a href="settings"><span class="glyphicon glyphicon-cog"></span>Settings</a></li>
                        <li><a href="about"><span class="glyphicon glyphicon-info-sign"></span>About</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
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
                        <div class="col-md-12 main">
                            <div class="panel panel-default panel-success">
                                <div class="panel-heading">Sensor nodes</div>
                                <div class="panel-body">
                                    <div class="row placeholders">
                                        <c:forEach var="node" items="${nodes}">
                                            <div class="col-md-2 placeholder">
                                                <div class="sensor" align="center">
                                                    <br>
                                                    <br>
                                                    <b><c:out value="${node.type}"/> <c:out value="${node.id}"/></b><br>
                                                    <span class="text-muted" ><c:out value="${node.value}"/></span>
                                                </div>
                                                <br>
                                                <br>
                                            </div>
                                        </c:forEach>
                                    </div></div>
                            </div>
                            <h3 class="sub-header" >Network events</h3>
                            <div class="table-responsive" align="left">
                                <table class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="col-md-1">#</th>
                                            <th class="col-md-1">Type</th>
                                            <th class="col-md-1"># sensor node</th>
                                            <th class="col-md-1">Value</th>
                                            <th class="col-md-2">Measurement quantity</th>
                                            <th class="col-md-1">Note</th>
                                            <th class="col-md-2">Date</th>
                                            <th class="col-md-1">Label</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="event" items="${events}">
                                            <tr class="<c:out value="${event.label}"/>">
                                                <td class="col-md-1"><c:out value="${event.source.id}"/></td>
                                                <td class="col-md-1"><c:out value="${event.source.type}"/></td>
                                                <td class="col-md-1"><c:out value="${event.source.name}"/></td>
                                                <td class="col-md-1"><c:out value="${event.source.value}"/></td>
                                                <td class="col-md-2"><c:out value="${event.source.measuredQuantity}"/></td>
                                                <td class="col-md-1"><c:out value="${event.source.note}"/></td>
                                                <td class="col-md-2"><fmt:formatDate type="both" value="${event.date}"/></td>
                                                <td class="col-md-1"><span class="label label-<c:out value="${event.label}"/>"><c:out value="${event.label}"/></span></td>
                                            </tr>
                                        </c:forEach> 
                                    </tbody>
                                </table>
                            </div>
                            <ul class="pagination">
                                <li class="active"><a href="#">1</a></li>
                                <li><a href="overview?page=2">2</a></li>
                                <li><a href="overview?page=3">3</a></li>
                                <li><a href="overview?page=4">4</a></li>
                                <li><a href="overview?page=5">5</a></li>
                            </ul>
                        </div>
                    </td>
                </tr>
            </div>
        </div>
    </body>
</html>
