<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="javascript/jquery-1.11.2.js"></script>
        <script src="http://code.highcharts.com/highcharts.js"></script>
        <script src="http://code.highcharts.com/highcharts-more.js"></script>
        <script src="http://code.highcharts.com/modules/exporting.js"></script>
        <script src="javascript/graph.js"></script>
        <script src="javascript/bootstrap.js"></script>
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" href="css/basic.css" media="screen" />

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
                        <li  class="active"><a href="graphics" ><span class="glyphicon glyphicon-signal"></span> Graphics</a></li>
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
        Test<div id="displayTemp"></div>
        <br>
        <br>
        <br>
        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
        <div id="container3" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
    </body>
</html>
