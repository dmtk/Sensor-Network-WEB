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
        <script src="javascript/basic.js"></script>
        <link rel="stylesheet" href="css/basic.css" media="screen" />

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
        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
        
        <div id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
        
        <div id="container3" style="min-width: 310px; height: 400px; margin: 0 auto"></div>



    </body>
</html>
