package saltzaileLeihoak.abisuak;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Abisuen leihoaren klase nagusia.
 * <p>
 * Klase honek bezeroen abisu guztiak erakusten dituen leihoa abiarazten du.
 * Abisuak listan erakusten dira eta saltzaileak abisu bakoitza ikus dezake.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class Abisuak extends Application {

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
    public Abisuak() {
    }

    /**
     * JavaFX aplikazioaren hasierako metodoa.
     *
     * @param primaryStage aplikazioaren eszenatoki nagusia
     * @throws Exception FXML fitxategia kargatzean errorea gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Abisuak.fxml"));
        Scene scene = new Scene(loader.load());

        AbisuakKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Bezeroen Abisuak");
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