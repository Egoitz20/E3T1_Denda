package saltzaileLeihoak.arazoDeskribapena;

import java.time.format.DateTimeFormatter;
import datuBaseKonexioa.AbisuakBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kontrolagailuGlobala.AbisuakGlobala;
import kontrolagailuGlobala.HandlerGlobala;

public class ArazoDeskribapenaKontrolagailua extends HandlerGlobala {

    @FXML
    private Label bezeroIzenaLabel;

    @FXML
    private Label dataLabel;

    @FXML
    private TextField kontzeptuaField;

    @FXML
    private TextArea deskribapenaArea;

    private AbisuakBean abisua;  // Abisua zeinari erreferentzia egiten dion

    public ArazoDeskribapenaKontrolagailua() {
    }

    public void setAbisua(AbisuakBean abisua) {
        this.abisua = abisua;
        kargatuDatuak();
    }

    private void kargatuDatuak() {
        if (abisua != null) {
            // Bezeroaren izena
            bezeroIzenaLabel.setText(abisua.getBezeroIzena());
            
            // Data formateatu
            if (abisua.getData() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                dataLabel.setText(abisua.getData().format(formatter));
            } else {
                dataLabel.setText("-");
            }
            
            // Kontzeptua eta deskribapena
            kontzeptuaField.setText(abisua.getKontzeptua());
            deskribapenaArea.setText(abisua.getDeskribapena());
        }
    }

    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
        irekiSaltzaileMenuPrintzipala();
    }

    @FXML
    public void amaituta() {
        // Abisua ezabatu zerrenda globaletik
        if (abisua != null) {
            AbisuakGlobala.getInstantzia().getAbisuak().remove(abisua);
            irekiAlerta("Arrakasta", "Arazoa amaituta", 
                       "Bezeroaren arazoa amaitu bezala markatu da. Abisua kendu da.");
        }
        itzuli();
    }
}