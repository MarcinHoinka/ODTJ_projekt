package app.model;

public class RootKursyModel {

	private int id_sz;
	private String nazwa_sz, opis;
	private int czas_trwania;
	
	public RootKursyModel() {
		super();
	}

	public RootKursyModel(int id_sz, String nazwa_sz, String opis, int czas_trwania) {
		super();
		this.id_sz = id_sz;
		this.nazwa_sz = nazwa_sz;
		this.opis = opis;
		this.czas_trwania = czas_trwania;
	}

	public int getId_sz() {
		return id_sz;
	}

	public void setId_sz(int id_sz) {
		this.id_sz = id_sz;
	}

	public String getNazwa_sz() {
		return nazwa_sz;
	}

	public void setNazwa_sz(String nazwa_sz) {
		this.nazwa_sz = nazwa_sz;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getCzas_trwania() {
		return czas_trwania;
	}

	public void setCzas_trwania(int czas_trwania) {
		this.czas_trwania = czas_trwania;
	}


	
}
