package bezeroLeihoak.bezeroArreta;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BezeroArreta extends Application {

	private BezeroBean bezeroData;

	public BezeroArreta() {
	}

	public void setBezeroData(BezeroBean bezeroa) {
		this.bezeroData = bezeroa;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BezeroArreta.fxml"));
		Scene scene = new Scene(loader.load());

		BezeroArretaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);
		controller.setBezeroData(bezeroData);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bezero Arreta");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
