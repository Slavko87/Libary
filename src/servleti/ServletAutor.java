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
import model.Autor;

@WebServlet("/ServletAutor")
public class ServletAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAutor() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String zahtev = request.getParameter("hiddenZahtev");
		
		if (zahtev.equalsIgnoreCase("unosAutora"))
			unosAutora(request, response);
		if (zahtev.equalsIgnoreCase("ucitajAutora"))
			ucitajAutora(request, response);
		if (zahtev.equalsIgnoreCase("izmenaAutora"))
			izmenaAutora(request, response);
		if (zahtev.equalsIgnoreCase("brisiAutora"))
			brisiAutora(request, response);
		if (zahtev.equalsIgnoreCase("pretragaAutora"))
			pretragaAutora(request, response);
		
	}
	
	public void unosAutora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		
		Autor a = new Autor(ime, prezime);
		new AutorDB().dodajAutora(a);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Autor/unosAutora.jsp");
		rd.forward(request, response);
	}
	
	public void ucitajAutora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] autor = request.getParameterValues("autori[]");
		int idObelezenogAutora = Integer.parseInt(autor[0]);
		
		Autor obelezeniAutor = new AutorDB().dajAutora(idObelezenogAutora);
		request.setAttribute("autor", obelezeniAutor);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Autor/izmenaAutora.jsp");
		rd.forward(request, response);
	}
	
	public void izmenaAutora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		
		Autor a = new Autor(Integer.parseInt(id), ime, prezime);
		new AutorDB().izmeniAutora(a);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Autor/izmenaAutora.jsp");
		rd.forward(request, response);
	}
	
	public void brisiAutora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] autor = request.getParameterValues("autori[]");
		
		if (autor[0] != null)
			new AutorDB().obrisiAutora(Integer.parseInt(autor[0]));
		
		RequestDispatcher rd = request.getRequestDispatcher("/Autor/brisanjeAutora.jsp");
		rd.forward(request, response);
	}
	
	public void pretragaAutora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String uslov = request.getParameter("poljeZaPretragu");
		ArrayList<Autor> listaAutora = new AutorDB().pretragaAutoraPoImenuIPrezimenu(uslov);
		request.setAttribute("listaAutora", listaAutora);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Autor/pretragaAutora.jsp");
		rd.forward(request, response);
	}

}
