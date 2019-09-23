package servleti;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ClanDB;
import database.EvidencijaIznajmljenihKnjigaDB;
import database.KnjigaDB;
import model.Clan;
import model.EvidencijaIznajmljenihKnjiga;
import model.Knjiga;

@WebServlet("/ServletEvidencija")
public class ServletEvidencija extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletEvidencija() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String zahtev = request.getParameter("hiddenZahtev");
		
		if (zahtev.equalsIgnoreCase("unosEvidencije"))
			unesiEvidenciju(request, response);
		if (zahtev.equalsIgnoreCase("ucitajClanove"))
			ucitajZaduzeneClanove(request, response);
		if (zahtev.equalsIgnoreCase("razduziKnjigu"))
			razduziKnjigu(request, response);
	}
	
	public void unesiEvidenciju(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		boolean unetaEvidencija = false;
		String [] clanS = request.getParameterValues("clan[]");
		int idClana = Integer.parseInt(clanS[0]);
		Clan clan = new ClanDB().dajClana(idClana);
		
		String [] knjigaS = request.getParameterValues("knjiga[]");
		int idKnjige = Integer.parseInt(knjigaS[0]);
		Knjiga knjiga = new KnjigaDB().dajKnjigu(idKnjige);
		
		if (knjiga.getTrenutniBrojKnjiga() > 0 && clan.getBrojZaduzenihKnjiga() <5)
		{
			EvidencijaIznajmljenihKnjiga e = new EvidencijaIznajmljenihKnjiga(clan, knjiga);
			new EvidencijaIznajmljenihKnjigaDB().dodajEvidenciju(e);
			unetaEvidencija = true;
		}
		else
			unetaEvidencija = false;
		
		request.setAttribute("unetaEvidencija", unetaEvidencija);
		RequestDispatcher rd = request.getRequestDispatcher("/Evidencija/unosEvidencija.jsp");
		rd.forward(request, response);
	}
	
	public void ucitajZaduzeneClanove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String [] clanS = request.getParameterValues("clanovi[]");
		int idClana = Integer.valueOf(clanS[0]);
		
		HashMap<Knjiga, EvidencijaIznajmljenihKnjiga> mapZaduzeneKnjigeOdClana = new ClanDB().zaduzeneKnjigeOdClana(idClana);
		
		request.setAttribute("mapZaduzeneKnjigeOdClana", mapZaduzeneKnjigeOdClana);
		RequestDispatcher rd = request.getRequestDispatcher("/Evidencija/razduzivanjeKnjige.jsp");
		rd.forward(request, response);
	}
	
	public void razduziKnjigu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String [] idRazduzenjas = request.getParameterValues("knjige[]");
		
		EvidencijaIznajmljenihKnjiga evidencija = new EvidencijaIznajmljenihKnjigaDB().dajEvidenciju(Integer.parseInt(idRazduzenjas[0]));
		
		EvidencijaIznajmljenihKnjigaDB edb = new EvidencijaIznajmljenihKnjigaDB();
		edb.izmeniEvidenciju(evidencija);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Evidencija/razduzivanjeKnjige.jsp");
		rd.forward(request, response);
	}
	
}
