package servleti;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ClanDB;
import model.Clan;
import util.Sha1;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {private static final long serialVersionUID = 1L;     
    
    public ServletLogin() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		s.setAttribute("ulogovan", false);
		s.invalidate();
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String zahtev = request.getParameter("hiddenZahtev");
		
		if (zahtev.equalsIgnoreCase("logovanje"))
			ulogujSe(request,response);
	}
	
	public void ulogujSe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		HttpSession s = request.getSession();
		String email = request.getParameter("email");
		String shaSifra = "";
		try {
			shaSifra = Sha1.sha1(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		Clan clan = new ClanDB().dajClanaPoEmailu(email);
		
		if (clan != null && clan.getSifra().equals(shaSifra))
		{
			System.out.println("Ulogovao se clan - " + clan);
			s.setAttribute("privilegija", clan.getPrivilegija());
			s.setAttribute("email", request.getAttribute("email"));
			s.setAttribute("sifra", request.getAttribute("sifra"));
			s.setAttribute("ulogovan", true);
			s.setAttribute("clan", clan);
			response.sendRedirect("Biblioteka.jsp");
		}
		else
		{
			s.setAttribute("ulogovan", false);
			response.sendRedirect("index.jsp");
		}
	}
}
