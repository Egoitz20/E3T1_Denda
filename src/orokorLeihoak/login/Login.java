package orokorLeihoak.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Login aplikazioaren klase nagusia.
 * <p>
 * Klase honek erabiltzailearen saio hasierako leihoa abiarazten du, FXML
 * fitxategia kargatu eta kontrolagailua konfiguratzen du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */

public class Login extends Application {

	/**
	 * Login klasearen eraikitzaile lehenetsia.
	 */

	public Login() {

	}

	/**
	 * JavaFX aplikazioaren hasierako metodoa.
	 * <p>
	 * FXML fitxategia kargatzen du, eszena sortzen du, kontrolagailua lortzen du,
	 * eszenatokia pasatzen dio eta leihoa erakusten du.
	 * </p>
	 *
	 * @param primaryStage aplikazioaren eszenatoki nagusia
	 * @throws Exception FXML fitxategia edo eszena kargatzean errorea gertatuz gero
	 */

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(loader.load());

		// Lortu kontrolagailua eta stage pasatu
		LoginKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show();
	}

	/**
	 * Aplikazioa abiarazten duen metodo nagusia.
	 *
	 * @param args komando lerroko argumentuak (ez dira erabiltzen)
	 */
	public static void main(String[] args) {
		launch();
	}

}
