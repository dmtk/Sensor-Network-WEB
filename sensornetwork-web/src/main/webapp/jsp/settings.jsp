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
                        <li><a href="overview"><span class="glyphicon glyphicon-home"></span> Overview</a></li>
                        <li><a href="reports" ><span class="glyphicon glyphicon-th-list"></span> Reports</a></li>
                        <li><a href="analitics" ><span class="glyphicon glyphicon-stats"></span> Analytics</a></li>
                        <li><a href="export" ><span class="glyphicon glyphicon-export"></span> Export</a></li>
                        <li><a href="graphics" ><span class="glyphicon glyphicon-signal"></span> Graphics</a></li>
                        <li><a href="map"><span class="glyphicon glyphicon-map-marker"></span> Map</a></li>
                        <li><a href="rawdata" ><span class="glyphicon glyphicon-flash"></span> Raw data</a></li>                                
                        <li class="active"><a href="settings"><span class="glyphicon glyphicon-cog"></span>Settings</a></li>
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
                        <div class="col-md-10 main">
                            <c:set var="now" value="<%=new java.util.Date()%>" />

                            <p>Formatted Date (1): <fmt:formatDate type="time" 
                                            value="${now}" /></p>
                            <div class="form-group">
                                <label for="comment">Comment:</label>
                                <textarea class="form-control" rows="5" id="comment"></textarea>
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" value="">Option 1</label>
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" value="">Option 2</label>
                            </div>
                            <div class="checkbox disabled">
                                <label><input type="checkbox" value="" disabled>Option 3</label>
                            </div>
                            <label class="checkbox-inline"><input type="checkbox" value="">Option 1</label>
                            <label class="checkbox-inline"><input type="checkbox" value="">Option 2</label>
                            <label class="checkbox-inline"><input type="checkbox" value="">Option 3</label>
                            <div class="radio">
                                <label><input type="radio" name="optradio">Option 1</label>
                            </div>
                            <div class="radio">
                                <label><input type="radio" name="optradio">Option 2</label>
                            </div>
                            <div class="radio disabled">
                                <label><input type="radio" name="optradio" disabled>Option 3</label>
                            </div>
                            <label class="radio-inline"><input type="radio" name="optradio">Option 1</label>
                            <label class="radio-inline"><input type="radio" name="optradio">Option 2</label>
                            <label class="radio-inline"><input type="radio" name="optradio">Option 3</label> 
                            <div class="form-group">
                                <label for="sel1">Select list:</label>
                                <select class="form-control" id="sel1">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                </select>
                            </div>
                        </div>
                    </td>
                </tr>
            </div>
        </div>
    </body>
</html>
