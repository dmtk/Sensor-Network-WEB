<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login to sensor network monitoring system</title>
        <script src="javascript/jquery-1.11.2.js"></script>
        <script src="javascript/bootstrap.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="javascript/login.js"></script>
        <!-- Bootstrap core CSS-->
        <link href="css/bootstrap.css" rel="stylesheet"> 
        <link href="css/basic.css" rel="stylesheet">
    </head>
    <body background="img/network.jpeg">


        <table class="table">

            <tbody>

                <tr><br></tr>
            <tr>
                <td class="col-md-4"></td>
                <td class="col-md-4">
                    <form class="login" role="form" action="authenticate" method="post">
                        <div class="form-group">
                            <label for="email">Login</label>
                            <input type="email" class="form-control" name="login">
                            <p class="help-block"><a href="guest">Login as a guest</a></p>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" name="password">
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox"> Remember me</label>
                        </div>
                        <button type="submit" class="btn btn-primary">Sign In</button>
                    </form>
                </td>
                <td class="col-md-4"></td>

            </tr>

        </tbody>
    </table>



</body>
</html>
