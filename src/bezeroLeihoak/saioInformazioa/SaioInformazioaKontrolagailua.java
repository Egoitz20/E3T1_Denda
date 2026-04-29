package bezeroLeihoak.saioInformazioa;

import datuBaseKonexioa.BezeroBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kontrolagailuGlobala.HandlerGlobala;

/**
 * Saioaren informazioaren kontrolagailua.
 * <p>
 * Bezeroaren datu pertsonalak kudeatzen ditu: ikusi, editatu eta gorde.
 * Bi modu ditu: irakurketa modua eta edizio modua.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 * @see HandlerGlobala
 */
public class SaioInformazioaKontrolagailua extends HandlerGlobala {

    /**
     * Datuen gordetzeko botoia.
     */
    @FXML
    private Button gordeButton;

    /**
     * Izenaren testu eremua.
     */
    @FXML
    private TextField ipiniIzena;

    /**
     * Abizenaren testu eremua.
     */
    @FXML
    private TextField ipiniAbizenak;

    /**
     * NANaren testu eremua.
     */
    @FXML
    private TextField ipiniNan;

    /**
     * Telefonoaren testu eremua.
     */
    @FXML
    private TextField ipiniTelefonoa;

    /**
     * Helbidearen testu eremua.
     */
    @FXML
    private TextField ipiniHelbidea;

    /**
     * Emailaren testu eremua.
     */
    @FXML
    private TextField ipiniEmaila;

    /**
     * Erabiltzailearen testu eremua.
     */
    @FXML
    private TextField ipiniErabiltzailea;

    /**
     * Pasahitzaren testu eremua.
     */
    @FXML
    private TextField ipiniPasahitza;

    /**
     * Izena editatzeko botoia.
     */
    @FXML
    private Button editatuIzenaButton;

    /**
     * Abizena editatzeko botoia.
     */
    @FXML
    private Button editatuAbizenaButton;

    /**
     * NAN editatzeko botoia.
     */
    @FXML
    private Button editatuNanButton;

    /**
     * Telefonoa editatzeko botoia.
     */
    @FXML
    private Button editatuTelefonoaButton;

    /**
     * Helbidea editatzeko botoia.
     */
    @FXML
    private Button editatuHelbideaButton;

    /**
     * Emaila editatzeko botoia.
     */
    @FXML
    private Button editatuEmailaButton;

    /**
     * Erabiltzailea editatzeko botoia.
     */
    @FXML
    private Button editatuErabiltzaileaButton;

    /**
     * Pasahitza editatzeko botoia.
     */
    @FXML
    private Button editatuPasahitzaButton;

    /**
     * Bezeroaren datuak.
     */
    private BezeroBean bezeroData;

    /**
     * Jatorrizko izena.
     */
    private String jatorrizkoIzena;

    /**
     * Jatorrizko abizena.
     */
    private String jatorrizkoAbizena;

    /**
     * Jatorrizko NAN.
     */
    private String jatorrizkoNan;

    /**
     * Jatorrizko telefonoa.
     */
    private String jatorrizkoTelefonoa;

    /**
     * Jatorrizko helbidea.
     */
    private String jatorrizkoHelbidea;

    /**
     * Jatorrizko emaila.
     */
    private String jatorrizkoEmaila;

    /**
     * Jatorrizko erabiltzailea.
     */
    private String jatorrizkoErabiltzailea;

    /**
     * Jatorrizko pasahitza.
     */
    private String jatorrizkoPasahitza;

    /**
     * Bezeroaren datuak ezartzen ditu.
     *
     * @param data Bezeroaren datuak
     */
    public void setBezeroData(BezeroBean data) {
        this.bezeroData = data;
        kargatuDatuakFormularioan();
    }

    /**
     * Eraikitzaile hutsa.
     */
    public SaioInformazioaKontrolagailua() {

    }

