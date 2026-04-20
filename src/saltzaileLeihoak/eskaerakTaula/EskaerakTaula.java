package saltzaileLeihoak.eskaerakTaula;

import datuBaseKonexioa.BezeroBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EskaerakTaula extends Application {

    private BezeroBean bezeroData;

    public EskaerakTaula() {
    }

    public void setEskaeraBezeroData(BezeroBean bezeroa) {
        this.bezeroData = bezeroa;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EskaeraTaula.fxml"));
        Scene scene = new Scene(loader.load());

        EskaerakTaulaKontrolagailua controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setEskaeraBezeroData(bezeroData);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Bezeroaren eskaerak informazioa");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}