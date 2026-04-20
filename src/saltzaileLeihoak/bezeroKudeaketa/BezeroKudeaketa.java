package saltzaileLeihoak.bezeroKudeaketa;

import datuBaseKonexioa.BezeroBean;
import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BezeroKudeaketa extends Application {

	private BezeroBean bezeroData;
	private boolean isEditMode = false;

	private LangileSaltzaileBean saltzaileData;

	public void setSaltzaileData(LangileSaltzaileBean saltzailea) {
		this.saltzaileData = saltzailea;
	}

	public BezeroKudeaketa() {
	}

	// Método para recibir los datos desde la tabla
	public void setBezeroData(BezeroBean bezeroa) {
		this.bezeroData = bezeroa;
		this.isEditMode = (bezeroa != null);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BezeroKudeaketa.fxml"));
		Scene scene = new Scene(loader.load());

		BezeroKudeaketaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);
		controller.setBezeroData(bezeroData, isEditMode);

		primaryStage.setScene(scene);
		primaryStage.setTitle(isEditMode ? "Bezeroa Editatu" : "Bezero Berria");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}