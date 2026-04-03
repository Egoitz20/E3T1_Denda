package saltzaileLeihoak.saltzaileMenuPrintzipala;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SaltzaileMenuPrintzipala extends Application {

    private LangileSaltzaileBean saltzaileData;  // Logeatutako saltzaileen datuak

    public SaltzaileMenuPrintzipala() {
    }
    
    // Logeatutako saltzaileen datuak bidaltzeko metodoa 
    public void setSaltzaileData(LangileSaltzaileBean saltzailea) {
        this.saltzaileData = saltzailea;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SaltzaileMenuPrintzipala.fxml"));
        Scene scene = new Scene(loader.load());
        
        SaltzaileMenuPrintzipalaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);
        
        // Pasar los datos del vendedor al controlador
        if (saltzaileData != null) {
            controller.setSaltzaileData(saltzaileData);
        }

        primaryStage.setScene(scene);
        primaryStage.setTitle("Saltzaile Menu Printzipala");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}