package orokorLeihoak.erregistratu;

import java.util.ArrayList;

import datuBaseKonexioa.BezeroBean;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kontrolagailuGlobala.HandlerGlobala;

public class ErregistratuKontrolagailua extends HandlerGlobala {

	@FXML
	private TextField erabiltzaileBerria;
	@FXML
	private PasswordField pasahitzaBerria;
	@FXML
	private PasswordField errepikatuPasahitzaBerria;

	public ErregistratuKontrolagailua() {
	}

	@FXML
	public void itzuli() {
		 itxiOraingoLeihoa();
	}

	@FXML
	public void sortuErabiltzailea() {
		String jasotakoErabiltzaileBerria = erabiltzaileBerria.getText();
		String jasotakoPasahitzaBerria = pasahitzaBerria.getText();
		String jasotakoErrepikatuPasahitzaBerria = errepikatuPasahitzaBerria.getText();

		if (pasahitzaKonparaketa(jasotakoPasahitzaBerria, jasotakoErrepikatuPasahitzaBerria)) {
			if (konprobatuBezeroa(jasotakoErabiltzaileBerria, jasotakoPasahitzaBerria)) {
				irekiAlerta("Errorea", "Erabiltzaile dago sortuta",
						"Erabiltzaile sortuta dago, mesedez saiatu erabiltzaile edo pasahitza berri batekin");
			} else {
				erregistroBezeroBerriaSortu(jasotakoErabiltzaileBerria, jasotakoPasahitzaBerria);
				irekiAlerta("Sortuta", "Erabiltzaile Sortuta", "Erabiltzale ondo sorto da.");
			}

		}

	}
	

	// Bezero konprobatzeko metodoa
	private boolean konprobatuBezeroa(String erabiltzailea, String pasahitza) {
	    ArrayList<BezeroBean> bezeroak = jasoBezeroak();

	    for (BezeroBean bezero : bezeroak) {
	        if (bezero.getErabiltzaile() != null && bezero.getErabiltzaile().equals(erabiltzailea)) {
	            return true;
	        }
	    }
	    return false;
	}


	private boolean pasahitzaKonparaketa(String pasahitza1, String pasahitza2) {

		if (pasahitza1.equals(pasahitza2)) {
			return true;
		}
		return false;

	}

}
