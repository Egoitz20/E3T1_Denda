package bezeroLeihoak.saioInformazioa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SaioInformazioa extends Application {

	public SaioInformazioa() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SaioInformazioa.fxml"));
		Scene scene = new Scene(loader.load());

		SaioInformazioaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bezero Menu Printzipala");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
