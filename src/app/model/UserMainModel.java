package app.model;

public class UserMainModel {

	private int id_gr;
	private String nazwa_grupy, data_szkolenia, instruktor, tor, szkolenie;
	private int czas_trwania;
	
	public UserMainModel() {
		super();
	}

	public UserMainModel(int id_gr, String nazwa_grupy, String data_szkolenia, String instruktor, String tor,
			String szkolenie, int czas_trwania) {
		super();
		this.id_gr = id_gr;
		this.nazwa_grupy = nazwa_grupy;
		this.data_szkolenia = data_szkolenia;
		this.instruktor = instruktor;
		this.tor = tor;
		this.szkolenie = szkolenie;
		this.czas_trwania = czas_trwania;
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

	public String getTor() {
		return tor;
	}

	public void setTor(String tor) {
		this.tor = tor;
	}

	public String getSzkolenie() {
		return szkolenie;
	}

	public void setSzkolenie(String szkolenie) {
		this.szkolenie = szkolenie;
	}

	public int getCzas_trwania() {
		return czas_trwania;
	}

	public void setCzas_trwania(int czas_trwania) {
		this.czas_trwania = czas_trwania;
	}
		
}
