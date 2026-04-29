package bezeroLeihoak.storageTaula;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Storage produktuen taula leihoaren klase nagusia.
 * <p>
 * Klase honek Storage (disko gogorrak, SSDak) kategorian dauden produktu guztien
 * zerrenda erakusten duen taula abiarazten du. Bezeroak produktuak hautatu eta otzaran gehitu ditzake.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class StorageTaula extends Application {

    /** Saioa hasi duen bezeroaren datuak */
    private BezeroBean bezeroData;

    /**
     * Bezeroaren datuak ezartzen ditu.
     *
     * @param bezeroa saioa hasi duen bezeroaren datuak
     */
    public void setBezeroData(BezeroBean bezeroa) {
        this.bezeroData = bezeroa;
    }
    
    /**
     * Eraikitzaile lehenetsia.
     */
    public StorageTaula() {
    }

    /**
     * JavaFX aplikazioaren hasierako metodoa.
     *
     * @param primaryStage aplikazioaren eszenatoki nagusia
     * @throws Exception FXML fitxategia kargatzean errorea gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StorageTaula.fxml"));
        Scene scene = new Scene(loader.load());

        StorageTaulaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Storage Lista");
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