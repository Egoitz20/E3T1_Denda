package bezeroLeihoak.erosiMetodoa;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ErosiMetodoa extends Application {

	private BezeroBean bezeroData;

	public void setBezeroData(BezeroBean bezeroa) {
		this.bezeroData = bezeroa;
	}
	
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
