package saltzaileLeihoak.saltzaileKudeaketa;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import kontrolagailuGlobala.HandlerGlobala;

public class SaltzaileKudeaketaKontrolagailua extends HandlerGlobala {

	@FXML
	private TextField ipiniId;

	@FXML
	private TextField ipiniIzenAbizenak;

	@FXML
	private TextField ipiniErabiltzailea;

	@FXML
	private TextField ipiniPasahitza;

	@FXML
	private TextField ipiniEmaila;

	@FXML
	private TextField ipiniTelefonoa;

	@FXML
	private TextField ipiniNagusiIzena;

	@FXML
	private TextField ipiniKontratazioData;

	@FXML
	private TextField ipiniSoldata;

	@FXML
	private Button sortuButton;

	@FXML
	private Button aldatuButton;

	@FXML
	private Button ezabatuButton;

	private LangileSaltzaileBean saltzaileInfo; // Saltzailearen datuak
	private boolean editazekoModoa = false; // Sortzeko edo editatzeko modoa

	public SaltzaileKudeaketaKontrolagailua() {
	}

	// Leiho nagusitik jasotzen ditu datuak, eta interfazea konfiguratzen du
	// moduaren arabera (edizioa edo sorkuntza).

	public void setSaltzaileData(LangileSaltzaileBean data, boolean editMode) {
		this.saltzaileInfo = data;
		this.editazekoModoa = editMode;

		// Interfazea konfiguratzeko moduaren arabera
		if (editMode && data != null) {
			kargatuDatuakFormularioan(); // Datuak kargatzen ditu
			sortuButton.setVisible(false); // "Sortu" botoia desagertzen du
			sortuButton.setManaged(false); // Ez da biztaratzen
			aldatuButton.setVisible(true); // "Aldatu" botoia bistaratzen du
			ezabatuButton.setVisible(true); // "Ezabatu" botoia bistaratzen du
		} else {
			sortuButton.setVisible(true); // "Sortu" botoia bistaratzen du
			sortuButton.setManaged(true);
			aldatuButton.setVisible(false); // "Aldatu" botoia desagertzen du
			aldatuButton.setManaged(false);
			ezabatuButton.setVisible(false); // "Aldatu" botoia desagertzen du
			ezabatuButton.setManaged(false);
			garbituEremuak(); // Eremu denak garbitzen ditu
		}
	}

	// saltzaileInfo objektuaren datuak hartzen ditu eta formularioaren eremuetan
	// jartzen ditu.
	private void kargatuDatuakFormularioan() {
		if (saltzaileInfo != null) {
			ipiniId.setText(String.valueOf(saltzaileInfo.getId()));
			ipiniIzenAbizenak.setText(saltzaileInfo.getLangileIzenaAbizena());
			ipiniErabiltzailea.setText(saltzaileInfo.getErabiltzailea());
			ipiniPasahitza.setText(saltzaileInfo.getPasahitza());
			ipiniEmaila.setText(saltzaileInfo.getEmaila());
			ipiniTelefonoa.setText(saltzaileInfo.getTelefonoa());
			ipiniNagusiIzena.setText(saltzaileInfo.getNagusiIzenaAbizena());

			if (saltzaileInfo.getKontratazio_data() != null) {
				ipiniKontratazioData.setText(saltzaileInfo.getKontratazio_data().toString());
			}

			ipiniSoldata.setText(String.valueOf(saltzaileInfo.getSoldata()));
		}
	}

	@FXML
	public void itzuli() {
		itxiOraingoLeihoa();
		irekiSaltzaileMenuPrintzipala();
	}

