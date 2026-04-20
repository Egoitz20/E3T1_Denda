package saltzaileLeihoak.abisuak;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Abisuak extends Application {

	private LangileSaltzaileBean saltzaileData;

	public void setSaltzaileData(LangileSaltzaileBean saltzailea) {
		this.saltzaileData = saltzailea;
	}

	public Abisuak() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Abisuak.fxml"));
		Scene scene = new Scene(loader.load());

		AbisuakKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bezeroen Abisuak");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}