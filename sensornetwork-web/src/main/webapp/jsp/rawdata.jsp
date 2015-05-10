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
                   
        <p id="displayTemp"></p>
        <p class="small">NTUU KPI</p>
    </body>
</html>
