package bezeroLeihoak.storageTaula;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StorageTaula extends Application {

	public StorageTaula() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StorageTaula.fxml"));
		Scene scene = new Scene(loader.load());

		StorageTaulaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Storage Lista");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
