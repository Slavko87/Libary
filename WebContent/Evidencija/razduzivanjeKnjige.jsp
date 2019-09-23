<%@page import="java.util.Map"%>
<%@page import="model.EvidencijaIznajmljenihKnjiga"%>
<%@page import="model.Knjiga"%>
<%@page import="java.util.HashMap"%>
<%@page import="database.ClanDB"%>
<%@page import="model.Clan"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="/header.html"%>
<title>Razduzi knjigu</title>
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
                            <h4 class="mb-0">Razduzi knjigu</h4>
                        </div>
                        <div class="card-body" >
                            <form class="form" role="form" id="frmLogin" action="/Libary/ServletEvidencija" method="post" style="text-align:center;">
                                <div class="form-group" >
	                                <select name="clanovi[]" >
									<%
									ArrayList<Clan> listaClanova = new ClanDB().dajListuZaduzenihClanova();
							    	for(Clan clan:listaClanova)  
									{ 
										%>
										<option value=<%=clan.getId()%>> <%=clan%></option>
										<%
									} 
							  			%>
									</select><br>
								</div>
								<input type="hidden" name="hiddenZahtev" value="ucitajClanove">
								<button type="submit" class="btn btn-success btn-lg btn-block" id="btnLogin">Ucitaj zaduzene knjige clana</button>
							</form>
							
							<%
				
							HashMap<Knjiga, EvidencijaIznajmljenihKnjiga> mapZaduzeneKnjigeOdClana = new HashMap<Knjiga, EvidencijaIznajmljenihKnjiga>();
							if (request.getAttribute("mapZaduzeneKnjigeOdClana") != null)
							{
								Clan clan = (Clan) request.getAttribute("odabranClan");
								mapZaduzeneKnjigeOdClana = (HashMap<Knjiga, EvidencijaIznajmljenihKnjiga>) request.getAttribute("mapZaduzeneKnjigeOdClana");
								
								%>
								<form action="/Libary/ServletEvidencija" method="post" style="text-align:center; margin-top: 10px;">
									<select name="knjige[]">
									<%
									
							    	for(Map.Entry<Knjiga, EvidencijaIznajmljenihKnjiga> e : mapZaduzeneKnjigeOdClana.entrySet())  
									{ 
										%>
										<option value=<%=e.getValue().getId()%>> <%=e.getKey()%></option>
										<%
									} 
							  			%>
									</select><br>
									<input type="hidden" name="hiddenZahtev" value="razduziKnjigu">
									<input type="submit" class="btn btn-success btn-lg btn-block" value="Razduzi knjigu" id="razduziKnjigu" style="margin-top: 10px;">
								</form>
								<%
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