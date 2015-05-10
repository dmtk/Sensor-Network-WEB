<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login to sensor network monitoring</title>
        <script src="javascript/jquery-1.11.2.js"></script>
        <script src="javascript/bootstrap.js"></script>
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/basic.css" rel="stylesheet">
    </head>
    <body>
        <div id="myCarousel" class="carousel slide image" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
                <li data-target="#myCarousel" data-slide-to="3"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div align="center" class="item active">
                    <img src="img/EquipmentImage_250.jpg" alt="Chania">
                </div>

                <div align="center" class="item">
                    <img src="img/future_chip.jpg" alt="Chania">
                </div>

                <div align="center" class="item">
                    <img src="img/lamps.jpg" alt="Flower">
                </div>

                <div align="center" class="item">
                    <img src="img/msp430-risc-block.jpg" alt="Flower">
                </div>
            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <form class="login" role="form" action="authenticate" method="post">
            <div class="form-group">
                <label for="login">Login</label>
                <input type="login" class="form-control" name="login"  placeholder="Login">
                <p class="help-block">Пример строки с подсказкой</p>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" name="password" placeholder="Password">
            </div>
            <div class="checkbox">
                <label><input type="checkbox"> Remember</label>
            </div>
            <button type="submit" class="btn btn-success">Sign In</button>
        </form>
    </body>
</html>
