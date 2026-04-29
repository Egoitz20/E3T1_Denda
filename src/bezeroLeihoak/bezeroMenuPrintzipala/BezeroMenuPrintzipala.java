package bezeroLeihoak.bezeroMenuPrintzipala;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Bezeroaren menu nagusiaren aplikazio nagusia.
 * <p>
 * Menu nagusiaren leihoa abiarazten du eta bezeroaren datuak kudeatzen ditu.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 * @see BezeroMenuPrintzipalaKontrolagailua
 * @see datuBaseKonexioa.BezeroBean
 */
public class BezeroMenuPrintzipala extends Application {

    /**
     * Bezeroaren datuak gordetzeko atributua.
     */
    private BezeroBean bezeroData;

    /**
     * Bezeroaren datuak ezartzen ditu.
     *
     * @param bezeroa Bezeroaren datuak dituen BezeroBean objektua
     */
    public void setBezeroData(BezeroBean bezeroa) {
        this.bezeroData = bezeroa;
    }

    /**
     * BezeroMenuPrintzipala klasearen eraikitzaile hutsa.
     */
    public BezeroMenuPrintzipala() {

    }

    /**
     * Aplikazioaren hasierako metodoa. Menuko leihoa kargatzen eta erakusten du.
     *
     * @param primaryStage Leiho nagusia
     * @throws Exception FXMLa kargatzean errore bat gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BezeroMenuPrintzipala.fxml"));
        Scene scene = new Scene(loader.load());

        BezeroMenuPrintzipalaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setBezeroData(bezeroData);

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