package saltzaileLeihoak.bezeroTaula;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BezeroTaula extends Application {

	private LangileSaltzaileBean saltzaileData;

	public void setSaltzaileData(LangileSaltzaileBean saltzailea) {
		this.saltzaileData = saltzailea;
	}

	public BezeroTaula() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BezeroTaula.fxml"));
		Scene scene = new Scene(loader.load());

		BezeroTaulaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bezeroaren informazioa");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
