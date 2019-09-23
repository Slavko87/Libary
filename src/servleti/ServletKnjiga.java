package servleti;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AutorDB;
import database.KnjigaDB;
import model.Autor;
import model.Knjiga;

@WebServlet("/ServletKnjiga")
public class ServletKnjiga extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletKnjiga() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String zahtev = request.getParameter("hiddenZahtev");
		
		if (zahtev.equalsIgnoreCase("unosKnjige"))
			unesiKnjigu(request, response);
		else if (zahtev.equalsIgnoreCase("ucitajKnjigu"))
			ucitajKnjigu(request, response);
		else if (zahtev.equalsIgnoreCase("izmenaKnjige"))
			izmenaKnjige(request, response);
		else if (zahtev.equalsIgnoreCase("brisiKnjigu"))
			brisiKnjigu(request, response);
		else if (zahtev.equalsIgnoreCase("pretragaKnjige"))
			pretragaKnjiga(request, response);
		
	}
	
	public void unesiKnjigu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String naziv = request.getParameter("naziv");
		String brojKnjiga = request.getParameter("brojKnjiga");
		
		String [] listaObelezenih = request.getParameterValues("autori[]");
		
		ArrayList<Autor> listaAutora = new ArrayList<Autor>();
		
		for (int i=0; i < listaObelezenih.length; i++)
		{
			listaAutora.add(new AutorDB().dajAutora(Integer.parseInt(listaObelezenih[i])));
		}
		
		Knjiga knjiga = new Knjiga(naziv, Integer.parseInt(brojKnjiga));
		new KnjigaDB().dodajKnjigu(knjiga, listaAutora);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Knjiga/unosKnjige.jsp");
		rd.forward(request, response);
		//response.sendRedirect("/Libary/Knjiga/unosKnjige.jsp");
	}
	
	public void ucitajKnjigu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] knjiga = request.getParameterValues("knjige[]");
		int idObelezeneKnjige = Integer.parseInt(knjiga[0]);
		
		Knjiga obelezenaKnjiga = new KnjigaDB().dajKnjigu(idObelezeneKnjige);
		ArrayList<Autor> listaAutoraZaKnjigu = new KnjigaDB().dajListuAutoraZaKnjigu(idObelezeneKnjige);
		request.setAttribute("knjiga", obelezenaKnjiga);
		request.setAttribute("listaAutoraZaKnjigu", listaAutoraZaKnjigu);
		RequestDispatcher rd = request.getRequestDispatcher("/Knjiga/izmenaKnjige.jsp");
		rd.forward(request, response);
	}
	
	public void izmenaKnjige(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idKnjigeS = request.getParameter("id");
		int idKnjige = Integer.valueOf(idKnjigeS);
		String nazivKnjige = request.getParameter("naziv");
		String brojKnjigaS = request.getParameter("ukupanBrojKnjiga");
		int brojKnjiga = Integer.valueOf(brojKnjigaS);
		String trenutniBrojKnjigaS = request.getParameter("trenutanBrojKnjiga");
		int trenutniBrojKnjiga = Integer.valueOf(trenutniBrojKnjigaS);
		Knjiga k = new Knjiga(idKnjige, nazivKnjige, brojKnjiga, trenutniBrojKnjiga);
		
		String [] autori = request.getParameterValues("autori[]");
		ArrayList<Autor> listaAutora = new ArrayList<Autor>();
		for(int i = 0; i < autori.length; i++)
		{
			listaAutora.add(new AutorDB().dajAutora(Integer.parseInt(autori[i])));
		}
		new KnjigaDB().izmeniKnjiguIAutore(k, listaAutora);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Knjiga/izmenaKnjige.jsp");
		rd.forward(request, response);
	}
	
	public void brisiKnjigu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] knjiga = request.getParameterValues("knjige[]");
		
		if (knjiga[0] != null)
			new KnjigaDB().obrisiKnjigu(Integer.parseInt(knjiga[0]));
		
		RequestDispatcher rd = request.getRequestDispatcher("/Knjiga/brisanjeKnjige.jsp");
		rd.forward(request, response);
		
	}
	
	public void pretragaKnjiga(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String uslov = request.getParameter("poljeZaPretragu");
		ArrayList<Knjiga> listaKnjiga = new KnjigaDB().pretragaKnjiga(uslov);
		request.setAttribute("listaKnjiga", listaKnjiga);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Knjiga/pretragaKnjige.jsp");
		rd.forward(request, response);
	}
}
