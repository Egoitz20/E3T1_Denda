package bezeroLeihoak.motherBoardTaula;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MotherBoardTaula extends Application {

	private BezeroBean bezeroData;

	public void setBezeroData(BezeroBean bezeroa) {
		this.bezeroData = bezeroa;
	}
	
	public MotherBoardTaula() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MotherBoardTaula.fxml"));
		Scene scene = new Scene(loader.load());

		MotherBoardTaulaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Mother Board Lista");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
