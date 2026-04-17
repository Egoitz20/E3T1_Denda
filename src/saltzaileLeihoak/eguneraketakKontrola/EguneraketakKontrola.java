package saltzaileLeihoak.eguneraketakKontrola;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EguneraketakKontrola extends Application{

	public EguneraketakKontrola() {
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EguneraketakKontrola.fxml"));
		Scene scene = new Scene(loader.load());

		EguneraketakKontrolaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Bezeroaren eskaerak informazioa");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
