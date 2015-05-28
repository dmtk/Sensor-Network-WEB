<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login to sensor network monitoring</title>
        <script src="javascript/jquery-1.11.2.js"></script>
        <script src="javascript/bootstrap.js"></script>
        <script src="javascript/login.js"></script>
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

                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="about"><span class="glyphicon glyphicon-info-sign"></span>About</a></li>
                        <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <form class="col-md-2 login" role="form" action="authenticate" method="post">
                <div class="form-group">
                    <label for="login">Login</label>
                    <input type="login" class="form-control" name="login"  placeholder="Login">
                    <p class="help-block"></p>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" name="password" placeholder="Password">
                </div>
                <div class="checkbox">
                    <label><input type="checkbox"> Remember me</label>
                </div>
                <button type="submit" class="btn btn-success">Sign In</button>
            </form>

    </body>
</html>
