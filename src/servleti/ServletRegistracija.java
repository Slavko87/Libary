package servleti;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ClanDB;
import model.Clan;

@WebServlet("/ServletRegistracija")
public class ServletRegistracija extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ServletRegistracija() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String zahtev = request.getParameter("hiddenZahtev");
		
		if (zahtev.equalsIgnoreCase("registracijaClana"))
			registracijaClana(request, response);
	}
	
	public void registracijaClana (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		boolean registrovan = true;
		String ime = request.getParameter("ime").trim();
		String prezime = request.getParameter("prezime").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		String password2 = request.getParameter("password2").trim();
		
		Clan daLiPostojiSaTimEmailom = new ClanDB().dajClanaPoEmailu(email);
		if (daLiPostojiSaTimEmailom == null && password.equals(password2) && (password.isEmpty() != true || password.equals("") == false))
		{
			registrovan = true;
			new ClanDB().dodajClana(new Clan(ime, prezime, email, password, 0, 2));
			request.setAttribute("registrovan", registrovan);
			response.sendRedirect("index.jsp");
		}
		else
		{
			registrovan = false;
			RequestDispatcher rd = request.getRequestDispatcher("registracijaClana.jsp");
			request.setAttribute("registrovan", registrovan);
			rd.forward(request, response);
		}
	}

}
