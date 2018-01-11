package app.model;

public class RootKursyEditModel {
	
	private int id_tr;
	private String nazwa, opis;
	private int czas_trwania;
	
	public RootKursyEditModel() {
		super();
	}

	public RootKursyEditModel(int id_tr, String nazwa, String opis, int czas_trwania) {
		super();
		this.id_tr = id_tr;
		this.nazwa = nazwa;
		this.opis = opis;
		this.czas_trwania = czas_trwania;
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
