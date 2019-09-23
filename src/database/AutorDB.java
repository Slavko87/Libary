package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Autor;
import model.Knjiga;

public class AutorDB 
{
	private DBHelper dbhelper = new DBHelper();

	public AutorDB() {super();}
	
	public boolean dodajAutora(Autor autor)
	{
		try 
		{
			String query = "INSERT INTO autor (ime, prezime) VALUES (?, ?)";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			
			ps.setString(1, autor.getIme());
			ps.setString(2, autor.getPrezime());
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
	
	public Autor dajAutora(int id)
	{
		try
		{
			String query = "SELECT * FROM autor WHERE id = ?";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
	
			if (res.next()) 
			{
				String ime = res.getString("ime").trim();
				String prezime = res.getString("prezime").trim();
				Autor autor = new Autor(id, ime, prezime);
				return autor;
			}

			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Autor> dajListuAutora() 
	{
		ArrayList<Autor> lista = new ArrayList<>();
		
		try
		{
			String query = "SELECT * FROM autor";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
						
			while (rs.next()) 
			{
				int id = rs.getInt("id");
				String ime = rs.getString("ime").trim();
				String prezime = rs.getString("prezime").trim();
				Autor autor = new Autor(id, ime, prezime);
				lista.add(autor);
			}
			return lista;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean izmeniAutora(Autor autor)
	{
		String query = "UPDATE autor SET ime = ?, prezime = ? WHERE id = ?";
		try 
		{
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setString(1, autor.getIme());
			ps.setString(2, autor.getPrezime());
			ps.setInt(3, autor.getId());
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
	
	public boolean obrisiAutora(int id)
	{
		try
		{
			String query = "DELETE FROM autor WHERE id = ?";
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
	
	public ArrayList<Knjiga> dajListuKnjigaZaAutora(int idAutora)
	{
		ArrayList<Knjiga> listaKnjiga = new ArrayList<Knjiga>();
		try
		{
			String query = "SELECT * FROM knjigaautor ka JOIN knjiga k ON ka.idKnjige = k.id WHERE idAutora = ?";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setInt(1, idAutora);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				int id = rs.getInt("idKnjige");
				String naziv = rs.getString("naziv").trim();
				int ukupnoKnjiga = rs.getInt("ukupnoKnjiga");
				int trenutniBrojKnjiga = rs.getInt("trenutniBrojKnjiga");
				Knjiga k = new Knjiga(id, naziv, ukupnoKnjiga, trenutniBrojKnjiga);
				listaKnjiga.add(k);
			}
			
			return listaKnjiga;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Autor> pretragaAutoraPoImenuIPrezimenu(String uslov)
	{
		
		ArrayList<Autor> listaAutora = new ArrayList<Autor>();
		try
		{
			uslov = "%" + uslov.toUpperCase() + "%";
			String query = "SELECT * FROM autor c WHERE UPPER(c.ime) LIKE '" + uslov + "' OR UPPER(c.prezime) LIKE '" + uslov + "'";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				int id = rs.getInt("id");
				String ime = rs.getString("ime").trim();
				String prezime = rs.getString("prezime").trim();
				Autor autor = new Autor(id, ime, prezime);
				listaAutora.add(autor);
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
