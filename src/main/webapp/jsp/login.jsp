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
        <form class="login" role="form">
            <div class="form-group">
                <label for="login">Email</label>
                <input type="login" class="form-control" id="email" placeholder="Login">
                <p class="help-block">Пример строки с подсказкой</p>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="pass" placeholder="Password">
            </div>
            <div class="checkbox">
                <label><input type="checkbox"> Remember</label>
            </div>
            <button type="submit" class="btn btn-success">Войти</button>
        </form>
    </body>
</html>
