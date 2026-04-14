package orokorLeihoak.login;

import java.util.ArrayList;

import bezeroLeihoak.bezeroMenuPrintzipala.BezeroMenuPrintzipala;
import datuBaseKonexioa.BezeroBean;
import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;
import orokorLeihoak.erregistratu.Erregistratu;
import saltzaileLeihoak.saltzaileMenuPrintzipala.SaltzaileMenuPrintzipala;

public class LoginKontrolagailua extends HandlerGlobala {

	@FXML
	private TextField erabiltzaileak;

	@FXML
	private PasswordField pasahitza;

	// Saltzaile gordetzeko aldagaia
	private LangileSaltzaileBean saltzaileLogueado;

	private BezeroBean bezeroLogueado;

	public LoginKontrolagailua() {
	}

	// 'Sartu' botoia sakatzen denean metodoa.
	@FXML
	public void sartu() {
		String jasotakoErabiltzailea = erabiltzaileak.getText();
		String jasotakoPasahitza = pasahitza.getText();

		if (jasotakoErabiltzailea.isEmpty() || jasotakoPasahitza.isEmpty()) {
			irekiAlerta("Errorea", "Erabiltzailea eta pasahitza bete behar dira",
					"Mesedez, bete erabiltzailea eta pasahitza.");
			return;
		}

		// Primero comprobar si es vendedor
		if (konprobatuEtaLortuSaltzailea(jasotakoErabiltzailea, jasotakoPasahitza)) {
			irekiSaltzaileMenuPrintzipalaDatuekin();
		}
		// Si no, comprobar si es cliente y obtener sus datos
		else if (konprobatuEtaLortuBezeroa(jasotakoErabiltzailea, jasotakoPasahitza)) {
			irekiBezeroMenuPrintzipalaDatuekin();
		} else {
			irekiAlerta("Errorea", "Erabiltzailea edo pasahitza okerra",
					"Sartutako erabiltzailea edo pasahitza ez da zuzena.");
		}
	}

	// Saltzailea baldin bada komprobatuko du eta beren datu gusztiak jasoko ditu
	private boolean konprobatuEtaLortuSaltzailea(String erabiltzailea, String pasahitza) {
		ArrayList<LangileSaltzaileBean> saltzaileak = jasoLangileSaltzaileak();

		for (LangileSaltzaileBean saltzaile : saltzaileak) {
			if (saltzaile.getErabiltzailea() != null && saltzaile.getErabiltzailea().equals(erabiltzailea)
					&& saltzaile.getPasahitza().equals(pasahitza)) {
				saltzaileLogueado = saltzaile; // Logeatutako saltzailea gordeko du
				return true;
			}
		}
		return false;
	}

	private boolean konprobatuEtaLortuBezeroa(String erabiltzailea, String pasahitza) {
		ArrayList<BezeroBean> bezeroak = jasoBezeroak();

		for (BezeroBean bezero : bezeroak) {
			if (bezero.getErabiltzaile() != null && bezero.getPasahitza() != null) {
				if (bezero.getErabiltzaile().equals(erabiltzailea) && bezero.getPasahitza().equals(pasahitza)) {
					bezeroLogueado = bezero;
					return true;
				}
			}
		}
		return false;
	}

	// Saltzaile menu printzipala irekitzen da eta bidaltzen ditu saltzaileen datuak
	private void irekiSaltzaileMenuPrintzipalaDatuekin() {
		try {
			SaltzaileMenuPrintzipala saltzaileMenu = new SaltzaileMenuPrintzipala();
			Stage newStage = new Stage();

			// Pasar los datos del vendedor logueado
			saltzaileMenu.setSaltzaileData(saltzaileLogueado);
			saltzaileMenu.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea saltzailearen menua irekitzean: " + e.getMessage());
		}
	}

	// Bezeroak komprobatzeko metodoa
	private void irekiBezeroMenuPrintzipalaDatuekin() {
		try {
			BezeroMenuPrintzipala bezeroMenu = new BezeroMenuPrintzipala();
			Stage newStage = new Stage();

			bezeroMenu.setBezeroData(bezeroLogueado);
			bezeroMenu.start(newStage);

			itxiOraingoLeihoa();
		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea bezeroaren menua irekitzean: " + e.getMessage());
		}
	}

	@FXML
	public void erregistratu() {
		okultatuLeihoa();
		irekiErregistratu();
	}

	private void irekiErregistratu() {
		try {
			Erregistratu erregitratuLeihoa = new Erregistratu();
			Stage newStage = new Stage();

			erregitratuLeihoa.start(newStage);

			newStage.setOnHidden(_ -> {
				ikusiLeihoa();
			});

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea bezeroaren menua irekitzean: " + e.getMessage());
		}
	}
}