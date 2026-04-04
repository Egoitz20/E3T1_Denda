package bezeroLeihoak.bezeroMenuPrintzipala;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BezeroMenuPrintzipala extends Application {

	private BezeroBean bezeroData;

	 public void setBezeroData(BezeroBean bezeroa) {
	        this.bezeroData = bezeroa;
	    }
	
	public BezeroMenuPrintzipala() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BezeroMenuPrintzipala.fxml"));
		Scene scene = new Scene(loader.load());

		BezeroMenuPrintzipalaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);
		controller.setBezeroData(bezeroData);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bezero Menu Printzipala");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