    /**
     * Hasieratzen du kontrolagailua.
     * <p>
     * Irakurketa modua konfiguratu eta edizio botoiak ezartzen ditu.
     * </p>
     */
    @FXML
    public void initialize() {
        // Lehenenik, eremuak bakarrik irakurtzeko konfiguratzen da.
        konfiguratuIrakurketaModua();

        // Edizio botoiak konfiguratzen dira
        konfiguratuEditatuBotoiak();
    }

    /**
     * Datu berriak gordetzen ditu.
     * <p>
     * Derrigorrezko eremuak beteta dauden egiaztatu eta datuak eguneratzen ditu.
     * </p>
     */
    @FXML
    public void gordeInfo() {
        if (ipiniIzena.getText().isEmpty() || ipiniNan.getText().isEmpty() || ipiniTelefonoa.getText().isEmpty()
                || ipiniEmaila.getText().isEmpty() || ipiniErabiltzailea.getText().isEmpty()
                || ipiniPasahitza.getText().isEmpty()) {

            irekiAlerta("Errorea", "Eremuak hutsik", "Derrigorrezko eremuak hutsik daude. Mesedez bete eremu horiek");
            return;
        }

        // BezeroBean objektua eguneratu
        bezeroData.setIzena(ipiniIzena.getText());
        bezeroData.setAbizena(ipiniAbizenak.getText());
        bezeroData.setNan(ipiniNan.getText());
        bezeroData.setZenbakia(ipiniTelefonoa.getText());
        bezeroData.setHelbidea(ipiniHelbidea.getText());
        bezeroData.setEmaila(ipiniEmaila.getText());
        bezeroData.setErabiltzaile(ipiniErabiltzailea.getText());
        bezeroData.setPasahitza(ipiniPasahitza.getText());

        // Prozedimentua deitzen da datu berriak gordetzeko
        eguneratuBezeroa(bezeroData.getId(), bezeroData.getIzena(), bezeroData.getAbizena(), bezeroData.getNan(),
                bezeroData.getEmaila(), bezeroData.getHelbidea(), bezeroData.getErabiltzaile(),
                bezeroData.getPasahitza(), bezeroData.getZenbakia());

        irekiAlerta("Arrakasta", "Datak eguneratuta", "Zure informazioa ondo eguneratu da.");

        //Irakurtzeko modua bueltatzen da.
        konfiguratuIrakurketaModua();
    }

    /**
     * Datu baseko datuak formularioan kargatzen ditu.
     */
    private void kargatuDatuakFormularioan() {
        if (bezeroData != null) {
            ipiniIzena.setText(bezeroData.getIzena());
            ipiniAbizenak.setText(bezeroData.getAbizena());
            ipiniNan.setText(bezeroData.getNan());
            ipiniTelefonoa.setText(bezeroData.getZenbakia());
            ipiniHelbidea.setText(bezeroData.getHelbidea());
            ipiniEmaila.setText(bezeroData.getEmaila());
            ipiniErabiltzailea.setText(bezeroData.getErabiltzaile());
            ipiniPasahitza.setText(bezeroData.getPasahitza());

        }
    }

    /**
     * Irakurketa modua konfiguratzen du.
     * <p>
     * Eremu guztiak irakurtzeko bakarrik jartzen ditu eta gorde botoia ezkutatzen du.
     * </p>
     */
    private void konfiguratuIrakurketaModua() {
        ipiniIzena.setEditable(false);
        ipiniAbizenak.setEditable(false);
        ipiniNan.setEditable(false);
        ipiniTelefonoa.setEditable(false);
        ipiniHelbidea.setEditable(false);
        ipiniEmaila.setEditable(false);
        ipiniErabiltzailea.setEditable(false);
        ipiniPasahitza.setEditable(false);

        gordeButton.setVisible(false);
        gordeButton.setManaged(false);
    }

