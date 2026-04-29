package bezeroLeihoak.otzara;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Otzararen aplikazioa.
 * <p>
 * Bezeroak erositako produktuak ikusteko eta kudeatzeko leihoa abiarazten du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 * @see OtzaraKontrolagailua
 */
public class Otzara extends Application {

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
    public Otzara() {
    }

    /**
     * Aplikazioaren hasierako metodoa.
     *
     * @param primaryStage Leiho nagusia
     * @throws Exception FXMLa kargatzean errore bat gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Otzara.fxml"));
        Scene scene = new Scene(loader.load());

        OtzaraKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Otzara");
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