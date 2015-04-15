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
            <fieldset>
                <legend>jQuery Ajax Form data Submit Request</legend>
                <label for="temperature">Type temperature</label>
                <input id="temperature" type="submit" value="Очистити" method="GET"/>
            </fieldset>
        </form>
        <fieldset>
            <legend>Response from jQuery Ajax Request</legend>
            <p id="displayTemp"></p>
        </fieldset>

        <p class="small">NTUU KPI</p>
    </body>
</html>
