package orokorLeihoak.login;

import java.util.ArrayList;

import datuBaseKonexioa.BezeroBean;
import datuBaseKonexioa.SaltzaileBean;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;
import orokorLeihoak.erregistratu.Erregistratu;

public class LoginKontrolagailua extends HandlerGlobala {

	@FXML
	private TextField erabiltzaileak;

	@FXML
	private PasswordField pasahitza;

	public LoginKontrolagailua() {
	}

	// 'Sartu' botoia sakatzen denean metodoa.
	@FXML
	public void sartu() {

		String jasotakoErabiltzailea = erabiltzaileak.getText();
		String jasotakoPasahitza = pasahitza.getText();

		// Kampoak hutsik ez dagoela konprobatuko dira
		if (jasotakoErabiltzailea.isEmpty() || jasotakoPasahitza.isEmpty()) {

			irekiAlerta("Errorea", "Erabiltzailea eta pasahitza bete behar dira",
					"Mesedez, bete erabiltzailea eta pasahitza.");
			return;
		}

		// Lehenengo, saltzaile bada konprobatuko da.
		if (konprobatuSaltzailea(jasotakoErabiltzailea, jasotakoPasahitza)) {
			irekiSaltzaileMenuPrintzipala();
		}

		// Ez bada, bezeroa bada konprobatuko da.
		else if (konprobatuBezeroa(jasotakoErabiltzailea, jasotakoPasahitza)) {
			irekiBezeroMenuPrintzipala();
		}
		// Biak ez badira, salbuespen bat jasoko da.
		else {
			irekiAlerta("Errorea", "Erabiltzailea edo pasahitza okerra",
					"Sartutako erabiltzailea edo pasahitza ez da zuzena.");
		}
	};

	// 'Erregistratu' botoia sakatzen denean metodoa
	@FXML
	public void erregistratu() {
		
		okultatuLeihoa();
	 
	    irekiErregistratu();
	};

	// Saltzailea konprobatzeko metodoa
	private boolean konprobatuSaltzailea(String erabiltzailea, String pasahitza) {
		ArrayList<SaltzaileBean> saltzaileak = jasoSaltzaileak();

		for (SaltzaileBean saltzaile : saltzaileak) {
			if (saltzaile.getErabiltzailea().equals(erabiltzailea) && saltzaile.getPasahitza().equals(pasahitza)) {
				return true;
			}
		}
		return false;
	}

	// Bezero konprobatzeko metodoa
	private boolean konprobatuBezeroa(String erabiltzailea, String pasahitza) {
	    ArrayList<BezeroBean> bezeroak = jasoBezeroak();

	    for (BezeroBean bezero : bezeroak) {
	        if (bezero.getErabiltzaile() != null && bezero.getPasahitza() != null) {
	            if (bezero.getErabiltzaile().equals(erabiltzailea) && bezero.getPasahitza().equals(pasahitza)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}

	private void irekiErregistratu() {
		try {
			 	Erregistratu erregitratuLeihoa = new Erregistratu();
		        Stage newStage = new Stage();

		        erregitratuLeihoa.start(newStage);
		        
		        // Erregistro leihoa ixten denean, login leihoa ikusiko da 
		        newStage.setOnHidden(event -> {
		           ikusiLeihoa();
		        });

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea bezeroaren menua irekitzean: " + e.getMessage());
		}
	}

}
