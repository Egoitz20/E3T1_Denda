package saltzaileLeihoak.saltzaileKudeaketa;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SaltzaileKudeaketa extends Application {

	private LangileSaltzaileBean saltzaileData;
    private boolean isEditMode = false; 
	
	public SaltzaileKudeaketa() {
	}
	
	// ✅ Método para recibir los datos desde la tabla
    public void setSaltzaileData(LangileSaltzaileBean saltzailea) {
        this.saltzaileData = saltzailea;
        this.isEditMode = (saltzailea != null);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SaltzaileKudeaketa.fxml"));
		Scene scene = new Scene(loader.load());
		
		SaltzaileKudeaketaKontrolagailua controller = loader.getController();
		controller.setStage(primaryStage);
		
		//Saltzailearen datuak eta editatzeko modua pasatzen ditu

        controller.setSaltzaileData(saltzaileData, isEditMode);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Saltzaile Kudeaketa");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();

	}

}
