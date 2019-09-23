<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="indexcss.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
<%@ include file="header.html"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" charset="utf-8"></script>
<title>Dobrodosli u biblioteku</title>
</head>
<body class="bg-grad" >
<div class="container py-5">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
           <div class="row">
                <div class="col-lg-5 col-md-7 col-sm-7 mx-auto" >
                   <div class="card rounded-1" >
                        <div class="card-header">
                            <h4 class="mb-0">LOGIN</h4>
                        </div>
                        <div class="card-body" >
                            <form class="form" role="form" id="frmLogin" action="/Libary/ServletLogin" method="post">
                                <div class="form-group">
                                    <label for="lblusername">Email</label>
                                    <input type="text" autofocus="autofocus" class="form-control form-control-lg rounded-0" name="email" id="email">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" class="form-control form-control-lg rounded-0" id="password" name="password">
                                </div>
                                <div class="d-flex justify-content-center links" style="margin-bottom:5px">
									<a href="registracijaClana.jsp">Registruj se</a>
								</div>
								<input type="hidden" name="hiddenZahtev" value="logovanje">
                                <button type="submit" class="btn btn-success btn-lg btn-block" id="btnLogin">Login</button>
                            </form>
                        </div>
                       </div>
                    </div>
            </div>
        </div>
    </div>
   
</div>
</body>
</html>