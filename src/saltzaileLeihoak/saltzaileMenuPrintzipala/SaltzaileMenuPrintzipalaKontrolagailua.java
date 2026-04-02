package saltzaileLeihoak.saltzaileMenuPrintzipala;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;
import saltzaileLeihoak.bezeroKudeaketa.BezeroKudeaketa;
import saltzaileLeihoak.bezeroTaula.BezeroTaula;
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

	// Bezero taula irekitzeko metodoa
	@FXML
	public void irekiBezeroTaula() {
		try {
			BezeroTaula bezeroTaula = new BezeroTaula();
			Stage newStage = new Stage();
			bezeroTaula.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea bezeroen taula irekitzean: " + e.getMessage());
		}
	}
	
	// Bezero taula irekitzeko metodoa
		@FXML
		public void irekiBezeroKudeaketa() {
			try {
				BezeroKudeaketa bezeroTaula = new BezeroKudeaketa();
				Stage newStage = new Stage();
				bezeroTaula.start(newStage);

				itxiOraingoLeihoa();

			} catch (Exception e) {
				e.printStackTrace();
				irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
						"Errorea bezeroen taula irekitzean: " + e.getMessage());
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
					"Errorea saltzaileen kudeaketa irekitzean: " + e.getMessage());
		}
	}

	// Saioa ixteko metodoa
	@FXML
	public void saioaIxten() {
		itxiOraingoLeihoa();
		irekiLogina();
	}

}
