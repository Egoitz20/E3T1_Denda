package saltzaileLeihoak.bezeroKudeaketa;

import datuBaseKonexioa.BezeroBean;
import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Bezeroen kudeaketa leihoaren klase nagusia.
 * <p>
 * Klase honek bezeroak gehitu, aldatu edo ezabatzeko leihoa abiarazten du.
 * Bi modu ditu: sorkuntza modua (bezero berria sortzeko) eta edizio modua
 * (existitzen den bezero bat aldatu edo ezabatzeko).
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class BezeroKudeaketa extends Application {

    /** Editatu beharreko bezeroaren datuak */
    private BezeroBean bezeroData;
    
    /** Editatzeko moduan dagoen adierazten du */
    private boolean isEditMode = false;

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
    public BezeroKudeaketa() {
    }

    /**
     * Bezeroaren datuak ezartzen ditu eta edizio modua ezartzen du.
     *
     * @param bezeroa editatzeko bezeroaren datuak (null bada, sorkuntza modua)
     */
    public void setBezeroData(BezeroBean bezeroa) {
        this.bezeroData = bezeroa;
        this.isEditMode = (bezeroa != null);
    }

    /**
     * JavaFX aplikazioaren hasierako metodoa.
     *
     * @param primaryStage aplikazioaren eszenatoki nagusia
     * @throws Exception FXML fitxategia kargatzean errorea gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BezeroKudeaketa.fxml"));
        Scene scene = new Scene(loader.load());

        BezeroKudeaketaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setBezeroData(bezeroData, isEditMode);

        primaryStage.setScene(scene);
        primaryStage.setTitle(isEditMode ? "Bezeroa Editatu" : "Bezero Berria");
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