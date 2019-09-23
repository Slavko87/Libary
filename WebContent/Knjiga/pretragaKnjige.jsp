<%@page import="database.KnjigaDB"%>
<%@page import="model.Autor"%>
<%@page import="model.Knjiga"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="/header.html"%>
<title>Pretraga knjige</title>
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
                            <h4 class="mb-0">PRETRAGA KNJIGE</h4>
                        </div>
                        <div class="card-body" >
                            <form class="form" role="form" id="frmLogin" action="/Libary/ServletKnjiga" method="post">
                                <div class="form-group">
	                                <div class="form-group">
                                    <input type="text" autofocus="autofocus" class="form-control form-control-lg rounded-0" name="poljeZaPretragu" id="poljeZaPretragu" placeholder="Pretrazujte">
                                	</div>
								</div>
								<input type="hidden" name="hiddenZahtev" value="pretragaKnjige">
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
		      <th scope="col">ID knjige</th>
		      <th scope="col" style = "width: 200px;">Naziv knjige</th>
		      <th scope="col">Trenutno stanje knjige</th>
		      <th scope="col">Ukupno knjiga u biblioteci</th>
		      <th scope="col" style = "width: 300px;">Autori</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%
		  if (request.getAttribute("listaKnjiga") != null)
		  {
			  ArrayList<Knjiga> listaKnjiga = (ArrayList<Knjiga>) request.getAttribute("listaKnjiga");
			  
			  for (Knjiga knjiga: listaKnjiga)
			  {
				  %>
				  <tr>
				      <th scope="row"><%=knjiga.getId()%></th>
				      <td><%=knjiga.getNaziv() %></td>
				      <td><%=knjiga.getTrenutniBrojKnjiga() %></td>
				      <td><%=knjiga.getUkupnoKnjiga() %></td>
				      <%
				      ArrayList<Autor> listaAutora = new KnjigaDB().dajListuAutoraZaKnjigu(knjiga.getId());
				      String text = "";
				      for (Autor autor: listaAutora)
				      {
				    	  text += autor.getIme() + " " + autor.getPrezime() + ", ";
				      }
				      text = text.substring(0, text.length() - 2);
				      %>
				      <td><%=text %></td>
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