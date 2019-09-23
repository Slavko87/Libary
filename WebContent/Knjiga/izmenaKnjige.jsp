<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.List"%>
<%@page import="model.Autor"%>
<%@page import="database.AutorDB"%>
<%@page import="model.Knjiga"%>
<%@page import="database.KnjigaDB"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="/header.html"%>
<title>Izmena knjige</title>
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
                            <h4 class="mb-0">IZMENA KNJIGE</h4>
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
								<input type="hidden" name="hiddenZahtev" value="ucitajKnjigu">
								<button type="submit" class="btn btn-success btn-lg btn-block" id="btnLogin">Ucitaj knjigu</button>
							</form>
							
							<form class="form" role="form" id="frmLogin" action="/Libary/ServletKnjiga" method="post">
								<%
								Knjiga knjiga = null;
								if (request.getAttribute("knjiga") != null)
								{
									knjiga = (Knjiga) request.getAttribute("knjiga");
									%> 
									<div>
										<label for="lblusername">ID:</label>
	                                    <input type="text" autofocus="autofocus" class="form-control form-control-lg rounded-0" name="id" id="id" value="<%=knjiga.getId() %>" readonly="readonly">
	                                </div>
									<div>
										<label for="lblusername">Naziv</label>
	                                    <input type="text" autofocus="autofocus" class="form-control form-control-lg rounded-0" name="naziv" id="naziv" value="<%=knjiga.getNaziv() %>" required>
	                                </div>
                                	<div class="form-group">
	                                    <label>Ukupan broj knjiga</label>
	                                    <input type="number" class="form-control form-control-lg rounded-0" id="brojKnjiga" name="ukupanBrojKnjiga" value="<%=knjiga.getUkupnoKnjiga() %>"required>
                                	</div>
                                	<div class="form-group">
	                                    <label>Trenutan broj knjiga</label>
	                                    <input type="number" class="form-control form-control-lg rounded-0" id="brojKnjiga" name="trenutanBrojKnjiga" value="<%=knjiga.getTrenutniBrojKnjiga() %>"required>
                                	</div>
                                	<div>
	                                	<select name="autori[]" size="3" multiple="multiple" style="margin:auto; display:block; margin-top:10px; margin-bottom:10px; width: 100%;" required>
										<%
										ArrayList<Autor> listaAutora = new AutorDB().dajListuAutora();
										ArrayList<Autor> listaAutoraZaKnjigu = new ArrayList<>();
										
										if (request.getAttribute("listaAutoraZaKnjigu") != null)
										{
											listaAutoraZaKnjigu = (ArrayList<Autor>) request.getAttribute("listaAutoraZaKnjigu");
											listaAutora.removeAll(listaAutoraZaKnjigu);
											
											for(Autor l : listaAutoraZaKnjigu)
											{
												%><option value=<%=l.getId()%> selected> <%=l%></option><%
											}
											for(Autor l : listaAutora)
											{
												%><option value=<%=l.getId()%>> <%=l%></option><%
											}
										}
								    	%>
										</select>
                                	</div>
                                	<input type="hidden" name="hiddenZahtev" value="izmenaKnjige">
                                	<button type="submit" class="btn btn-success btn-lg btn-block" id="btnLogin">Sacuvaj promenu</button>
									<%
								}
								%>
                                
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