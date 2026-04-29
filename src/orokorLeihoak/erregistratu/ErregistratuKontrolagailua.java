package orokorLeihoak.erregistratu;

import java.util.ArrayList;

import datuBaseKonexioa.BezeroBean;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kontrolagailuGlobala.HandlerGlobala;

/**
 * Erregistro leihoaren kontrolagailua.
 * <p>
 * Klase honek erabiltzaile berri baten erregistroa kudeatzen du,
 * pasahitzak konparatu eta erabiltzailea datu-basean gordetzen du.
 * </p>
 * 
 * @authorAIA
 * @version 1.0
 */
public class ErregistratuKontrolagailua extends HandlerGlobala {

    @FXML
    private TextField erabiltzaileBerria;
    
    @FXML
    private PasswordField pasahitzaBerria;
    
    @FXML
    private PasswordField errepikatuPasahitzaBerria;

    /**
     * ErregistratuKontrolagailuaren eraikitzaile lehenetsia.
     */
    public ErregistratuKontrolagailua() {
    }

    /**
     * 'Itzuli' botoia sakatzean exekutatzen den metodoa.
     * <p>
     * Leihoa ixten du.
     * </p>
     */
    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
    }

    /**
     * 'Erabiltzaile sortu' botoia sakatzean exekutatzen den metodoa.
     * <p>
     * Pasahitzak konparatzen ditu, erabiltzailea existitzen den egiaztatzen du,
     * eta existitzen ez bada, erabiltzaile berria sortzen du.
     * </p>
     */
    @FXML
    public void sortuErabiltzailea() {
        String jasotakoErabiltzaileBerria = erabiltzaileBerria.getText();
        String jasotakoPasahitzaBerria = pasahitzaBerria.getText();
        String jasotakoErrepikatuPasahitzaBerria = errepikatuPasahitzaBerria.getText();

        if (pasahitzaKonparaketa(jasotakoPasahitzaBerria, jasotakoErrepikatuPasahitzaBerria)) {
            if (konprobatuBezeroa(jasotakoErabiltzaileBerria, jasotakoPasahitzaBerria)) {
                irekiAlerta("Errorea", "Erabiltzaile dago sortuta",
                        "Erabiltzaile sortuta dago, mesedez saiatu erabiltzaile edo pasahitza berri batekin");
            } else {
                erregistroBezeroBerriaSortu(jasotakoErabiltzaileBerria, jasotakoPasahitzaBerria);
                irekiAlerta("Sortuta", "Erabiltzaile Sortuta", "Erabiltzale ondo sortu da.");
            }
        }
    }

    /**
     * Erabiltzailea dagoeneko existitzen den konprobatu.
     *
     * @param erabiltzailea konprobatu nahi den erabiltzaile izena
     * @param pasahitza erabiltzailearen pasahitza (ez da erabiltzen konparaketan)
     * @return {@code true} erabiltzailea existitzen bada, bestela {@code false}
     */
    private boolean konprobatuBezeroa(String erabiltzailea, String pasahitza) {
        ArrayList<BezeroBean> bezeroak = jasoBezeroak();

        for (BezeroBean bezero : bezeroak) {
            if (bezero.getErabiltzaile() != null && bezero.getErabiltzaile().equals(erabiltzailea)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Bi pasahitzak berdinak diren konparatzen du.
     *
     * @param pasahitza1 lehenengo pasahitza
     * @param pasahitza2 bigarren pasahitza
     * @return {@code true} pasahitzak berdinak badira, bestela {@code false}
     */
    private boolean pasahitzaKonparaketa(String pasahitza1, String pasahitza2) {
        return pasahitza1.equals(pasahitza2);
    }
}