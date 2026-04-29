package bezeroLeihoak.erosiMetodoa;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Ordainketa metodoa hautatzeko aplikazioa.
 * <p>
 * Erosketaren ordainketa metodoa aukeratzeko leihoa abiarazten du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 * @see ErosiMetodoaKontrolagailua
 */
public class ErosiMetodoa extends Application {

    /**
     * Bezeroaren datuak.
     */
    private BezeroBean bezeroData;

    /**
     * Bezeroaren datuak ezartzen ditu.
     *
     * @param bezeroa Bezeroaren datuak
     */
    public void setBezeroData(BezeroBean bezeroa) {
        this.bezeroData = bezeroa;
    }

    /**
     * Eraikitzaile hutsa.
     */
    public ErosiMetodoa() {
    }

    /**
     * Aplikazioaren hasierako metodoa.
     *
     * @param primaryStage Leiho nagusia
     * @throws Exception FXMLa kargatzean errore bat gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ErosiMetodoa.fxml"));
        Scene scene = new Scene(loader.load());

        ErosiMetodoaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Bezero Menu Printzipala");
        primaryStage.show();
    }

    /**
     * Aplikazioaren sarrera nagusia.
     *
     * @param args Komando lerroko argumentuak
     */
    public static void main(String[] args) {
        launch();
    }
}