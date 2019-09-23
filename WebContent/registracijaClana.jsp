<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="indexcss.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" charset="utf-8"></script>
<title>Registracija clana</title>
</head>
<%
if (request.getAttribute("registrovan") != null)
{
	boolean b = (boolean) request.getAttribute("registrovan");
	if (b == false)
	{
		%>
		<script type="text/javascript">
		function display_alert()
  		{
			alert("Pogresno ste uneli password ili vec postoji korisnik sa ovim emailom! ")
		}
		</script>
		<%
	}
}
%>

<body class="bg-grad" onload="display_alert()">

<div class="container py-5" >
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
           <div class="row">
                <div class="col-lg-5 col-md-7 col-sm-7 mx-auto">
                   <div class="card rounded-1">
                        <div class="card-header">
                            <h4 class="mb-0">REGISTRUJ SE</h4>
                        </div>
                        <div class="card-body">
                            <form class="form" role="form" id="frmLogin" action="/Libary/ServletRegistracija" method="post">
                            	<div class="form-group">
                                    <label for="lblusername">Ime</label>
                                    <input type="text" autofocus="autofocus" class="form-control form-control-lg rounded-0" name="ime" id="ime" required>
                                </div>
                                <div class="form-group">
                                    <label for="lblusername">Prezime</label>
                                    <input type="text" autofocus="autofocus" class="form-control form-control-lg rounded-0" name="prezime" id="prezime" required>
                                </div>
                                <div class="form-group">
                                    <label for="lblusername">Email</label>
                                    <input type="text" autofocus="autofocus" class="form-control form-control-lg rounded-0" name="email" id="email" required>
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" class="form-control form-control-lg rounded-0" id="password" name="password" required>
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" class="form-control form-control-lg rounded-0" id="password2" name="password2" required>
                                </div>
                                <input type="hidden" name="hiddenZahtev" value="registracijaClana">
                                <button type="submit" class="btn btn-success btn-lg btn-block" id="btnLogin">Registruj se</button>
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