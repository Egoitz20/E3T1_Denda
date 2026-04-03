package saltzaileLeihoak.bezeroTaulaEskaerak;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BezeroTaulaEskaerak extends Application {

	public BezeroTaulaEskaerak () {
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BezeroTaulaEskaerak.fxml"));
		Scene scene = new Scene(loader.load());

		BezeroTaulaEskaerakKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bezeroaren informazioa eskaerarako");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
