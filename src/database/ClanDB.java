package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Clan;
import model.EvidencijaIznajmljenihKnjiga;
import model.Knjiga;

public class ClanDB 
{
private DBHelper dbhelper = new DBHelper();
	
	public boolean dodajClana(Clan clan)
	{
		try 
		{
			String query = "INSERT INTO clan (ime, prezime, email, sifra, brojZaduzenihKnjiga, privilegija) VALUES (?, ?, ?, SHA1(?), ?, ?)";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			
			ps.setString(1, clan.getIme());
			ps.setString(2, clan.getPrezime());
			ps.setString(3, clan.getEmail());
			ps.setString(4, clan.getSifra());
			ps.setInt(5, clan.getBrojZaduzenihKnjiga());
			ps.setInt(6, clan.getPrivilegija());
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
	
	public Clan dajClana(int id)
	{
		try
		{
			String query = "SELECT * FROM clan WHERE id = ?";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
	
			if (res.next()) 
			{
				String ime = res.getString("ime").trim();
				String prezime = res.getString("prezime").trim();
				String email = res.getString("email").trim();
				String sifra = res.getString("sifra").trim();
				int brojZaduzenihKnjiga = res.getInt("brojZaduzenihKnjiga");
				int privilegija = res.getInt("privilegija");
				
				Clan clan = new Clan(id, ime, prezime, email, sifra, brojZaduzenihKnjiga, privilegija);
				return clan;
			}

			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Clan dajClanaPoEmailu(String email)
	{
		try
		{
			String query = "SELECT * FROM clan WHERE email = ?";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setString(1, email);
			ResultSet res = ps.executeQuery();
	
			if (res.next()) 
			{
				int id = res.getInt("id");
				String ime = res.getString("ime").trim();
				String prezime = res.getString("prezime").trim();
				String sifra = res.getString("sifra").trim();
				int brojZaduzenihKnjiga = res.getInt("brojZaduzenihKnjiga");
				int privilegija = res.getInt("privilegija");
				Clan clan = new Clan(id, ime, prezime, email, sifra, brojZaduzenihKnjiga, privilegija);
				return clan;
			}

			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public ArrayList<Clan> dajListuClanova() 
	{
		ArrayList<Clan> lista = new ArrayList<>();
		
		try
		{
			String query = "SELECT * FROM clan";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
						
			while (rs.next()) 
			{
				int id = rs.getInt("id");
				String ime = rs.getString("ime").trim();
				String prezime = rs.getString("prezime").trim();
				String email = rs.getString("email").trim();
				String sifra = rs.getString("sifra").trim();
				int brojZaduzenihKnjiga = rs.getInt("brojZaduzenihKnjiga");
				int privilegija = rs.getInt("privilegija");
				Clan clan = new Clan(id, ime, prezime, email, sifra, brojZaduzenihKnjiga, privilegija);
				lista.add(clan);
			}
			return lista;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Clan> dajListuZaduzenihClanova() 
	{
		ArrayList<Clan> lista = new ArrayList<>();
		
		try
		{
			String query = "SELECT * FROM clan WHERE brojZaduzenihKnjiga > 0";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
						
			while (rs.next()) 
			{
				int id = rs.getInt("id");
				String ime = rs.getString("ime").trim();
				String prezime = rs.getString("prezime").trim();
				String email = rs.getString("email").trim();
				String sifra = rs.getString("sifra").trim();
				int brojZaduzenihKnjiga = rs.getInt("brojZaduzenihKnjiga");
				int privilegija = rs.getInt("privilegija");
				Clan clan = new Clan(id, ime, prezime, email, sifra, brojZaduzenihKnjiga, privilegija);
				lista.add(clan);
			}
			return lista;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean izmeniClana(Clan clan)
	{
		String query = "UPDATE clan SET ime = ?, prezime = ?, email = ?, sifra = SHA1(?), brojZaduzenihKnjiga = ?, privilegija = ? WHERE id = ?";
		try 
		{
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setString(1, clan.getIme());
			ps.setString(2, clan.getPrezime());
			ps.setString(3, clan.getEmail());
			ps.setString(4, clan.getSifra());
			ps.setInt(5, clan.getBrojZaduzenihKnjiga());
			ps.setInt(6, clan.getBrojZaduzenihKnjiga());
			ps.setInt(7, clan.getId());
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
	
	public boolean razduziClana(Clan clan)
	{
		String query = "UPDATE clan SET brojZaduzenihKnjiga = ? WHERE id = ?";
		try 
		{
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setInt(1, clan.getBrojZaduzenihKnjiga());
			ps.setInt(2, clan.getId());
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
	
	public boolean obrisiClana(int id)
	{
		try
		{
			String query = "DELETE FROM clan WHERE id = ?";
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
	
	public ArrayList<Clan> pretragaClanovaPoImenuIPrezimenu(String uslov)
	{
		ArrayList<Clan> listaClanova = new ArrayList<Clan>();
		
		try
		{
			uslov = "%" + uslov.toUpperCase() + "%";
			String query = "SELECT * FROM clan c WHERE UPPER(c.ime) LIKE '" + uslov + "' OR UPPER(c.prezime) LIKE '" + uslov + "'";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				int id = rs.getInt("id");
				String ime = rs.getString("ime").trim();
				String prezime = rs.getString("prezime").trim();
				String email = rs.getString("email");
				String sifra = rs.getString("sifra");
				int brojZaduzenihKnjiga = rs.getInt("brojZaduzenihKnjiga");
				int privilegija = rs.getInt("privilegija");
				Clan clan = new Clan(id, ime, prezime, email, sifra, brojZaduzenihKnjiga, privilegija);
				listaClanova.add(clan);
			}
			return listaClanova;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public HashMap<Knjiga, EvidencijaIznajmljenihKnjiga> zaduzeneKnjigeOdClana(int idClana)
	{
		HashMap<Knjiga, EvidencijaIznajmljenihKnjiga> mapZaduzeneKnjigeOdClana = new HashMap<Knjiga, EvidencijaIznajmljenihKnjiga>();
		try
		{
			String query = "SELECT e.id AS idEvidencije, k.id AS idKnjige, k.naziv AS nazivKnjige, k.ukupnoKnjiga AS ukupnoKnjiga, k.trenutniBrojKnjiga AS trenutniBrojKnjiga FROM evidencijaiznajmljenihknjiga e JOIN clan c JOIN knjiga k ON e.idClana=c.id AND e.idKnjige=k.id WHERE e.datumVracanja IS NULL AND c.id = " + idClana;
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
						
			while (rs.next()) 
			{
				int idKnjige = rs.getInt("idKnjige");
				Knjiga k = new KnjigaDB().dajKnjigu(idKnjige);
				int idEvidencije = rs.getInt("idEvidencije");
				EvidencijaIznajmljenihKnjiga e = new EvidencijaIznajmljenihKnjigaDB().dajEvidenciju(idEvidencije);
				mapZaduzeneKnjigeOdClana.put(k, e);
			}
			return mapZaduzeneKnjigeOdClana;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		
	}
}
