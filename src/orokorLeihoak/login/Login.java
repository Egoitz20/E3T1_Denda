package orokorLeihoak.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {
	
	public Login () {
		
	}

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
	
	
	public static void main(String[] args) {
		launch();
	}


}


