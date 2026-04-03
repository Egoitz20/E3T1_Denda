package bezeroLeihoak.erosiMetodoa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ErosiMetodoa extends Application {

	public ErosiMetodoa() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ErosiMetodoa.fxml"));
		Scene scene = new Scene(loader.load());

		ErosiMetodoaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bezero Menu Printzipala");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
