package saltzaileLeihoak.bezeroKudeaketa;

import datuBaseKonexioa.BezeroBean;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import kontrolagailuGlobala.HandlerGlobala;

public class BezeroKudeaketaKontrolagailua extends HandlerGlobala {

	@FXML
	private TextField ipiniId;

	@FXML
	private TextField ipiniIzenAbizenak;

	@FXML
	private TextField ipiniNan;

	@FXML
	private TextField ipiniEmaila;

	@FXML
	private TextField ipiniTelefonoa;

	@FXML
	private TextField ipiniHelbidea;

	@FXML
	private TextField ipiniErabiltzailea;

	@FXML
	private TextField ipiniPasahitza;

	@FXML
	private Button sortuButton;

	@FXML
	private Button aldatuButton;

	@FXML
	private Button ezabatuButton;

	@FXML
	private Button itzuliButton;

	private BezeroBean bezeroInfo;
	private boolean editazekoModoa = false;

	public BezeroKudeaketaKontrolagailua() {
	}

	public void setBezeroData(BezeroBean data, boolean editMode) {
		this.bezeroInfo = data;
		this.editazekoModoa = editMode;

		if (editMode && data != null) {
			kargatuDatuakFormularioan();
			sortuButton.setVisible(false);
			sortuButton.setManaged(false);
			aldatuButton.setVisible(true);
			aldatuButton.setManaged(true);
			ezabatuButton.setVisible(true);
			ezabatuButton.setManaged(true);
			ipiniId.setEditable(false);
		} else {
			sortuButton.setVisible(true);
			sortuButton.setManaged(true);
			aldatuButton.setVisible(false);
			aldatuButton.setManaged(false);
			ezabatuButton.setVisible(false);
			ezabatuButton.setManaged(false);
			garbituEremuak();
			ipiniId.setEditable(false);
		}
	}

	private void kargatuDatuakFormularioan() {
		if (bezeroInfo != null) {
			ipiniId.setText(String.valueOf(bezeroInfo.getId()));

			// Izena eta abizena kombinaketa
			String izenaOsoa = bezeroInfo.getIzena() + " " + bezeroInfo.getAbizena();
			ipiniIzenAbizenak.setText(izenaOsoa);

			ipiniEmaila.setText(bezeroInfo.getEmaila());
			ipiniHelbidea.setText(bezeroInfo.getHelbidea());
			ipiniErabiltzailea.setText(bezeroInfo.getErabiltzaile());
			ipiniPasahitza.setText(bezeroInfo.getPasahitza());
			ipiniNan.setText(bezeroInfo.getNan());
			ipiniTelefonoa.setText(bezeroInfo.getZenbakia());
		}
	}

	@FXML
	public void itzuli() {
		itxiOraingoLeihoa();
		irekiSaltzaileMenuPrintzipala();
	}

	@FXML
	public void sortu() {
		if (ipiniIzenAbizenak.getText().isEmpty() || ipiniErabiltzailea.getText().isEmpty()
				|| ipiniPasahitza.getText().isEmpty()) {

			irekiAlerta("Errorea", "Datuak bete behar dira",
					"Izena, erabiltzailea eta pasahitza derrigorrezkoak dira.");
			return;
		}

		String[] izenaAbizena = ipiniIzenAbizenak.getText().split(" ", 2);
		String izena = izenaAbizena[0];
		String abizena = izenaAbizena.length > 1 ? izenaAbizena[1] : "";

		txertatuBezeroa(izena, abizena, ipiniNan.getText(), ipiniEmaila.getText(), ipiniHelbidea.getText(),
				ipiniErabiltzailea.getText(), ipiniPasahitza.getText(), ipiniTelefonoa.getText());

		irekiAlerta("Arrakasta", "Bezeroa sortuta", "Bezeroa ondo sortu da.");

		itxiOraingoLeihoa();
		irekiSaltzaileMenuPrintzipala();
	}

	@FXML
	public void aldatu() {
		if (ipiniId.getText().isEmpty()) {
			irekiAlerta("Errorea", "ID bat hautatu behar duzu", "Lehenengo hautatu bezero bat.");
			return;
		}

		String[] izenaAbizena = ipiniIzenAbizenak.getText().split(" ", 2);
		String izena = izenaAbizena[0];
		String abizena = izenaAbizena.length > 1 ? izenaAbizena[1] : "";

		int id = Integer.parseInt(ipiniId.getText());

		eguneratuBezeroa(id, izena, abizena, ipiniNan.getText(), ipiniEmaila.getText(), ipiniHelbidea.getText(),
				ipiniErabiltzailea.getText(), ipiniPasahitza.getText(), ipiniTelefonoa.getText());

		irekiAlerta("Arrakasta", "Bezeroa eguneratuta", "Bezeroa ondo eguneratu da.");

		itxiOraingoLeihoa();
		irekiSaltzaileMenuPrintzipala();
	}

	@FXML
	public void ezabatu() {
		if (ipiniId.getText().isEmpty()) {
			irekiAlerta("Errorea", "ID bat hautatu behar duzu", "Lehenengo hautatu bezero bat.");
			return;
		}

		ezabaketaBaieztatu();

		itxiOraingoLeihoa();
		irekiSaltzaileMenuPrintzipala();

	}

	private void garbituEremuak() {
		ipiniId.clear();
		ipiniIzenAbizenak.clear();
		ipiniNan.clear();
		ipiniEmaila.clear();
		ipiniTelefonoa.clear();
		ipiniHelbidea.clear();
		ipiniErabiltzailea.clear();
		ipiniPasahitza.clear();
	}

	private void ezabaketaBaieztatu() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Baieztapena");
		alert.setHeaderText("Bezeroa ezabatu");
		alert.setContentText("Ziur zaude bezero hau ezabatu nahi duzula?");

		if (alert.showAndWait().get() == ButtonType.OK) {
			int id = Integer.parseInt(ipiniId.getText());
			ezabatuBezeroa(id);

			irekiAlerta("Arrakasta", "Bezeroa ezabatuta", "Bezeroa ondo ezabatu da.");
			
			itxiOraingoLeihoa();
			irekiSaltzaileMenuPrintzipala();
		}

	}
}