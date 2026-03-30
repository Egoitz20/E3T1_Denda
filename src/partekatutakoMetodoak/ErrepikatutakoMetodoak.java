package partekatutakoMetodoak;

import bezeroLeihoak.bezeroMenuPrintzipala.BezeroMenuPrintzipala;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import saltzaileLeihoak.saltzaileMenuPrintzipala.SaltzaileMenuPrintzipala;

public class ErrepikatutakoMetodoak {

	private Stage stage;
	
	// Logineko stage jasotzeko metodoa
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	// SaltzaileMenuPrintzipala irekitzeko metodoa
	protected void irekiSaltzaileMenuPrintzipala() {
		try {
			SaltzaileMenuPrintzipala saltzaileMenu = new SaltzaileMenuPrintzipala();

			// Sortu Stage berri bat leiho -rako
			Stage newStage = new Stage();

			// Pistu leihoa deitzen start metodoari
			saltzaileMenu.start(newStage);

			// Login leihoa ixten da
			if (stage != null) {
				stage.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea saltzailearen menua irekitzean: " + e.getMessage());
		}
	}

	// BezeroMenuPrintzipala irekitzeko metodoa
	protected void irekiBezeroMenuPrintzipala() {
		try {
			BezeroMenuPrintzipala bezeroMenu = new BezeroMenuPrintzipala();
			Stage newStage = new Stage();

			bezeroMenu.start(newStage);

			if (stage != null) {
				stage.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea bezeroaren menua irekitzean: " + e.getMessage());
		}
	}

	// Salbuespen alerta metodoa
	protected void irekiAlerta(String titulua, String goiburua, String mezua) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulua);
		alert.setHeaderText(goiburua);
		alert.setContentText(mezua);
		alert.showAndWait();
	}

}
