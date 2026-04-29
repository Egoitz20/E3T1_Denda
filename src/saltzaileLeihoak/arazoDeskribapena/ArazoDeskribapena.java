package saltzaileLeihoak.arazoDeskribapena;

import datuBaseKonexioa.AbisuakBean;
import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Arazoaren deskribapena leihoaren klase nagusia.
 * <p>
 * Klase honek abisu jakin baten informazio osoa erakusten duen leihoa
 * abiarazten du: bezeroa, data, kontzeptua eta deskribapena.
 * Saltzaileak arazoa "amaituta" bezala markatu dezake.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class ArazoDeskribapena extends Application {

    /** Ikusi beharreko abisua */
    private AbisuakBean abisua;

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
    public ArazoDeskribapena() {
    }

    /**
     * Ikusi beharreko abisua ezartzen du.
     *
     * @param abisua ikusi nahi den abisua
     */
    public void setAbisua(AbisuakBean abisua) {
        this.abisua = abisua;
    }

    /**
     * JavaFX aplikazioaren hasierako metodoa.
     *
     * @param primaryStage aplikazioaren eszenatoki nagusia
     * @throws Exception FXML fitxategia kargatzean errorea gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ArazoDeskribapena.fxml"));
        Scene scene = new Scene(loader.load());

        ArazoDeskribapenaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setAbisua(abisua);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Arazo deskribapena");
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