    /**
     * Edizio modua konfiguratzen du.
     * <p>
     * Eremu guztiak editagarri jartzen ditu, gorde botoia erakutsi
     * eta jatorrizko balioak gordetzen ditu.
     * </p>
     */
    private void konfiguratuEditatuModua() {
        ipiniIzena.setEditable(true);
        ipiniAbizenak.setEditable(true);
        ipiniNan.setEditable(true);
        ipiniTelefonoa.setEditable(true);
        ipiniHelbidea.setEditable(true);
        ipiniEmaila.setEditable(true);
        ipiniErabiltzailea.setEditable(true);
        ipiniPasahitza.setEditable(true);

        gordeButton.setVisible(true);
        gordeButton.setManaged(true);

        // Jatorrizko balioak gorde egiten dira editatu aurretik
        jatorrizkoIzena = ipiniIzena.getText();
        jatorrizkoAbizena = ipiniAbizenak.getText();
        jatorrizkoNan = ipiniNan.getText();
        jatorrizkoTelefonoa = ipiniTelefonoa.getText();
        jatorrizkoHelbidea = ipiniHelbidea.getText();
        jatorrizkoEmaila = ipiniEmaila.getText();
        jatorrizkoErabiltzailea = ipiniErabiltzailea.getText();
        jatorrizkoPasahitza = ipiniPasahitza.getText();
    }

    /**
     * Editatzeko botoiak konfiguratzen ditu.
     */
    private void konfiguratuEditatuBotoiak() {
        editatuIzenaButton.setOnAction(_ -> konfiguratuEditatuModua());
        editatuAbizenaButton.setOnAction(_ -> konfiguratuEditatuModua());
        editatuNanButton.setOnAction(_ -> konfiguratuEditatuModua());
        editatuTelefonoaButton.setOnAction(_ -> konfiguratuEditatuModua());
        editatuHelbideaButton.setOnAction(_ -> konfiguratuEditatuModua());
        editatuEmailaButton.setOnAction(_ -> konfiguratuEditatuModua());
        editatuErabiltzaileaButton.setOnAction(_ -> konfiguratuEditatuModua());
        editatuPasahitzaButton.setOnAction(_ -> konfiguratuEditatuModua());
    }

    /**
     * Menu nagusira itzultzen da.
     */
    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
        irekiBezeroMenuPrintzipala();
    }

    /**
     * Izena editatzeko modua abiarazten du.
     */
    @FXML
    public void editatuIzena() {
        konfiguratuEditatuModua();
        ipiniIzena.requestFocus();
    }

    /**
     * Abizena editatzeko modua abiarazten du.
     */
    @FXML
    public void editatuAbizena() {
        konfiguratuEditatuModua();
        ipiniAbizenak.requestFocus();
    }

    /**
     * NAN editatzeko modua abiarazten du.
     */
    @FXML
    public void editatuNan() {
        konfiguratuEditatuModua();
        ipiniNan.requestFocus();
    }

    /**
     * Telefonoa editatzeko modua abiarazten du.
     */
    @FXML
    public void editatuTelefonoa() {
        konfiguratuEditatuModua();
        ipiniTelefonoa.requestFocus();
    }

    /**
     * Helbidea editatzeko modua abiarazten du.
     */
    @FXML
    public void editatuHelbidea() {
        konfiguratuEditatuModua();
        ipiniHelbidea.requestFocus();
    }

    /**
     * Emaila editatzeko modua abiarazten du.
     */
    @FXML
    public void editatuEmaila() {
        konfiguratuEditatuModua();
        ipiniEmaila.requestFocus();
    }

    /**
     * Erabiltzailea editatzeko modua abiarazten du.
     */
    @FXML
    public void editatuErabiltzailea() {
        konfiguratuEditatuModua();
        ipiniErabiltzailea.requestFocus();
    }

    /**
     * Pasahitza editatzeko modua abiarazten du.
     */
    @FXML
    public void editatuPasahitza() {
        konfiguratuEditatuModua();
        ipiniPasahitza.requestFocus();
    }
}