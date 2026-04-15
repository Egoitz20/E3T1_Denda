package bezeroLeihoak.saioInformazioa;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SaioInformazioa extends Application {

	private BezeroBean bezeroData;
	
	public SaioInformazioa() {
		// TODO Auto-generated constructor stub
	}
	
	public void setBezeroData(BezeroBean bezeroa) {
		this.bezeroData = bezeroa;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SaioInformazioa.fxml"));
		Scene scene = new Scene(loader.load());

		SaioInformazioaKontrolagailua controller = loader.getController();
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
