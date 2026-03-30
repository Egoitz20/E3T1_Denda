package orokorLeihoak.erregistratu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Erregistratu extends Application {

	public Erregistratu() {
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Erregistratu.fxml"));
		Scene scene = new Scene(loader.load());

		primaryStage.setScene(scene);
		primaryStage.setTitle("Erregistratu");
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch();
	}

}
