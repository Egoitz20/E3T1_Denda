package saltzaileLeihoak.saltzaileTaula;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SaltzaileTaula extends Application {

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
