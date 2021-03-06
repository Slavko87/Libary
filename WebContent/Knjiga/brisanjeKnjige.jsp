<%@page import="database.KnjigaDB"%>
<%@page import="model.Knjiga"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="/header.html"%>
<title>Brisanje knjige</title>
</head>
<body>
<%@ include file="/upitMenu.jsp"%>
<%@ include file="/upitAdminStranice.jsp"%>
<div class="container py-5">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
           <div class="row">
                <div class="col-lg-5 col-md-7 col-sm-7 mx-auto" >
                   <div class="card rounded-1" >
                        <div class="card-header">
                            <h4 class="mb-0">BRISANJE KNJIGE</h4>
                        </div>
                        <div class="card-body" >
                            <form class="form" role="form" id="frmLogin" action="/Libary/ServletKnjiga" method="post">
                                <div class="form-group">
	                                <select name="knjige[]" style="margin:auto; display:block; margin-top:10px; margin-bottom:10px; width: 100%;" required>
									<%
									ArrayList<Knjiga> listaKnjiga = new KnjigaDB().dajListuKnjiga();
							    	for(Knjiga list:listaKnjiga)  
									{ 
										%>
										<option value=<%=list.getId()%>> <%=list%></option>
										<%
									} 
							  			%>
									</select>
								</div>
								<input type="hidden" name="hiddenZahtev" value="brisiKnjigu">
								<button type="submit" class="btn btn-success btn-lg btn-block" id="btnLogin">Obrisi knjigu</button>
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