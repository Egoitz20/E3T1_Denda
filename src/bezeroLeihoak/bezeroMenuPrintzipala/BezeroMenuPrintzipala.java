package bezeroLeihoak.bezeroMenuPrintzipala;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BezeroMenuPrintzipala extends Application {

	public BezeroMenuPrintzipala() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BezeroMenuPrintzipala.fxml"));
		Scene scene = new Scene(loader.load());

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bezero Menu Printzipala");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
