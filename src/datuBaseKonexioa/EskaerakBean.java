package datuBaseKonexioa;

import java.sql.Date;

public class EskaerakBean {

	private String produktu;
	private String kategoria;
	private int kopurua;
	private Date eskaera_data;
	private String deskribapena;
	private String saltzailea;

	public String getProduktu() {
		return produktu;
	}

	public void setProduktu(String produktu) {
		this.produktu = produktu;
	}

	public String getKategoria() {
		return kategoria;
	}

	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}

	public int getKopurua() {
		return kopurua;
	}

	public void setKopurua(int kopurua) {
		this.kopurua = kopurua;
	}

	public Date getEskaera_data() {
		return eskaera_data;
	}

	public void setEskaera_data(Date eskaera_data) {
		this.eskaera_data = eskaera_data;
	}

	public String getDeskribapena() {
		return deskribapena;
	}

	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}

	public String getSaltzailea() {
		return saltzailea;
	}

	public void setSaltzailea(String saltzailea) {
		this.saltzailea = saltzailea;
	}

}
