package orokorLeihoak.login;

import java.util.ArrayList;

import bezeroLeihoak.bezeroMenuPrintzipala.BezeroMenuPrintzipala;
import datuBaseKonexioa.BezeroBean;
import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;
import orokorLeihoak.erregistratu.Erregistratu;
import saltzaileLeihoak.saltzaileMenuPrintzipala.SaltzaileMenuPrintzipala;

/**
 * Login leihoaren kontrolagailua.
 * <p>
 * Klase honek erabiltzailearen sarrera kudeatzen du, saltzaileak eta bezeroak
 * identifikatzen ditu eta dagokion menu nagusia irekitzen du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class LoginKontrolagailua extends HandlerGlobala {

    @FXML
    private TextField erabiltzaileak;
    
    @FXML
    private PasswordField pasahitza;

    /** Sartutako saltzailearen datuak gordetzeko aldagaia */
    private LangileSaltzaileBean saltzaileLogeatuta;

    /** Sartutako bezeroaren datuak gordetzeko aldagaia */
    private BezeroBean bezeroLogeatuta;

    /**
     * LoginKontrolagailuaren eraikitzaile lehenetsia.
     */
    public LoginKontrolagailua() {
    }

    /**
     * 'Sartu' botoia sakatzean exekutatzen den metodoa.
     * <p>
     * Erabiltzaile eta pasahitzaren balidazioa egiten du, lehenik saltzailea
     * bilatzen du eta gero bezeroa. Aurkitzen badu dagokion menura bideratzen du.
     * </p>
     */
    @FXML
    public void sartu() {
        String jasotakoErabiltzailea = erabiltzaileak.getText();
        String jasotakoPasahitza = pasahitza.getText();

        if (jasotakoErabiltzailea.isEmpty() || jasotakoPasahitza.isEmpty()) {
            irekiAlerta("Errorea", "Erabiltzailea eta pasahitza bete behar dira",
                    "Mesedez, bete erabiltzailea eta pasahitza.");
            return;
        }

        // Lehenik, saltzailea den konprobatu
        if (konprobatuEtaLortuSaltzailea(jasotakoErabiltzailea, jasotakoPasahitza)) {
            irekiSaltzaileMenuPrintzipalaDatuekin();
        }
        // Saltzailea ez bada, bezeroa den konprobatu
        else if (konprobatuEtaLortuBezeroa(jasotakoErabiltzailea, jasotakoPasahitza)) {
            irekiBezeroMenuPrintzipalaDatuekin();
        } else {
            irekiAlerta("Errorea", "Erabiltzailea edo pasahitza okerra",
                    "Sartutako erabiltzailea edo pasahitza ez da zuzena.");
        }
    }

    /**
     * Erabiltzailea saltzailea den konprobatu eta bere datuak lortzen ditu.
     *
     * @param erabiltzailea sartutako erabiltzaile izena
     * @param pasahitza sartutako pasahitza
     * @return {@code true} saltzailea existitzen bada, bestela {@code false}
     */
    private boolean konprobatuEtaLortuSaltzailea(String erabiltzailea, String pasahitza) {
        ArrayList<LangileSaltzaileBean> saltzaileak = jasoLangileSaltzaileak();

        for (LangileSaltzaileBean saltzaile : saltzaileak) {
            if (saltzaile.getErabiltzailea() != null && saltzaile.getErabiltzailea().equals(erabiltzailea)
                    && saltzaile.getPasahitza().equals(pasahitza)) {
                saltzaileLogeatuta = saltzaile;
                setSaltzaileLogeatuta(saltzaile);
                return true;
            }
        }
        return false;
    }

    /**
     * Erabiltzailea bezeroa den konprobatu eta bere datuak lortzen ditu.
     * <p>
     * Bi modu onartzen ditu: erabiltzaile/pasahitzarekin edo izen/abizenarekin.
     * </p>
     *
     * @param erabiltzailea sartutako erabiltzaile izena (edo izena)
     * @param pasahitza sartutako pasahitza (edo abizena)
     * @return {@code true} bezeroa existitzen bada, bestela {@code false}
     */
    private boolean konprobatuEtaLortuBezeroa(String erabiltzailea, String pasahitza) {
        ArrayList<BezeroBean> bezeroak = jasoBezeroak();

        for (BezeroBean bezero : bezeroak) {
            if (bezero.getErabiltzaile() != null && bezero.getPasahitza() != null) {
                // Erabiltzaile eta pasahitzarekin saioa hasi
                if (bezero.getErabiltzaile().equals(erabiltzailea) && bezero.getPasahitza().equals(pasahitza)) {
                    bezeroLogeatuta = bezero;
                    setBezeroLogeatuta(bezero);
                    return true;
                // Izena eta abizenarekin saioa hasi
                } else if (bezero.getIzena().equals(erabiltzailea) && bezero.getAbizena().equals(pasahitza)) {
                    bezeroLogeatuta = bezero;
                    setBezeroLogeatuta(bezero);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Saltzailearen menu nagusia irekitzen du, saltzailearen datuekin.
     */
    private void irekiSaltzaileMenuPrintzipalaDatuekin() {
        try {
            SaltzaileMenuPrintzipala saltzaileMenu = new SaltzaileMenuPrintzipala();
            Stage newStage = new Stage();

            saltzaileMenu.setSaltzaileData(saltzaileLogeatuta);
            saltzaileMenu.start(newStage);

            itxiOraingoLeihoa();

        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea saltzailearen menua irekitzean: " + e.getMessage());
        }
    }

    /**
     * Bezeroaren menu nagusia irekitzen du, bezeroaren datuekin.
     */
    private void irekiBezeroMenuPrintzipalaDatuekin() {
        try {
            BezeroMenuPrintzipala bezeroMenu = new BezeroMenuPrintzipala();
            Stage newStage = new Stage();

            bezeroMenu.setBezeroData(bezeroLogeatuta);
            bezeroMenu.start(newStage);

            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea bezeroaren menua irekitzean: " + e.getMessage());
        }
    }

    /**
     * 'Erregistratu' botoia sakatzean exekutatzen den metodoa.
     * <p>
     * Leihoa ezkutatu eta erregistro leihoa irekitzen du.
     * </p>
     */
    @FXML
    public void erregistratu() {
        okultatuLeihoa();
        irekiErregistratu();
    }

    /**
     * Erregistro leihoa irekitzen du.
     * <p>
     * Erregistro leihoa ixtean, login leihoa berriz erakusten du.
     * </p>
     */
    private void irekiErregistratu() {
        try {
            Erregistratu erregitratuLeihoa = new Erregistratu();
            Stage newStage = new Stage();

            erregitratuLeihoa.start(newStage);

            newStage.setOnHidden(_ -> {
                ikusiLeihoa();
            });

        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea bezeroaren menua irekitzean: " + e.getMessage());
        }
    }
}