package saltzaileLeihoak.saltzaileMenuPrintzipala;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Saltzailearen menu nagusiaren klasea.
 * <p>
 * Klase honek saltzaileak saioa hasi ondoren ikusiko duen menu nagusia
 * abiarazten du. Saltzailearen datuak jasotzen ditu eta kontrolagailuari
 * pasatzen dizkio.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class SaltzaileMenuPrintzipala extends Application {

    /** Saioa hasi duen saltzailearen datuak */
    private LangileSaltzaileBean saltzaileData;

    /**
     * Eraikitzaile lehenetsia.
     */
    public SaltzaileMenuPrintzipala() {
    }
    
    /**
     * Saioa hasi duen saltzailearen datuak ezartzen ditu.
     *
     * @param saltzailea saioa hasi duen saltzailearen datuak
     */
    public void setSaltzaileData(LangileSaltzaileBean saltzailea) {
        this.saltzaileData = saltzailea;
    }

    /**
     * JavaFX aplikazioaren hasierako metodoa.
     * <p>
     * FXML fitxategia kargatzen du, eszena sortzen du, kontrolagailua lortzen du,
     * eszenatokia eta saltzailearen datuak pasatzen dizkio eta leihoa erakusten du.
     * </p>
     *
     * @param primaryStage aplikazioaren eszenatoki nagusia
     * @throws Exception FXML fitxategia edo eszena kargatzean errorea gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SaltzaileMenuPrintzipala.fxml"));
        Scene scene = new Scene(loader.load());
        
        SaltzaileMenuPrintzipalaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);
        
        if (saltzaileData != null) {
            controller.setSaltzaileData(saltzaileData);
        }

        primaryStage.setScene(scene);
        primaryStage.setTitle("Saltzaile Menu Printzipala");
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