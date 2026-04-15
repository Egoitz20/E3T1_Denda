package bezeroLeihoak.saioInformazioa;

import datuBaseKonexioa.BezeroBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kontrolagailuGlobala.HandlerGlobala;

public class SaioInformazioaKontrolagailua extends HandlerGlobala {

	@FXML
	private Button gordeButton;

	@FXML
	private TextField ipiniIzena;

	@FXML
	private TextField ipiniAbizenak;

	@FXML
	private TextField ipiniNan;

	@FXML
	private TextField ipiniTelefonoa;

	@FXML
	private TextField ipiniHelbidea;

	@FXML
	private TextField ipiniEmaila;

	@FXML
	private TextField ipiniErabiltzailea;

	@FXML
	private TextField ipiniPasahitza;

	@FXML
	private Button editatuIzenaButton;

	@FXML
	private Button editatuAbizenaButton;

	@FXML
	private Button editatuNanButton;

	@FXML
	private Button editatuTelefonoaButton;

	@FXML
	private Button editatuHelbideaButton;

	@FXML
	private Button editatuEmailaButton;

	@FXML
	private Button editatuErabiltzaileaButton;

	@FXML
	private Button editatuPasahitzaButton;

	private BezeroBean bezeroData;
	private boolean edizioModua = false;

	// Jatorrizko balioak gorde egiten dira ezeztatu ahal izateko. (Datu originalak)
	private String jatorrizkoIzena;
	private String jatorrizkoAbizena;
	private String jatorrizkoNan;
	private String jatorrizkoTelefonoa;
	private String jatorrizkoHelbidea;
	private String jatorrizkoEmaila;
	private String jatorrizkoErabiltzailea;
	private String jatorrizkoPasahitza;

	public void setBezeroData(BezeroBean data) {
		this.bezeroData = data;
		kargatuDatuakFormularioan();
	}

	public SaioInformazioaKontrolagailua() {

	}

	@FXML
	public void initialize() {
		// Lehenenik, eremuak bakarrik irakurtzeko konfiguratzen da.
		konfiguratuIrakurketaModua();

		// Edizio botoiak konfiguratzen dira
		konfiguratuEditatuBotoiak();
	}

	@FXML
	public void gordeInfo() {
		if (ipiniIzena.getText().isEmpty() || ipiniNan.getText().isEmpty() || ipiniTelefonoa.getText().isEmpty()
				|| ipiniEmaila.getText().isEmpty() || ipiniErabiltzailea.getText().isEmpty()
				|| ipiniPasahitza.getText().isEmpty()) {

			irekiAlerta("Errorea", "Eremuak hutsik", "Derrigorrezko eremuak hutsik daude. Mesedez bete eremu horiek");
			return;
		}

		// BezeroBean objektua eguneratu
		bezeroData.setIzena(ipiniIzena.getText());
		bezeroData.setAbizena(ipiniAbizenak.getText());
		bezeroData.setNan(ipiniNan.getText());
		bezeroData.setZenbakia(ipiniTelefonoa.getText());
		bezeroData.setHelbidea(ipiniHelbidea.getText());
		bezeroData.setEmaila(ipiniEmaila.getText());
		bezeroData.setErabiltzaile(ipiniErabiltzailea.getText());
		bezeroData.setPasahitza(ipiniPasahitza.getText());

		// Prozedimentua deitzen da datu berriak gordetzeko
		eguneratuBezeroa(bezeroData.getId(), bezeroData.getIzena(), bezeroData.getAbizena(), bezeroData.getNan(),
				bezeroData.getEmaila(), bezeroData.getHelbidea(), bezeroData.getErabiltzaile(),
				bezeroData.getPasahitza(), bezeroData.getZenbakia());
		
		irekiAlerta("Arrakasta", "Datak eguneratuta", "Zure informazioa ondo eguneratu da.");
		
		//Irakurtzeko modua bueltatzen da.
		konfiguratuIrakurketaModua();
	}

