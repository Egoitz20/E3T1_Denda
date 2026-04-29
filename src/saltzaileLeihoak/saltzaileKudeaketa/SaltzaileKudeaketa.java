package saltzaileLeihoak.saltzaileKudeaketa;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Saltzaileen kudeaketa leihoaren klase nagusia.
 * <p>
 * Klase honek saltzaileak gehitu, aldatu edo ezabatzeko leihoa abiarazten du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class SaltzaileKudeaketa extends Application {

    /** Editatu beharreko saltzailearen datuak */
    private LangileSaltzaileBean saltzaileData;
    
    /** Editatzeko moduan dagoen adierazten du */
    private boolean isEditMode = false; 
    
    /**
     * Eraikitzaile lehenetsia.
     */
    public SaltzaileKudeaketa() {
    }
    
    /**
     * Saltzailearen datuak ezartzen ditu eta edizio modua ezartzen du.
     *
     * @param saltzailea editatzeko saltzailearen datuak (null bada, sorkuntza modua)
     */
    public void setSaltzaileData(LangileSaltzaileBean saltzailea) {
        this.saltzaileData = saltzailea;
        this.isEditMode = (saltzailea != null);
    }
    
    /**
     * JavaFX aplikazioaren hasierako metodoa.
     *
     * @param primaryStage aplikazioaren eszenatoki nagusia
     * @throws Exception FXML fitxategia kargatzean errorea gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SaltzaileKudeaketa.fxml"));
        Scene scene = new Scene(loader.load());
        
        SaltzaileKudeaketaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setSaltzaileData(saltzaileData, isEditMode);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Saltzaile Kudeaketa");
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