package saltzaileLeihoak.bezeroTaulaEskaerak;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Bezeroen eskaeren taula leihoaren klase nagusia.
 * <p>
 * Klase honek bezero guztien zerrenda erakusten du taula batean,
 * bakoitzarekin lotutako eskaerak ikusteko botoi batekin.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class BezeroTaulaEskaerak extends Application {

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
    public BezeroTaulaEskaerak() {
    }

    /**
     * JavaFX aplikazioaren hasierako metodoa.
     *
     * @param primaryStage aplikazioaren eszenatoki nagusia
     * @throws Exception FXML fitxategia kargatzean errorea gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BezeroTaulaEskaerak.fxml"));
        Scene scene = new Scene(loader.load());

        BezeroTaulaEskaerakKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Bezeroaren informazioa eskaerarako");
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