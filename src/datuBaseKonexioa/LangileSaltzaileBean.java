package datuBaseKonexioa;

import java.sql.Date;

public class LangileSaltzaileBean {

	private int id;
	private String izenaAbizena;
	private String emaila;
	private String telefonoa;
	private Date kontratazio_data;
	private int id_nagusi;
	private int soldata;
	private String erabiltzailea;
	private String pasahitza;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIzenaAbizena() {
		return izenaAbizena;
	}

	public void setIzenaAbizena(String izenaAbizena) {
		this.izenaAbizena = izenaAbizena;
	}

	public String getEmaila() {
		return emaila;
	}

	public void setEmaila(String emaila) {
		this.emaila = emaila;
	}

	public String getTelefonoa() {
		return telefonoa;
	}

	public void setTelefonoa(String telefonoa) {
		this.telefonoa = telefonoa;
	}

	public Date getKontratazio_data() {
		return kontratazio_data;
	}

	public void setKontratazio_data(Date kontratazio_data) {
		this.kontratazio_data = kontratazio_data;
	}

	public int getId_nagusi() {
		return id_nagusi;
	}

	public void setId_nagusi(int id_nagusi) {
		this.id_nagusi = id_nagusi;
	}

	public int getSoldata() {
		return soldata;
	}

	public void setSoldata(int soldata) {
		this.soldata = soldata;
	}

	public String getErabiltzailea() {
		return erabiltzailea;
	}

	public void setErabiltzailea(String erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

}
