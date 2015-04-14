<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Temperature</title>
        <meta name="author" content="Dmytro Martynyuk"/>
        <meta name="description" content="Sensor network"/>
        <meta name="keywords" content="AJAX"/>
        
        <script src="javascript/jquery-1.11.2.js"></script>
        <script src="javascript/basic.js"></script>
        <link rel="stylesheet" href="css/basic.css" media="screen" />
    </head>
    <body>
        <p class="large">Temperature</p>
        <form id="updateTemperature">
            <label for="temperature">Type temperature</label>
            <input type="text" id="temperature" name="temperature" />
            
            <input type="submit"/>
            
        </form>
        <p id="displayTemp"></p>
        <hr/>
        <p class="small">NTUU KPI</p>
    </body>
</html>
