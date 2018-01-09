package app.model;

public class RootInstruktorzyModel {

	private int id_i;
	private String imie, nazwisko, email, telefon, login;

	
	public RootInstruktorzyModel() {
	}

	public RootInstruktorzyModel(int id_i, String imie, String nazwisko, String email, String telefon, String login) {
		super();
		this.id_i = id_i;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.email = email;
		this.telefon = telefon;
		this.login = login;
	}

	public int getId_i() {
		return id_i;
	}

	public void setId_i(int id_i) {
		this.id_i = id_i;
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
	
}
