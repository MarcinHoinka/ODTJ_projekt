package app.model;

public class RootUserEditModel {
	
	private int id_k;
	private String imie, nazwisko, email, telefon, login, haslo;
	
	public RootUserEditModel() {
		super();
	}
	
	public RootUserEditModel(int id_k, String imie, String nazwisko, String email, String telefon, String login,
			String haslo) {
		super();
		this.id_k = id_k;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.email = email;
		this.telefon = telefon;
		this.login = login;
		this.haslo = haslo;
	}
	
	
	public int getId_k() {
		return id_k;
	}
	public void setId_k(int id_k) {
		this.id_k = id_k;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getHaslo() {
		return haslo;
	}
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
	
	
	
}