	// Eremuak balidatzen ditu eta biltegiratutako prozedurara deitzen dio
	// datu-basean beste saltzaile bat sartzeko.
	@FXML
	public void sortu() {
		if (ipiniIzenAbizenak.getText().isEmpty() || ipiniErabiltzailea.getText().isEmpty()
				|| ipiniPasahitza.getText().isEmpty()) {

			irekiAlerta("Errorea", "Datuak bete behar dira",
					"Izena, erabiltzailea eta pasahitza derrigorrezkoak dira.");

			return;
		}

		// izenaAbizena separatzen ditu
		String[] izenaAbizena = ipiniIzenAbizenak.getText().split(" ", 2);
		String izena = izenaAbizena[0];
		String abizena = izenaAbizena.length > 1 ? izenaAbizena[1] : "";

		// Soldata balidatu
		int soldata = 0;
		try {
			soldata = Integer.parseInt(ipiniSoldata.getText());
		} catch (NumberFormatException e) {
			irekiAlerta("Errorea", "Soldata baliozkoa izan behar da", "Mesedez, sartu soldata zenbaki bat.");
			return;
		}

		// Deitu prozedura
		txertatuSaltzailea(izena, abizena, ipiniErabiltzailea.getText(), ipiniPasahitza.getText(),
				ipiniEmaila.getText(), ipiniTelefonoa.getText(), soldata);

		irekiAlerta("Arrakasta", "Saltzailea sortuta", "Saltzailea ondo sortu da.");

		itxiOraingoLeihoa();
		irekiSaltzaileMenuPrintzipala();

	}

	// Saltzaile baten datuak eguneratzen ditu, biltegiratutako prozedura erabilita.
	@FXML
	public void aldatu() {
		if (ipiniId.getText().isEmpty()) {
			irekiAlerta("Errorea", "ID bat hautatu behar duzu", "Lehenengo hautatu saltzaile bat.");
			return;
		}

		// izenaAbizena separatzen ditu
		String[] izenaAbizena = ipiniIzenAbizenak.getText().split(" ", 2);
		String izena = izenaAbizena[0];
		String abizena = izenaAbizena.length > 1 ? izenaAbizena[1] : "";

		int id = Integer.parseInt(ipiniId.getText());
		int soldata = Integer.parseInt(ipiniSoldata.getText());

		// Deitu prozedura
		eguneratuSaltzailea(id, izena, abizena, ipiniErabiltzailea.getText(), ipiniPasahitza.getText(),
				ipiniEmaila.getText(), ipiniTelefonoa.getText(), soldata);

		irekiAlerta("Arrakasta", "Saltzailea eguneratuta", "Saltzailea ondo eguneratu da.");

		itxiOraingoLeihoa();
		irekiSaltzaileMenuPrintzipala();

	}

	// Saltzaile bat datu-basetik ezabatzen du(konfirmatu ondoren).
	@FXML
	public void ezabatu() {
		if (ipiniId.getText().isEmpty()) {
			irekiAlerta("Errorea", "ID bat hautatu behar duzu", "Lehenengo hautatu saltzaile bat.");
			return;
		}

		ezabaketaBaieztatu();
	}

	private void garbituEremuak() {
		ipiniId.clear();
		ipiniIzenAbizenak.clear();
		ipiniErabiltzailea.clear();
		ipiniPasahitza.clear();
		ipiniEmaila.clear();
		ipiniTelefonoa.clear();
		ipiniNagusiIzena.clear();
		ipiniKontratazioData.clear();
		ipiniSoldata.clear();
	}
	
	private void ezabaketaBaieztatu() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Baieztapena");
		alert.setHeaderText("Saltzailea ezabatu");
		alert.setContentText("Ziur zaude saltzaile hau ezabatu nahi duzula?");

		if (alert.showAndWait().get() == ButtonType.OK) {
			int id = Integer.parseInt(ipiniId.getText());
			ezabatuSaltzailea(id);

			irekiAlerta("Arrakasta", "Saltzailea ezabatuta", "Saltzailea ondo ezabatu da.");

			itxiOraingoLeihoa();
			irekiSaltzaileMenuPrintzipala();
		}
		
	}

}
