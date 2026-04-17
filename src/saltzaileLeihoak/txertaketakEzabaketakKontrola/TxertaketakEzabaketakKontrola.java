package saltzaileLeihoak.txertaketakEzabaketakKontrola;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TxertaketakEzabaketakKontrola extends Application {

	public TxertaketakEzabaketakKontrola() {
	}
	
	@Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TxertaketakEzabaketakKontrola.fxml"));
        Scene scene = new Scene(loader.load());

        TxertaketakEzabaketakKontrolaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Erabiltzaileen txertketak edo ezabaketak informazioa");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
