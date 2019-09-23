package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Clan;
import model.EvidencijaIznajmljenihKnjiga;
import model.Knjiga;

public class EvidencijaIznajmljenihKnjigaDB 
{
	private DBHelper dbhelper = new DBHelper();

	public EvidencijaIznajmljenihKnjigaDB() {super();}
	
	public boolean dodajEvidenciju(EvidencijaIznajmljenihKnjiga evidencija)
	{
		try 
		{
			String query = "INSERT INTO evidencijaiznajmljenihknjiga (idClana, idKnjige, datumIznajmljivanja) VALUES (?, ?, ?)";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			
			ps.setInt(1, evidencija.getClan().getId());
			ps.setInt(2, evidencija.getKnjiga().getId());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
			Knjiga k = new KnjigaDB().dajKnjigu(evidencija.getKnjiga().getId());
			smanjiBrojRaspolozivihKnjiga(k);
			Clan c = new ClanDB().dajClana(evidencija.getClan().getId());
			povecajBrojZaduzenihKnjiga(c);
			ps.close();
			return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public EvidencijaIznajmljenihKnjiga dajEvidenciju(int id)
	{
		try
		{
			String query = "SELECT * FROM evidencijaiznajmljenihknjiga WHERE id = ?";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
	
			if (res.next()) 
			{
				
				int idClana = res.getInt("idClana");
				int idKnjige = res.getInt("idKnjige");
				Clan clan = new ClanDB().dajClana(idClana);
				Knjiga knjiga = new KnjigaDB().dajKnjigu(idKnjige);
				Timestamp datumI = res.getTimestamp("datumIznajmljivanja");
				Timestamp datumV = res.getTimestamp("datumVracanja");
				
				EvidencijaIznajmljenihKnjiga evidencija = new EvidencijaIznajmljenihKnjiga(id, clan, knjiga, datumI, datumV);
				return evidencija;
			}

			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<EvidencijaIznajmljenihKnjiga> dajListuEvidencija() 
	{
		ArrayList<EvidencijaIznajmljenihKnjiga> lista = new ArrayList<>();
		
		try
		{
			String query = "SELECT * FROM evidencijaiznajmljenihknjiga";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
						
			while (rs.next()) 
			{
				int id = rs.getInt("id");
				int idClana = rs.getInt("idClana");
				int idKnjige = rs.getInt("idKnjige");
				Clan clan = new ClanDB().dajClana(idClana);
				Knjiga knjiga = new KnjigaDB().dajKnjigu(idKnjige);
				Timestamp datumI = rs.getTimestamp("datumIznajmljivanja");
				Timestamp datumV = rs.getTimestamp("datumVracanja");
				
				EvidencijaIznajmljenihKnjiga evidencija = new EvidencijaIznajmljenihKnjiga(id, clan, knjiga, datumI, datumV);
				lista.add(evidencija);
			}
			return lista;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	//za razduzivanje
	public boolean izmeniEvidenciju(EvidencijaIznajmljenihKnjiga evidencija)
	{
		String query = "UPDATE evidencijaiznajmljenihknjiga SET idClana = ?, idKnjige = ?, datumVracanja = ? WHERE id = ?";
		try 
		{
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ps.setInt(1, evidencija.getClan().getId());
			ps.setInt(2, evidencija.getKnjiga().getId());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, evidencija.getId());
			ps.executeUpdate();
			smanjiBrojZaduzenihKnjiga(new ClanDB().dajClana(evidencija.getClan().getId()));
			povecajBrojRaspolozivihKnjiga(new KnjigaDB().dajKnjigu(evidencija.getKnjiga().getId()));
			ps.close();
			return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean obrisiEvidenciju(int id)
	{
		try
		{
			String query = "DELETE FROM evidencijaiznajmljenihknjiga WHERE id = ?";
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
	
//	public ResultSet dajListuZaduzenihKnjiga()
//	{
//		
//		try
//		{
//			String query = "SELECT e.id AS idEvidencije, c.id AS idClana, c.ime AS imeClana, c.prezime AS prezimeClana, k.naziv AS nazivKnjige, e.datumIznajmljivanja AS datumIznajmljivanja, e.datumVracanja AS datumVracanja FROM evidencijaiznajmljenihknjiga e JOIN clan c JOIN knjiga k ON e.idClana = c.id AND e.idKnjige = k.id WHERE e.datumVracanja IS NULL";
//			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
//			ResultSet rs = ps.executeQuery();
//			return rs;			
//			
//		}
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	public ResultSet dajListuSvihZaduzenja()
	{
		try
		{
			String query = "SELECT datediff(NOW(), e.datumIznajmljivanja) AS brojDanaKodKorisnika, e.id AS idEvidencije, c.id AS idClana, c.ime AS imeClana, c.prezime AS prezimeClana, k.naziv AS nazivKnjige, e.datumIznajmljivanja AS datumIznajmljivanja, e.datumVracanja AS datumVracanja FROM evidencijaiznajmljenihknjiga e JOIN clan c JOIN knjiga k ON e.idClana = c.id AND e.idKnjige = k.id";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			return rs;			
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet dajRSZaduzenihKnjiga()
	{
		try
		{
			String query = "SELECT datediff(NOW(), e.datumIznajmljivanja) AS brojDanaKodKorisnika, e.id AS idEvidencije, c.id AS idClana, c.ime AS imeClana, c.prezime AS prezimeClana, k.naziv AS nazivKnjige, e.datumIznajmljivanja AS datumIznajmljivanja, e.datumVracanja AS datumVracanja FROM evidencijaiznajmljenihknjiga e JOIN clan c JOIN knjiga k ON e.idClana = c.id AND e.idKnjige = k.id WHERE e.datumVracanja IS NULL";
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			return rs;			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet dajListuSvihZaduzenjaClana(int idClana)
	{
		try
		{
			String query = "SELECT datediff(NOW(), e.datumIznajmljivanja) AS brojDanaKodKorisnika, e.id AS idEvidencije, c.id AS idClana, c.ime AS imeClana, c.prezime AS prezimeClana, k.naziv AS nazivKnjige, e.datumIznajmljivanja AS datumIznajmljivanja, e.datumVracanja AS datumVracanja FROM evidencijaiznajmljenihknjiga e JOIN clan c JOIN knjiga k ON e.idClana = c.id AND e.idKnjige = k.id WHERE c.id = " + idClana;
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			return rs;			
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet dajRSZaduzenihKnjigaClana(int idClana)
	{
		try
		{
			String query = "SELECT datediff(NOW(), e.datumIznajmljivanja) AS brojDanaKodKorisnika, e.id AS idEvidencije, c.id AS idClana, c.ime AS imeClana, c.prezime AS prezimeClana, k.naziv AS nazivKnjige, e.datumIznajmljivanja AS datumIznajmljivanja, e.datumVracanja AS datumVracanja FROM evidencijaiznajmljenihknjiga e JOIN clan c JOIN knjiga k ON e.idClana = c.id AND e.idKnjige = k.id WHERE e.datumVracanja IS NULL AND c.id = " + idClana;
			PreparedStatement ps = dbhelper.getConn().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			return rs;			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private void smanjiBrojRaspolozivihKnjiga(Knjiga knjiga)
	{
		KnjigaDB knjigadb = new KnjigaDB();
		knjiga.setTrenutniBrojKnjiga(knjiga.getTrenutniBrojKnjiga() - 1);
		knjigadb.izmeniKnjigu(knjiga);
	}
	
	private void povecajBrojRaspolozivihKnjiga(Knjiga knjiga)
	{
		KnjigaDB knjigadb = new KnjigaDB();
		knjiga.setTrenutniBrojKnjiga(knjiga.getTrenutniBrojKnjiga() + 1);
		knjigadb.izmeniKnjigu(knjiga);
	}
	
	private void povecajBrojZaduzenihKnjiga(Clan clan)
	{
		ClanDB clandb = new ClanDB();
		clan.setBrojZaduzenihKnjiga(clan.getBrojZaduzenihKnjiga() + 1);
		clandb.razduziClana(clan);
	}
	
	private void smanjiBrojZaduzenihKnjiga(Clan clan)
	{
		ClanDB clandb = new ClanDB();
		clan.setBrojZaduzenihKnjiga(clan.getBrojZaduzenihKnjiga() - 1);
		clandb.razduziClana(clan);
	}
	
}
