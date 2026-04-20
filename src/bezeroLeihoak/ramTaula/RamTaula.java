package bezeroLeihoak.ramTaula;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RamTaula extends Application{

	private BezeroBean bezeroData;

	public void setBezeroData(BezeroBean bezeroa) {
		this.bezeroData = bezeroa;
	}
	
	public RamTaula() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RamTaula.fxml"));
		Scene scene = new Scene(loader.load());

		RamTaulaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("RAM Lista");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
