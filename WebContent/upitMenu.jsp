<%
/* System.out.println("SESIJA - " + session.getAttribute("ulogovan")); */
if (session.getAttribute("ulogovan") != null)
{
	if ((boolean) session.getAttribute("ulogovan") == true)
	{
		int privilegija = (int) session.getAttribute("privilegija");
		if (privilegija == 1)
		{
			%>
			<%@ include file="/Menu/adminMenu.html"%>
			<%
		}
		else
		{
			%>
			<%@ include file="/Menu/clanMenu.html"%>
			<%
		}
	}
	else
	{
		%>
		<%response.sendRedirect("index.jsp");%>
		<%
	}
}
else
{
	%>
	<%response.sendRedirect("index.jsp");%>
	<%
}
%>
<!--  
Sesija je true ako se korisnik uspesno uloguje
Sesija je false ako korisnik ne uspe da se uloguje, pogresan mail i sifra
Sesija je null kada se korisnik izloguje
-->