	private void kargatuDatuakFormularioan() {
		if (bezeroData != null) {
			ipiniIzena.setText(bezeroData.getIzena());
			ipiniAbizenak.setText(bezeroData.getAbizena());
			ipiniNan.setText(bezeroData.getNan());
			ipiniTelefonoa.setText(bezeroData.getZenbakia());
			ipiniHelbidea.setText(bezeroData.getHelbidea());
			ipiniEmaila.setText(bezeroData.getEmaila());
			ipiniErabiltzailea.setText(bezeroData.getErabiltzaile());
			ipiniPasahitza.setText(bezeroData.getPasahitza());

		}

	}

	private void konfiguratuIrakurketaModua() {
		ipiniIzena.setEditable(false);
		ipiniAbizenak.setEditable(false);
		ipiniNan.setEditable(false);
		ipiniTelefonoa.setEditable(false);
		ipiniHelbidea.setEditable(false);
		ipiniEmaila.setEditable(false);
		ipiniErabiltzailea.setEditable(false);
		ipiniPasahitza.setEditable(false);

		gordeButton.setVisible(false);
		gordeButton.setManaged(false);

	}

	private void konfiguratuEditatuModua() {
		ipiniIzena.setEditable(true);
		ipiniAbizenak.setEditable(true);
		ipiniNan.setEditable(true);
		ipiniTelefonoa.setEditable(true);
		ipiniHelbidea.setEditable(true);
		ipiniEmaila.setEditable(true);
		ipiniErabiltzailea.setEditable(true);
		ipiniPasahitza.setEditable(true);

		gordeButton.setVisible(true);
		gordeButton.setManaged(true);

		// Jatorrizko balioak gorde egiten dira editatu aurretik
		jatorrizkoIzena = ipiniIzena.getText();
		jatorrizkoAbizena = ipiniAbizenak.getText();
		jatorrizkoNan = ipiniNan.getText();
		jatorrizkoTelefonoa = ipiniTelefonoa.getText();
		jatorrizkoHelbidea = ipiniHelbidea.getText();
		jatorrizkoEmaila = ipiniEmaila.getText();
		jatorrizkoErabiltzailea = ipiniErabiltzailea.getText();
		jatorrizkoPasahitza = ipiniPasahitza.getText();
	}

	private void konfiguratuEditatuBotoiak() {
		editatuIzenaButton.setOnAction(_ -> konfiguratuEditatuModua());
		editatuAbizenaButton.setOnAction(_ -> konfiguratuEditatuModua());
		editatuNanButton.setOnAction(_ -> konfiguratuEditatuModua());
		editatuTelefonoaButton.setOnAction(_ -> konfiguratuEditatuModua());
		editatuHelbideaButton.setOnAction(_ -> konfiguratuEditatuModua());
		editatuEmailaButton.setOnAction(_ -> konfiguratuEditatuModua());
		editatuErabiltzaileaButton.setOnAction(_ -> konfiguratuEditatuModua());
		editatuPasahitzaButton.setOnAction(_ -> konfiguratuEditatuModua());
	}

	
	// Edizio-botoiek banaka funtzionatzen dute 
	@FXML
	public void itzuli() {
		itxiOraingoLeihoa();
		irekiBezeroMenuPrintzipala();
	}
	
	@FXML
	public void editatuIzena() {
	    konfiguratuEditatuModua();
	    ipiniIzena.requestFocus();
	}

	@FXML
	public void editatuAbizena() {
	    konfiguratuEditatuModua();
	    ipiniAbizenak.requestFocus();
	}

	@FXML
	public void editatuNan() {
	    konfiguratuEditatuModua();
	    ipiniNan.requestFocus();
	}

	@FXML
	public void editatuTelefonoa() {
	    konfiguratuEditatuModua();
	    ipiniTelefonoa.requestFocus();
	}

	@FXML
	public void editatuHelbidea() {
	    konfiguratuEditatuModua();
	    ipiniHelbidea.requestFocus();
	}

	@FXML
	public void editatuEmaila() {
	    konfiguratuEditatuModua();
	    ipiniEmaila.requestFocus();
	}

	@FXML
	public void editatuErabiltzailea() {
	    konfiguratuEditatuModua();
	    ipiniErabiltzailea.requestFocus();
	}

	@FXML
	public void editatuPasahitza() {
	    konfiguratuEditatuModua();
	    ipiniPasahitza.requestFocus();
	}

}
