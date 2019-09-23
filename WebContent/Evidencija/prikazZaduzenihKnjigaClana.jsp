<%@page import="model.Clan"%>
<%@page import="database.EvidencijaIznajmljenihKnjigaDB"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="/header.html"%>
<title>Insert title here</title>
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
                            <h4 class="mb-0" style="text-align:center;">Prikaz trenutno zaduzenih knjiga</h4>
                        </div>
                       </div>
                    </div>
            </div>
        </div>
    </div>
   
</div>
<div class="container py-5">
<table class="table table-striped" style="text-align:center; margin-top: 10px; width:1150px;">
							  <thead>
							    <tr>
							      <th scope="col">ID evidencije</th>
							      <th scope="col">ID korisnika</th>
							      <th scope="col">Ime</th>
							      <th scope="col">Prezime</th>
							      <th scope="col">Naziv knjige</th>
							      <th scope="col">Datum zaduzenja</th>
							      <th scope="col">Broj dana kod korisnika</th>
							    </tr>
							  </thead>
							  <tbody>
							  <%
							 	 Clan c = (Clan) session.getAttribute("clan");
								 ResultSet rs = new EvidencijaIznajmljenihKnjigaDB().dajRSZaduzenihKnjigaClana(c.getId());
								 
								 while(rs.next())
								 {
									 %>
									  <tr>
									      <th scope="row"><%=rs.getInt("idEvidencije")%></th>
									      <td><%=rs.getInt("idClana") %></td>
									      <td><%=rs.getString("imeClana") %></td>
									      <td><%=rs.getString("prezimeClana") %></td>
									      <td><%=rs.getString("nazivKnjige") %></td>
									      <td><%=rs.getTimestamp("datumIznajmljivanja") %></td>
									      <%
										      
									      if(rs.getLong("brojDanaKodKorisnika") > 10L && rs.getTimestamp("datumVracanja") == null){
									      %>
									      	 <td style="background-color:red;"><%=rs.getLong("brojDanaKodKorisnika") %></td>
									      <% 
									     	 }
									      else
									      {
									    	 %>
									    	 <td ><%=rs.getLong("brojDanaKodKorisnika") %></td>
									  	 	<%
									      }
									     %>	
									 </tr> 
									<% 
								 		}
									%>  
									
								  </tbody>
								</table>
</div>
</body>
</html>