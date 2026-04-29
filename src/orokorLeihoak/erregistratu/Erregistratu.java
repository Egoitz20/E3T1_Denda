package orokorLeihoak.erregistratu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Erregistro leihoaren klase nagusia.
 * <p>
 * Klase honek erabiltzaile berria erregistratzeko leihoa abiarazten du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class Erregistratu extends Application {

    /**
     * Erregistratu klasearen eraikitzaile lehenetsia.
     */
    public Erregistratu() {
    }
    
    /**
     * JavaFX aplikazioaren hasierako metodoa.
     * <p>
     * FXML fitxategia kargatzen du, eszena sortzen du, kontrolagailua lortzen du,
     * eszenatokia pasatzen dio eta leihoa erakusten du.
     * </p>
     *
     * @param primaryStage aplikazioaren eszenatoki nagusia
     * @throws Exception FXML fitxategia edo eszena kargatzean errorea gertatuz gero
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Erregistratu.fxml"));
        Scene scene = new Scene(loader.load());

        ErregistratuKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Erregistratu");
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