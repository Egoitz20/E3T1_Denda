package saltzaileLeihoak.arazoDeskribapena;

import java.time.format.DateTimeFormatter;
import datuBaseKonexioa.AbisuakBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kontrolagailuGlobala.AbisuakGlobala;
import kontrolagailuGlobala.HandlerGlobala;

/**
 * Arazoaren deskribapena leihoaren kontrolagailua.
 * <p>
 * Klase honek abisu jakin baten informazio guztia erakusten du:
 * bezeroaren izena, data, kontzeptua eta deskribapena. Saltzaileak
 * "Amaituta" botoia sakatuz gero, abisua zerrenda globaletik ezabatzen du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class ArazoDeskribapenaKontrolagailua extends HandlerGlobala {

    /** Bezeroaren izena erakusteko etiketa */
    @FXML
    private Label bezeroIzenaLabel;

    /** Data erakusteko etiketa */
    @FXML
    private Label dataLabel;

    /** Kontzeptua erakusteko testu eremua */
    @FXML
    private TextField kontzeptuaField;

    /** Deskribapena erakusteko testu eremua */
    @FXML
    private TextArea deskribapenaArea;

    /** Ikusi beharreko abisua */
    private AbisuakBean abisua;

    /**
     * Eraikitzaile lehenetsia.
     */
    public ArazoDeskribapenaKontrolagailua() {
    }

    /**
     * Ikusi beharreko abisua ezartzen du eta datuak kargatzen ditu.
     *
     * @param abisua ikusi nahi den abisua
     */
    public void setAbisua(AbisuakBean abisua) {
        this.abisua = abisua;
        kargatuDatuak();
    }

    /**
     * Abisuaren datuak formularioan kargatzen ditu.
     */
    private void kargatuDatuak() {
        if (abisua != null) {
            bezeroIzenaLabel.setText(abisua.getBezeroIzena());
            
            if (abisua.getData() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                dataLabel.setText(abisua.getData().format(formatter));
            } else {
                dataLabel.setText("-");
            }
            
            kontzeptuaField.setText(abisua.getKontzeptua());
            deskribapenaArea.setText(abisua.getDeskribapena());
        }
    }

    /**
     * Itzuli botoiaren metodoa.
     * <p>
     * Leihoa ixten du eta abisuen zerrendara itzultzen da.
     * </p>
     */
    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
        irekiSaltzaileMenuPrintzipala();
    }

    /**
     * Amaituta botoiaren metodoa.
     * <p>
     * Abisua amaituta bezala markatzen du, zerrenda globaletik ezabatzen du
     * eta abisu-zerrendara itzultzen da.
     * </p>
     */
    @FXML
    public void amaituta() {
        if (abisua != null) {
            AbisuakGlobala.getInstantzia().getAbisuak().remove(abisua);
            irekiAlerta("Arrakasta", "Arazoa amaituta", 
                       "Bezeroaren arazoa amaitu bezala markatu da. Abisua kendu da.");
        }
        itzuli();
    }
}