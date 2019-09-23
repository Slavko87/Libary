<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="/header.html"%>
<title>Unos autora</title>
</head>
<body onload="<%@ include file="/upitAdminStranice.jsp"%>">
<%@ include file="/upitMenu.jsp"%>
<div class="container py-5">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
           <div class="row">
                <div class="col-lg-5 col-md-7 col-sm-7 mx-auto" >
                   <div class="card rounded-1" >
                        <div class="card-header">
                            <h4 class="mb-0">UNOS AUTORA</h4>
                        </div>
                        <div class="card-body" >
                            <form class="form" role="form" id="frmLogin" action="/Libary/ServletAutor" method="post">
                                <div class="form-group">
                                    <label for="lblusername">Ime</label>
                                    <input type="text" autofocus="autofocus" class="form-control form-control-lg rounded-0" name="ime" id="ime" required>
                                </div>
                                <div class="form-group">
                                    <label for="lblusername">Prezime</label>
                                    <input type="text" autofocus="autofocus" class="form-control form-control-lg rounded-0" name="prezime" id="prezima" required>
                                </div>
                                <input type="hidden" name="hiddenZahtev" value="unosAutora">
                                <button type="submit" class="btn btn-success btn-lg btn-block" id="btnLogin">Unesi</button>
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