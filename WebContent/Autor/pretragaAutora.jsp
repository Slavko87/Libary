<%@page import="model.Autor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="/header.html"%>
<title>Pretraga autora</title>
</head>
<body>
<%@ include file="/upitMenu.jsp"%>
<div class="container py-5">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
           <div class="row">
                <div class="col-lg-5 col-md-7 col-sm-7 mx-auto" >
                   <div class="card rounded-1" >
                        <div class="card-header">
                            <h4 class="mb-0">PRETRAGA AUTORA</h4>
                        </div>
                        <div class="card-body" >
                            <form class="form" role="form" id="frmLogin" action="/Libary/ServletAutor" method="post">
                                <div class="form-group">
	                                <div class="form-group">
                                    <input type="text" autofocus="autofocus" class="form-control form-control-lg rounded-0" name="poljeZaPretragu" id="poljeZaPretragu" placeholder="Pretrazujte">
                                	</div>
								</div>
								<input type="hidden" name="hiddenZahtev" value="pretragaAutora">
								<button type="submit" class="btn btn-success btn-lg btn-block" id="btnLogin">Trazi</button>
							</form>
						</div>
                       </div>
                    </div>
            </div>
        </div>
    </div>
   
</div>
<div class="container" style = "width: 900px;">
<table class="table table-striped">
		  <thead>
		    <tr>
		      <th scope="col" style = "width: 50px;">ID autora</th>
		      <th scope="col" style = "width: 200px;">Ime autora</th>
		      <th scope="col" style = "width: 200px;">Prezime autora</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%
		  if (request.getAttribute("listaAutora") != null)
		  {
			  ArrayList<Autor> listaAutora = (ArrayList<Autor>) request.getAttribute("listaAutora");
			  
			  for (Autor autor: listaAutora)
			  {
				  %>
				  <tr>
				      <th scope="row"><%=autor.getId()%></th>
				      <td><%=autor.getIme()%></td>
				      <td><%=autor.getPrezime()%></td>
				  </tr>
				  <%
			  }
			  %>
			  </tbody>
			</table>
			<%
		  	}
			%>
</div>
</body>
</html>