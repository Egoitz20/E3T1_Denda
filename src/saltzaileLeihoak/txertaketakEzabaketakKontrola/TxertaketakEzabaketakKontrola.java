package saltzaileLeihoak.txertaketakEzabaketakKontrola;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TxertaketakEzabaketakKontrola extends Application {

	private LangileSaltzaileBean saltzaileData;

	public void setSaltzaileData(LangileSaltzaileBean saltzailea) {
		this.saltzaileData = saltzailea;
	}

	public TxertaketakEzabaketakKontrola() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TxertaketakEzabaketakKontrola.fxml"));
		Scene scene = new Scene(loader.load());

		TxertaketakEzabaketakKontrolaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Erabiltzaileen txertketak edo ezabaketak informazioa");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
