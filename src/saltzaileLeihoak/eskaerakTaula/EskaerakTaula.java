package saltzaileLeihoak.eskaerakTaula;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Bezeroaren eskaeren taula leihoaren klase nagusia.
 * <p>
 * Klase honek bezero jakin baten eskaera guztiak erakusten dituen taula
 * abiarazten du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class EskaerakTaula extends Application {

    /** Eskaerak erakutsi beharreko bezeroaren datuak */
    private BezeroBean bezeroData;

    /**
     * Eraikitzaile lehenetsia.
     */
    public EskaerakTaula() {
    }

    /**
     * Eskaerak erakutsi beharreko bezeroaren datuak ezartzen ditu.
     *
     * @param bezeroa bezeroaren datuak
     */
    public void setEskaeraBezeroData(BezeroBean bezeroa) {
        this.bezeroData = bezeroa;
    }

    /**
     * JavaFX aplikazioaren hasierako metodoa.
     *
     * @param primaryStage aplikazioaren eszenatoki nagusia
     * @throws Exception FXML fitxategia kargatzean errorea gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EskaeraTaula.fxml"));
        Scene scene = new Scene(loader.load());

        EskaerakTaulaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setEskaeraBezeroData(bezeroData);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Bezeroaren eskaerak informazioa");
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