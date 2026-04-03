package bezeroLeihoak.cpuTaula;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CpuTaula extends Application{

	public CpuTaula() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CpuTaula.fxml"));
		Scene scene = new Scene(loader.load());

		CpuTaulaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("CPU Lista");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
