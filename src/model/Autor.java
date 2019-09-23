package model;

import java.util.ArrayList;

public class Autor 
{
	private int id;
	private String ime;
	private String prezime;
	private ArrayList<Knjiga> listaKnjiga;
	
	public Autor() {super();}
	
	public Autor(String ime, String prezime) 
	{
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.listaKnjiga = new ArrayList<Knjiga>();
	}
	
	public Autor(int id, String ime, String prezime) 
	{
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.listaKnjiga = new ArrayList<Knjiga>();
	}
	
	@Override
	public String toString() 
	{
		return id + " - " + ime + " " + prezime;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Autor != true)
			return false;
		Autor a = (Autor) obj;
		if (a.id != this.id)
			return false;
		return true;
	}
	
	//GETERI I SETERI
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getIme() {return ime;}
	public void setIme(String ime) {this.ime = ime;}
	public String getPrezime() {return prezime;}
	public void setPrezime(String prezime) {this.prezime = prezime;}
	public ArrayList<Knjiga> getListaKnjiga() {return listaKnjiga;}
	public void setListaKnjiga(ArrayList<Knjiga> listaKnjiga) {this.listaKnjiga = listaKnjiga;}
		
}
