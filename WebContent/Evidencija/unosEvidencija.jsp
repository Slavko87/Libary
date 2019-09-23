<%@page import="database.KnjigaDB"%>
<%@page import="database.ClanDB"%>
<%@page import="model.Knjiga"%>
<%@page import="model.Clan"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="/header.html"%>
<title>Unos evidencije</title>
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
                            <h4 class="mb-0">UNOS EVIDENCIJE</h4>
                        </div>
                        <div class="card-body" >
                            <form class="form" role="form" id="frmLogin" action="/Libary/ServletEvidencija" method="post">
                            <%
							ArrayList<Clan> listaClanova = new ClanDB().dajListuClanova();
							ArrayList<Knjiga> listaKnjiga = new KnjigaDB().dajListuKnjiga();
							%>
                                <select name="clan[]" size="1" style="margin:auto; display:block; margin-top:10px; margin-bottom:10px;" required>
								<%
								
						    	for(Clan clan:listaClanova)  
								{ 
									%>
									<option value=<%=clan.getId()%>> <%=clan%></option>
									<%
								} 
						  			%>
								</select>
								
								<select name="knjiga[]" size="1" style="margin:auto; display:block; margin-top:10px; margin-bottom:10px;" required>
								<%
								
						    	for(Knjiga knjiga:listaKnjiga)  
								{ 
									%>
									<option value=<%=knjiga.getId()%>> <%=knjiga%></option>
									<%
								} 
						  			%>
								</select>
								<input type="hidden" name="hiddenZahtev" value="unosEvidencije">
                                <button type="submit" class="btn btn-success btn-lg btn-block" id="btnLogin">Unesi</button>
                            </form>
                            <%
							boolean uneta;
							if (request.getAttribute("unetaEvidencija") != null)
							{
								uneta = (boolean) request.getAttribute("unetaEvidencija");
								if (uneta == false)
								{
									
								%>
									Imate preko 5 zaduzenih knjiga, razduzite prvo neku knjigu!
								<%
								}
							}
							%>
                        </div>
                       </div>
                    </div>
            </div>
        </div>
    </div>
   
</div>

</body>
</html>