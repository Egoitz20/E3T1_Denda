package saltzaileLeihoak.saltzaileMenuPrintzipala;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;
import saltzaileLeihoak.saltzaileKudeaketa.SaltzaileKudeaketa;
import saltzaileLeihoak.saltzaileTaula.SaltzaileTaula;

public class SaltzaileMenuPrintzipalaKontrolagailua extends HandlerGlobala {

	public SaltzaileMenuPrintzipalaKontrolagailua() {
	}

	// Saltzaile taula irekitzeko metodoa
	@FXML
	public void irekiSaltzaileTaula() {
		try {
			SaltzaileTaula saltzaileTaula = new SaltzaileTaula();
			Stage newStage = new Stage();
			saltzaileTaula.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea saltzaileen taula irekitzean: " + e.getMessage());
		}
	}

	// Saltzaile taula irekitzeko metodoa
	@FXML
	public void irekiSaltzaileKudeaketa() {
		try {
			SaltzaileKudeaketa saltzaileTaula = new SaltzaileKudeaketa();
			Stage newStage = new Stage();
			saltzaileTaula.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea saltzaileen taula irekitzean: " + e.getMessage());
		}
	}

	// Saioa ixteko metodoa
	@FXML
	public void saioaIxten() {
		itxiOraingoLeihoa();
		irekiLogina();
	}

}
