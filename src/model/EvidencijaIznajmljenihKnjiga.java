package model;

import java.sql.Timestamp;

import database.UtilDB;

public class EvidencijaIznajmljenihKnjiga 
{
	private int id;
	private Clan clan;
	private Knjiga knjiga;
	private Timestamp datumIznajmljivanja;
	private Timestamp datumVracanja;
	
	public EvidencijaIznajmljenihKnjiga(Clan clan, Knjiga knjiga) 
	{
		super();
		this.clan = clan;
		this.knjiga = knjiga;
		this.datumIznajmljivanja = new Timestamp(System.currentTimeMillis());
		this.datumVracanja = null;
	}

	public EvidencijaIznajmljenihKnjiga(int id, Clan clan, Knjiga knjiga, Timestamp datumIznajmljivanja,
			Timestamp datumVracanja) 
	{
		super();
		this.id = id;
		this.clan = clan;
		this.knjiga = knjiga;
		this.datumIznajmljivanja = datumIznajmljivanja;
		this.datumVracanja = datumVracanja;
	}
	
	@Override
	public String toString()
	{
		String text;
		text = id + " " + knjiga.getNaziv() + " - " + clan.getIme() + " " + clan.getPrezime() + " , datum iznajmljivanja: " + UtilDB.konvertujeTimestampUString(datumIznajmljivanja);
		if (datumVracanja == null)
			text += " , NIJE RAZDUZENO";
		else
			text+= " " + UtilDB.konvertujeTimestampUString(datumVracanja);
		return text;
	}

	//GETERI I SETERI
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public Clan getClan() {return clan;}
	public void setClan(Clan clan) {this.clan = clan;}
	public Knjiga getKnjiga() {return knjiga;}
	public void setKnjiga(Knjiga knjiga) {this.knjiga = knjiga;}
	public Timestamp getDatumIznajmljivanja() {return datumIznajmljivanja;}
	public void setDatumIznajmljivanja(Timestamp datumIznajmljivanja) {this.datumIznajmljivanja = datumIznajmljivanja;}
	public Timestamp getDatumVracanja() {return datumVracanja;}
	public void setDatumVracanja(Timestamp datumVracanja) {this.datumVracanja = datumVracanja;}
	
}
