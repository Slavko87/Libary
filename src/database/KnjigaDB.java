package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Autor;
import model.Knjiga;

public class KnjigaDB 
{
	private DBHelper dbhelper = new DBHelper();
	
	public boolean dodajKnjigu(Knjiga knjiga, ArrayList<Autor> listaAutora)
	{
		try 
		{
			String query = "INSERT INTO knjiga (naziv, ukupnoKnjiga, trenutniBrojKnjiga) VALUES (?, ?, ?)";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			
			ps.setString(1, knjiga.getNaziv());
			ps.setInt(2, knjiga.getUkupnoKnjiga());
			ps.setInt(3, knjiga.getTrenutniBrojKnjiga());
			ps.executeUpdate();
			
			Knjiga knjigaIzBaze = new KnjigaDB().dajKnjigu(UtilDB.vratiPoslednjiID("knjiga", "id"));
			
			for (Autor autor : listaAutora) {
				dodajAutoraKnjizi(knjigaIzBaze.getId(), autor.getId());
			}
			ps.close();
			return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public Knjiga dajKnjigu(int id)
	{
		try
		{
			String query = "SELECT * FROM knjiga WHERE id = ?";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
	
			if (res.next()) 
			{
				String naziv = res.getString("naziv").trim();
				int ukupnoKnjiga = res.getInt("ukupnoKnjiga");
				int trenutniBrojKnjiga = res.getInt("trenutniBrojKnjiga");
				Knjiga k = new Knjiga(id, naziv, ukupnoKnjiga, trenutniBrojKnjiga);
				return k;
			}
			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Knjiga> dajListuKnjiga() 
	{
		ArrayList<Knjiga> lista = new ArrayList<>();
		
		try
		{
			String query = "SELECT * FROM knjiga";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
						
			while (rs.next()) 
			{
				int id = rs.getInt("id");
				String naziv = rs.getString("naziv").trim();
				int ukupnoKnjiga = rs.getInt("ukupnoKnjiga");
				int trenutniBrojKnjiga = rs.getInt("trenutniBrojKnjiga");
				Knjiga k = new Knjiga(id, naziv, ukupnoKnjiga, trenutniBrojKnjiga);
				lista.add(k);
			}
			return lista;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean izmeniKnjigu(Knjiga knjiga)
	{
		String query = "UPDATE knjiga SET naziv = ?, ukupnoKnjiga = ?, trenutniBrojKnjiga = ? WHERE id = ?";
		try 
		{
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setString(1, knjiga.getNaziv());
			ps.setInt(2, knjiga.getUkupnoKnjiga());
			ps.setInt(3, knjiga.getTrenutniBrojKnjiga());
			ps.setInt(4, knjiga.getId());
			ps.executeUpdate();
			ps.close();
			return true;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean izmeniKnjiguIAutore(Knjiga knjiga, ArrayList<Autor> listaAutora)
	{
		String query = "UPDATE knjiga SET naziv = ?, ukupnoKnjiga = ?, trenutniBrojKnjiga = ? WHERE id = ?";
		try 
		{
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setString(1, knjiga.getNaziv());
			ps.setInt(2, knjiga.getUkupnoKnjiga());
			ps.setInt(3, knjiga.getTrenutniBrojKnjiga());
			ps.setInt(4, knjiga.getId());
			ps.executeUpdate();
			obrisiAutoreKnjizi(knjiga.getId());
			for(Autor autor: listaAutora)
			{
				dodajAutoraKnjizi(knjiga.getId(), autor.getId());
			}
			
			ps.close();
			return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean obrisiKnjigu(int id)
	{
		try
		{
			String query = "DELETE FROM knjiga WHERE id = ?";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean dodajAutoraKnjizi(int idKnjige, int idAutora)
	{
		try 
		{
			String query = "INSERT INTO knjigaautor (idKnjige, idAutora) VALUES (?, ?)";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			
			ps.setInt(1, idKnjige);
			ps.setInt(2, idAutora);
			ps.executeUpdate();
			ps.close();
			return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean obrisiAutoreKnjizi(int idKnjige)
	{
		try
		{
			String query = "DELETE FROM knjigaautor WHERE idKnjige = ?";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			
			ps.setInt(1, idKnjige);
			ps.executeUpdate();
			ps.close();
			return true;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
		
	public ArrayList<Knjiga> pretragaKnjiga(String uslov) 
	{
		ArrayList<Knjiga> lista = new ArrayList<>();
		
		try
		{
			uslov = "%" + uslov.toUpperCase() + "%";
			String query = "SELECT * FROM knjiga k JOIN knjigaautor ka JOIN autor a ON k.id = ka.idKnjige AND ka.idAutora = a.id WHERE UPPER(k.naziv) LIKE '" + uslov + "' OR UPPER(a.ime) LIKE '" + uslov + "' OR UPPER(a.prezime) LIKE '" + uslov + "' GROUP BY k.id";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				int id = rs.getInt("id");
				String naziv = rs.getString("naziv").trim();
				int ukupnoKnjiga = rs.getInt("ukupnoKnjiga");
				int trenutniBrojKnjiga = rs.getInt("trenutniBrojKnjiga");
				Knjiga k = new Knjiga(id, naziv, ukupnoKnjiga, trenutniBrojKnjiga);
				lista.add(k);
			}
			return lista;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Autor> dajListuAutoraZaKnjigu(int idKnjige)
	{
		ArrayList<Autor> listaAutora = new ArrayList<Autor>();
		try
		{
			String query = "SELECT * FROM knjigaautor ka JOIN autor a ON ka.idAutora = a.id WHERE idKnjige = ?";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setInt(1, idKnjige);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				int id = rs.getInt("idAutora");
				String ime = rs.getString("ime").trim();
				String prezime = rs.getString("prezime").trim();
				Autor a = new Autor(id, ime, prezime);
				listaAutora.add(a);
			}
			
			return listaAutora;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
