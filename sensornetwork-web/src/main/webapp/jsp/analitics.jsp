<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- start: Meta -->
        <meta charset="utf-8">
        <title>About</title> 
        <meta name="description" content="About"/>
        <meta name="keywords" content="About" />
        <meta name="author" content="AI"/>
        <!-- end: Meta -->

        <!-- start: Mobile Specific -->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <!-- end: Mobile Specific -->

                <!-- start: CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-responsive.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Droid+Sans:400,700">
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Droid+Serif">
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Boogaloo">
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Economica:700,400italic">
        <!-- end: CSS -->

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <script src="javascript/jquery-1.11.2.js"></script>
        <script src="javascript/bootstrap.js"></script>
        <!-- Bootstrap core CSS
        <link href="css/bootstrap.css" rel="stylesheet"> -->
        <link href="css/basic.css" rel="stylesheet">
    </head>
    <body>
        <!--start: Header -->
        <header>

            <!--start: Container -->
            <div class="container">

                <!--start: Row -->
                <div class="row">

                    <!--start: Logo -->
                    <div class="logo span3">

                        <a class="brand" href="#"><img src="img/logo.png"></a>

                    </div>
                    <!--end: Logo -->

                    <!--start: Navigation -->
                    <div class="span9">

                        <div class="navbar navbar-inverse">
                            <div class="navbar-inner">
                                <div class="container">
                                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                                        <span class="icon-bar"></span>

                                    </a>
                                    <div class="nav-collapse collapse">
                                        <ul class="nav">

                                            <li><a href="overview">Overview</a></li>
                                            <li class="dropdown">
                                                <a href="reports" class="dropdown-toggle" data-toggle="dropdown">Data <b class="caret"></b></a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="reports" >Reports</a></li>
                                                    <li class="active"><a href="analitics" >Analytics</a></li>
                                                    <li><a href="export" >Export</a></li>
                                                    <li><a href="graphics" >Graphics</a></li>
                                                    <li class="divider"></li>
                                                    <li class="nav-header">Nav header</li>
                                                    <li><a href="#">Separated link</a></li>
                                                    <li><a href="#">One more separated link</a></li>
                                                </ul>
                                            </li>


                                            <li><a href="map"> Map</a></li>
                                            <li><a href="rawdata" >Realtime data</a></li>                                
                                            <li><a href="settings">Settings</a></li>
                                            <li><a href="about">About</a></li>
                                            <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><c:out value="${user}"/> <b class="caret"></b></a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="#">Action</a></li>
                                                    <li><a href="#">Another action</a></li>
                                                    <li><a href="#">Something else here</a></li>
                                                    <li class="divider"></li>
                                                    <li class="nav-header">Nav header</li>
                                                    <li><a href="#">Separated link</a></li>
                                                    <li><a href="#">One more separated link</a></li>
                                                    <li><a href="logout">Logout</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>	
                    <!--end: Navigation -->

                </div>
                <!--end: Row -->

            </div>
            <!--end: Container-->			

        </header>
        <!--end: Header-->

        <!--start: Wrapper-->
        <div id="wrapper">

            <!--start: Container -->
            <div class="container">



                
                Average
                <c:out value="${avg}"/>

            </div>
            <!--end: Container-->



        </div>
        <!-- end: Wrapper  -->			

        <!-- start: Footer Menu -->
        <div id="footer-menu" class="hidden-tablet hidden-phone">

            <!-- start: Container -->
            <div class="container">

                <!-- start: Row -->
                <div class="row">



                    <!-- start: Footer Menu Links-->
                    <div class="span9">

                        <div id="footer-menu-links">

                            <ul id="footer-nav">


                                <li><a href="overview">Overview</a></li>
                                <li><a href="reports" >Reports</a></li>
                                <li><a href="analitics" >Analytics</a></li>
                                <li><a href="export" >Export</a></li>
                                <li><a href="graphics" >Graphics</a></li>
                                <li><a href="map"> Map</a></li>
                                <li><a href="rawdata" >Realtime data</a></li>                                
                                <li><a href="settings">Settings</a></li>
                                <li><a href="about">About</a></li>



                            </ul>

                        </div>

                    </div>
                    <!-- end: Footer Menu Links-->

                    <!-- start: Footer Menu Back To Top -->
                    <div class="span1">

                        <div id="footer-menu-back-to-top">
                            <a href="#"></a>
                        </div>

                    </div>
                    <!-- end: Footer Menu Back To Top -->

                </div>
                <!-- end: Row -->

            </div>
            <!-- end: Container  -->	

        </div>	
        <!-- end: Footer Menu -->



        <!-- start: Copyright -->
        <div id="copyright">

            <!-- start: Container -->
            <div class="container">

                <p>
                    &copy; 2016,  Designed by 
                </p>

            </div>
            <!-- end: Container  -->

        </div>	
        <!-- end: Copyright -->

        <!-- start: Java Script -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="js/jquery-1.8.2.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/flexslider.js"></script>
        <script src="js/carousel.js"></script>
        <script src="js/jquery.cslider.js"></script>
        <script src="js/slider.js"></script>
        <script def src="js/custom.js"></script>


    </body>
</html>
