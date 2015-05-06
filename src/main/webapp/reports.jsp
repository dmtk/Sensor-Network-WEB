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
        <link rel="stylesheet" href="css/basic.css" media="screen" />
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.jsp">Sub-1GHz sensor network</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a class="active" href="index.jsp">Control panel</a></li>
                        <li><a href="settings.jsp">Setting</a></li>
                        <li><a href="about.jsp">About</a></li>
                        <li><a href="logout.jsp">Logout</a></li>
                    </ul>
                    <!--<form class="navbar-form navbar-right">
                        <input class="form-control" placeholder="Search..." type="text">
                    </form>-->
                </div>
            </div>
        </nav>
        
        <br>
        <div class="container-fluid">
            <div class="row">
                <tr>
                    <td>
                        <div class="col-md-2 sidebar">
                            <ul class="nav nav-sidebar">
                                <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
                                <li><a href="reports.jsp" >Reports</a></li>
                                <li><a href="#" >Analytics</a></li>
                                <li><a href="#" >Export</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li><a href="" >Map</a></li>
                                <li><a href="" >Raw data</a></li>
                                <li><a href="" >API</a></li>
                            </ul>
                        </div>
                    </td><td>
                        <div class="col-md-9 col-md-offset-1 main">
                            <h1 class="page-header" >Control panel</h1>

                            <div class="row placeholders">
                                <div class="col-xs-6 col-sm-3 placeholder">

                                    <h4 >Sensor node 1</h4>
                                    <span class="text-muted" >Something else</span>
                                </div>
                                <div class="col-xs-6 col-sm-3 placeholder">
                                    <h4 >Sensor node 2</h4>
                                    <span class="text-muted" >Something else</span>
                                </div>
                                <div class="col-xs-6 col-sm-3 placeholder">
                                    <h4 >Sensor node 3</h4>
                                    <span class="text-muted" >Something else</span>
                                </div>
                                <div class="col-xs-6 col-sm-3 placeholder">
                                    <h4 >Sensor node 4</h4>
                                    <span class="text-muted" >Something else  1</span>
                                </div>
                            </div>

                            <h2 class="sub-header" >Section title 2</h2>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th >Header</th>
                                            <th >Header</th>
                                            <th >Header</th>
                                            <th >Header</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1,001</td>
                                            <td >Lorem</td>
                                            <td >ipsum</td>
                                            <td >dolor</td>
                                            <td >sit</td>
                                        </tr>
                                        <tr>
                                            <td>1,002</td>
                                            <td >amet</td>
                                            <td >consectetur</td>
                                            <td >adipiscing</td>
                                            <td >elit</td>
                                        </tr>
                                        <tr>
                                            <td>1,003</td>
                                            <td >Integer</td>
                                            <td >nec</td>
                                            <td >odio</td>
                                            <td >Praesent</td>
                                        </tr>
                                        <tr>
                                            <td>1,003</td>
                                            <td >libero</td>
                                            <td >Sed</td>
                                            <td >cursus</td>
                                            <td >ante</td>
                                        </tr>
                                        <tr>
                                            <td>1,004</td>
                                            <td >dapibus</td>
                                            <td >diam</td>
                                            <td >Sed</td>
                                            <td >nisi</td>
                                        </tr>
                                        <tr>
                                            <td>1,005</td>
                                            <td >Nulla</td>
                                            <td >quis</td>
                                            <td >sem</td>
                                            <td >at</td>
                                        </tr>
                                        <tr>
                                            <td>1,006</td>
                                            <td >nibh</td>
                                            <td >elementum</td>
                                            <td >imperdiet</td>
                                            <td >Duis</td>
                                        </tr>
                                        <tr>
                                            <td>1,007</td>
                                            <td >sagittis</td>
                                            <td >ipsum</td>
                                            <td >Praesent</td>
                                            <td >mauris</td>
                                        </tr>
                                        <tr>
                                            <td>1,008</td>
                                            <td >Fusce</td>
                                            <td >nec</td>
                                            <td >tellus</td>
                                            <td >sed</td>
                                        </tr>
                                        <tr>
                                            <td>1,009</td>
                                            <td >augue</td>
                                            <td >semper</td>
                                            <td >porta</td>
                                            <td >Mauris</td>
                                        </tr>
                                        <tr>
                                            <td>1,010</td>
                                            <td >massa</td>
                                            <td >Vestibulum</td>
                                            <td >lacinia</td>
                                            <td >arcu</td>
                                        </tr>
                                        <tr>
                                            <td>1,011</td>
                                            <td >eget</td>
                                            <td >nulla</td>
                                            <td >Класс</td>
                                            <td >aptent</td>
                                        </tr>
                                        <tr>
                                            <td>1,012</td>
                                            <td >taciti</td>
                                            <td >sociosqu</td>
                                            <td >ad</td>
                                            <td >litora</td>
                                        </tr>
                                        <tr>
                                            <td>1,013</td>
                                            <td >torquent</td>
                                            <td >per</td>
                                            <td >conubia</td>
                                            <td >nostra</td>
                                        </tr>
                                        <tr>
                                            <td>1,014</td>
                                            <td >per</td>
                                            <td >inceptos</td>
                                            <td >himenaeos</td>
                                            <td >Curabitur</td>
                                        </tr>
                                        <tr>
                                            <td>1,015</td>
                                            <td >sodales</td>
                                            <td >ligula</td>
                                            <td >in</td>
                                            <td >libero</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </td>
                </tr>
            </div>
        </div>
    </body>
</html>
