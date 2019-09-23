<%@page import="database.AutorDB"%>
<%@page import="model.Autor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="/header.html"%>
<title>Unos knjige</title>
</head>

<body class="bg-grad" onload="openwin()">
<%@ include file="/upitMenu.jsp"%>
<%@ include file="/upitAdminStranice.jsp"%>
<div class="container py-5">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
           <div class="row">
                <div class="col-lg-5 col-md-7 col-sm-7 mx-auto" >
                   <div class="card rounded-1" >
                        <div class="card-header">
                            <h4 class="mb-0">UNOS KNJIGE</h4>
                        </div>
                        <div class="card-body" >
                            <form class="form" role="form" id="frmLogin" action="/Libary/ServletKnjiga" method="post">
                                <div class="form-group">
                                    <label for="lblusername">Naziv</label>
                                    <input type="text" autofocus="autofocus" class="form-control form-control-lg rounded-0" name="naziv" id="naziv" required>
                                </div>
                                <div class="form-group">
                                    <label>Broj knjiga</label>
                                    <input type="number" class="form-control form-control-lg rounded-0" id="brojKnjiga" name="brojKnjiga" required>
                                </div>
                                <select name="autori[]" size="5" multiple="multiple" style="margin:auto; display:block; margin-top:10px; margin-bottom:10px; width: 100%;" required>
								<%
								ArrayList<Autor> listaAutora = new AutorDB().dajListuAutora();
						    	for(Autor list:listaAutora)  
								{ 
									%>
									<option value=<%=list.getId()%>> <%=list%></option>
									<%
								} 
						  			%>
								</select>
                                
								<input type="hidden" name="hiddenZahtev" value="unosKnjige">
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