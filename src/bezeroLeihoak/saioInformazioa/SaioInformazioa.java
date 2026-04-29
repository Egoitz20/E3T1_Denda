package bezeroLeihoak.saioInformazioa;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Saioaren informazioa aplikazioa.
 * <p>
 * Bezeroaren datu pertsonalak ikusteko eta editatzeko leihoa abiarazten du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 * @see SaioInformazioaKontrolagailua
 */
public class SaioInformazioa extends Application {

    /**
     * Bezeroaren datuak.
     */
    private BezeroBean bezeroData;

    /**
     * Eraikitzaile hutsa.
     */
    public SaioInformazioa() {
    }

    /**
     * Bezeroaren datuak ezartzen ditu.
     *
     * @param bezeroa Bezeroaren datuak
     */
    public void setBezeroData(BezeroBean bezeroa) {
        this.bezeroData = bezeroa;
    }

    /**
     * Aplikazioaren hasierako metodoa.
     *
     * @param primaryStage Leiho nagusia
     * @throws Exception FXMLa kargatzean errore bat gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SaioInformazioa.fxml"));
        Scene scene = new Scene(loader.load());

        SaioInformazioaKontrolagailua controller = loader.getController();
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