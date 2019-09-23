package model;

import java.util.ArrayList;

public class Knjiga 
{
	private int id;
	private String naziv;
	private int ukupnoKnjiga;
	private int trenutniBrojKnjiga;
	private ArrayList<Autor> listaAutora;
	
	public Knjiga() {super();}
	
	public Knjiga(String naziv, int ukupnoKnjiga) 
	{
		super();
		
		this.naziv = naziv;
		this.ukupnoKnjiga = ukupnoKnjiga;
		this.trenutniBrojKnjiga = ukupnoKnjiga;
		listaAutora = new ArrayList<Autor>();
	}
	
	public Knjiga(String naziv, int ukupnoKnjiga, int trenutniBrojKnjiga) 
	{
		super();
		
		this.naziv = naziv;
		this.ukupnoKnjiga = ukupnoKnjiga;
		this.trenutniBrojKnjiga = trenutniBrojKnjiga;
		listaAutora = new ArrayList<Autor>();
	}

	public Knjiga(int id, String naziv, int ukupnoKnjiga, int trenutniBrojKnjiga) 
	{
		super();
		this.id = id;
		this.naziv = naziv;
		this.ukupnoKnjiga = ukupnoKnjiga;
		this.trenutniBrojKnjiga = trenutniBrojKnjiga;
		listaAutora = new ArrayList<Autor>();
	}
	
	@Override
	public String toString() 
	{
		return id + " - " + naziv + " - " + trenutniBrojKnjiga;
	}

	//GETERI I SETERI
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNaziv() {return naziv;}
	public void setNaziv(String naziv) {this.naziv = naziv;}
	public int getUkupnoKnjiga() {return ukupnoKnjiga;}
	public void setUkupnoKnjiga(int ukupnoKnjiga) {this.ukupnoKnjiga = ukupnoKnjiga;}
	public int getTrenutniBrojKnjiga() {return trenutniBrojKnjiga;}
	public void setTrenutniBrojKnjiga(int trenutniBrojKnjiga) {this.trenutniBrojKnjiga = trenutniBrojKnjiga;}
	public ArrayList<Autor> getListaAutora() {return listaAutora;}
	public void setListaAutora(ArrayList<Autor> listaAutora) {this.listaAutora = listaAutora;}
	
}
