package saltzaileLeihoak.saltzaileTaula;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Saltzaileen taula leihoaren klase nagusia.
 * <p>
 * Klase honek saltzaile guztien informazioa erakusten duen taula abiarazten du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class SaltzaileTaula extends Application {

    /** Saioa hasi duen saltzailearen datuak */
    private LangileSaltzaileBean saltzaileData;
    
    /**
     * Saltzailearen datuak ezartzen ditu.
     *
     * @param saltzailea saioa hasi duen saltzailearen datuak
     */
    public void setSaltzaileData(LangileSaltzaileBean saltzailea) {
        this.saltzaileData = saltzailea;
    }
    
    /**
     * Eraikitzaile lehenetsia.
     */
    public SaltzaileTaula() {
    }

    /**
     * JavaFX aplikazioaren hasierako metodoa.
     *
     * @param primaryStage aplikazioaren eszenatoki nagusia
     * @throws Exception FXML fitxategia kargatzean errorea gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SaltzaileTaula.fxml"));
        Scene scene = new Scene(loader.load());
        
        SaltzaileTaulaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Saltzaileen informazioa");
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