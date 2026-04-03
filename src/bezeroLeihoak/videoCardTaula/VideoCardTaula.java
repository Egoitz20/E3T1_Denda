package bezeroLeihoak.videoCardTaula;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VideoCardTaula extends Application {

	public VideoCardTaula() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("VideoCardTaula.fxml"));
		Scene scene = new Scene(loader.load());

		VideoCardTaulaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Video Card Lista");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
