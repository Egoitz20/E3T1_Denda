package saltzaileLeihoak.saltzaileTaula;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SaltzaileTaula extends Application {

	private LangileSaltzaileBean saltzaileData;
	
	public void setSaltzaileData(LangileSaltzaileBean saltzailea) {
	        this.saltzaileData = saltzailea;
	 }
	
	public SaltzaileTaula() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SaltzaileTaula.fxml"));
		Scene scene = new Scene(loader.load());
		
		SaltzaileTaulaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Saltzaileen informazioa");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
