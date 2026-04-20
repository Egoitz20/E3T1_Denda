package bezeroLeihoak.otzara;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Otzara extends Application {

	private BezeroBean bezeroData;

	public void setBezeroData(BezeroBean bezeroa) {
		this.bezeroData = bezeroa;
	}
	
	public Otzara() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Otzara.fxml"));
		Scene scene = new Scene(loader.load());

		OtzaraKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Otzara");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
