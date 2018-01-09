package app.model;

public class RootToryEditModel {
	
	private int id_tr;
	private String nazwa, adres, miasto, kod_pocztowy, telefon;
	
	
	public RootToryEditModel() {
		super();
	}

	public RootToryEditModel(int id_tr, String nazwa, String adres, String miasto, String kod_pocztowy,
			String telefon) {
		super();
		this.id_tr = id_tr;
		this.nazwa = nazwa;
		this.adres = adres;
		this.miasto = miasto;
		this.kod_pocztowy = kod_pocztowy;
		this.telefon = telefon;
	}

	public int getId_tr() {
		return id_tr;
	}

	public void setId_tr(int id_tr) {
		this.id_tr = id_tr;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getMiasto() {
		return miasto;
	}

	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public String getKod_pocztowy() {
		return kod_pocztowy;
	}

	public void setKod_pocztowy(String kod_pocztowy) {
		this.kod_pocztowy = kod_pocztowy;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	

}
