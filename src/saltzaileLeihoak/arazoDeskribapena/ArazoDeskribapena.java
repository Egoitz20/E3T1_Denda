package saltzaileLeihoak.arazoDeskribapena;

import datuBaseKonexioa.AbisuakBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ArazoDeskribapena extends Application {

    private AbisuakBean abisua;  // Abisua zeinari erreferentzia egiten dion

    public ArazoDeskribapena() {
    }

    public void setAbisua(AbisuakBean abisua) {
        this.abisua = abisua;
    }

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

    public static void main(String[] args) {
        launch();
    }
}