<%
if (session.getAttribute("ulogovan") != null)
{
	if ((boolean) session.getAttribute("ulogovan") == true)
	{
		int privilegija = (int) session.getAttribute("privilegija");
		if (privilegija != 1)
		{
			%>
			<%response.sendRedirect("login.jsp");%>
			<%
		}
	}
	else
	{
		%>
		<%response.sendRedirect("login.jsp");%>
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



