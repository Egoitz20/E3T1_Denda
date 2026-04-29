package saltzaileLeihoak.txertaketakEzabaketakKontrola;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Txertaketen eta ezabaketen kontrola leihoaren klase nagusia.
 * <p>
 * Klase honek datu-basean egindako txertaketa eta ezabaketa guztien
 * erregistroa erakusten du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class TxertaketakEzabaketakKontrola extends Application {

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
    public TxertaketakEzabaketakKontrola() {
    }

    /**
     * JavaFX aplikazioaren hasierako metodoa.
     *
     * @param primaryStage aplikazioaren eszenatoki nagusia
     * @throws Exception FXML fitxategia kargatzean errorea gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TxertaketakEzabaketakKontrola.fxml"));
        Scene scene = new Scene(loader.load());

        TxertaketakEzabaketakKontrolaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Erabiltzaileen txertaketak edo ezabaketak informazioa");
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