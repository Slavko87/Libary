package model;

public class Clan 
{
	private int id;
	private String ime;
	private String prezime;
	private String email;
	private String sifra;
	private int brojZaduzenihKnjiga;
	private int privilegija;
	
	
	/**Koristimo za ucitavanje clana iz baze
	 * 
	 * @param id
	 * @param ime
	 * @param prezime
	 * @param email
	 * @param sifra
	 * @param brojZaduzenihKnjiga
	 * @param privilegija
	 */
	public Clan(int id, String ime, String prezime, String email, String sifra, int brojZaduzenihKnjiga,
			int privilegija) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.sifra = sifra;
		this.brojZaduzenihKnjiga = brojZaduzenihKnjiga;
		this.privilegija = privilegija;
	}
		
	/**Koristimo kada unosimo u bazu clana
	 * Prilikom registracije obicnih clanova privilegija treba da bude 2, za admina privilegija je 1
	 * @param ime
	 * @param prezime
	 * @param email
	 * @param sifra
	 * @param brojZaduzenihKnjiga
	 * @param privilegija
	 */
	public Clan(String ime, String prezime, String email, String sifra, int brojZaduzenihKnjiga, int privilegija) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.sifra = sifra;
		this.brojZaduzenihKnjiga = brojZaduzenihKnjiga;
		this.privilegija = privilegija;
	}



	@Override
	public String toString() 
	{
		return id + " - " + ime + " " + prezime;
	}

	//GETERI I SETERI
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getIme() {return ime;}
	public void setIme(String ime) {this.ime = ime;}
	public String getPrezime() {return prezime;}
	public void setPrezime(String prezime) {this.prezime = prezime;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getSifra() {return sifra;}
	public void setSifra(String sifra) {this.sifra = sifra;}
	public int getBrojZaduzenihKnjiga() {return brojZaduzenihKnjiga;}
	public void setBrojZaduzenihKnjiga(int brojZaduzenihKnjiga) {this.brojZaduzenihKnjiga = brojZaduzenihKnjiga;}
	public int getPrivilegija() {return privilegija;}
	public void setPrivilegija(int privilegija) {this.privilegija = privilegija;}
	
}
