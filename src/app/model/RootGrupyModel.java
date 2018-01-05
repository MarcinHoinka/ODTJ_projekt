package app.model;

public class RootGrupyModel {
	
	private int id_gr;
	private String nazwa_grupy, data_szkolenia, instruktor, szkolenie, tor;
	private int czas_trwania;
	
	private int grupa; 
	private String imie, nazwisko, email, telefon;
	
	private int id_i; 
	private String instruktor_c;
	
	
	public RootGrupyModel() {
		super();
	}

	public RootGrupyModel(int id_gr, String nazwa_grupy, String data_szkolenia, String instruktor, String szkolenie,
			String tor, int czas_trwania) {
		super();
		this.id_gr = id_gr;
		this.nazwa_grupy = nazwa_grupy;
		this.data_szkolenia = data_szkolenia;
		this.instruktor = instruktor;
		this.szkolenie = szkolenie;
		this.tor = tor;
		this.czas_trwania = czas_trwania;
	}
	

	public RootGrupyModel(int grupa, String imie, String nazwisko, String email, String telefon) {
		super();
		this.grupa = grupa;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.email = email;
		this.telefon = telefon;
	}

	public RootGrupyModel(int id_i, String instruktor_c) {
		super();
		this.id_i = id_i;
		this.instruktor_c = instruktor_c;
	}

	public int getId_gr() {
		return id_gr;
	}

	public void setId_gr(int id_gr) {
		this.id_gr = id_gr;
	}

	public String getNazwa_grupy() {
		return nazwa_grupy;
	}

	public void setNazwa_grupy(String nazwa_grupy) {
		this.nazwa_grupy = nazwa_grupy;
	}

	public String getData_szkolenia() {
		return data_szkolenia;
	}

	public void setData_szkolenia(String data_szkolenia) {
		this.data_szkolenia = data_szkolenia;
	}

	public String getInstruktor() {
		return instruktor;
	}

	public void setInstruktor(String instruktor) {
		this.instruktor = instruktor;
	}

	public String getSzkolenie() {
		return szkolenie;
	}

	public void setSzkolenie(String szkolenie) {
		this.szkolenie = szkolenie;
	}

	public String getTor() {
		return tor;
	}

	public void setTor(String tor) {
		this.tor = tor;
	}

	public int getCzas_trwania() {
		return czas_trwania;
	}

	public void setCzas_trwania(int czas_trwania) {
		this.czas_trwania = czas_trwania;
	}

	public int getGrupa() {
		return grupa;
	}

	public void setGrupa(int grupa) {
		this.grupa = grupa;
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

	public int getId_i() {
		return id_i;
	}

	public void setId_i(int id_i) {
		this.id_i = id_i;
	}

	public String getInstruktor_c() {
		return instruktor_c;
	}

	public void setInstruktor_c(String instruktor_c) {
		this.instruktor_c = instruktor_c;
	}

	

}
