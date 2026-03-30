package saltzaileLeihoak.saltzaileMenuPrintzipala;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SaltzaileMenuPrintzipala extends Application {

	public SaltzaileMenuPrintzipala() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SaltzaileMenuPrintzipala.fxml"));
		Scene scene = new Scene(loader.load());

		primaryStage.setScene(scene);
		primaryStage.setTitle("Saltzaile Menu Printzipala");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
