package saltzaileLeihoak.bezeroKudeaketa;

import datuBaseKonexioa.BezeroBean;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import kontrolagailuGlobala.HandlerGlobala;

/**
 * Bezeroen kudeaketa leihoaren kontrolagailua.
 * <p>
 * Klase honek bezeroak gehitu, aldatu eta ezabatzeko formularioa kudeatzen du.
 * Bi modu ditu: sorkuntza modua (bezero berria sortzeko) eta edizio modua
 * (existitzen den bezero bat aldatu edo ezabatzeko).
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class BezeroKudeaketaKontrolagailua extends HandlerGlobala {

    // Formularioaren eremuak
    @FXML private TextField ipiniId;
    @FXML private TextField ipiniIzenAbizenak;
    @FXML private TextField ipiniNan;
    @FXML private TextField ipiniEmaila;
    @FXML private TextField ipiniTelefonoa;
    @FXML private TextField ipiniHelbidea;
    @FXML private TextField ipiniErabiltzailea;
    @FXML private TextField ipiniPasahitza;

    // Botoiak
    @FXML private Button sortuButton;
    @FXML private Button aldatuButton;
    @FXML private Button ezabatuButton;
    @FXML private Button itzuliButton;

    /** Editatu beharreko bezeroaren datuak */
    private BezeroBean bezeroInfo;
    
    /** Editatzeko moduan dagoen adierazten du */
    private boolean editazekoModoa = false;

    /**
     * Eraikitzaile lehenetsia.
     */
    public BezeroKudeaketaKontrolagailua() {
    }

    /**
     * Bezeroaren datuak jasotzen ditu eta interfazea konfiguratzen du moduaren arabera.
     *
     * @param data editatzeko bezeroaren datuak (null bada, sorkuntza modua)
     * @param editMode {@code true} edizio moduan, {@code false} sorkuntza moduan
     */
    public void setBezeroData(BezeroBean data, boolean editMode) {
        this.bezeroInfo = data;
        this.editazekoModoa = editMode;

        if (editMode && data != null) {
            kargatuDatuakFormularioan();
            sortuButton.setVisible(false);
            sortuButton.setManaged(false);
            aldatuButton.setVisible(true);
            aldatuButton.setManaged(true);
            ezabatuButton.setVisible(true);
            ezabatuButton.setManaged(true);
            ipiniId.setEditable(false);
        } else {
            sortuButton.setVisible(true);
            sortuButton.setManaged(true);
            aldatuButton.setVisible(false);
            aldatuButton.setManaged(false);
            ezabatuButton.setVisible(false);
            ezabatuButton.setManaged(false);
            garbituEremuak();
            ipiniId.setEditable(false);
        }
    }

    /**
     * Bezeroaren datuak formularioaren eremuetan kargatzen ditu.
     */
    private void kargatuDatuakFormularioan() {
        if (bezeroInfo != null) {
            ipiniId.setText(String.valueOf(bezeroInfo.getId()));

            String izenaOsoa = bezeroInfo.getIzena() + " " + bezeroInfo.getAbizena();
            ipiniIzenAbizenak.setText(izenaOsoa);

            ipiniEmaila.setText(bezeroInfo.getEmaila());
            ipiniHelbidea.setText(bezeroInfo.getHelbidea());
            ipiniErabiltzailea.setText(bezeroInfo.getErabiltzaile());
            ipiniPasahitza.setText(bezeroInfo.getPasahitza());
            ipiniNan.setText(bezeroInfo.getNan());
            ipiniTelefonoa.setText(bezeroInfo.getZenbakia());
        }
    }

    /**
     * Itzuli botoiaren metodoa.
     * <p>
     * Leihoa ixten du eta saltzaile menu nagusira itzultzen da.
     * </p>
     */
    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
        irekiSaltzaileMenuPrintzipala();
    }

    /**
     * Bezero berria sortzen du.
     * <p>
     * Eremuak balidatzen ditu eta datu-basean bezero berria txertatzen du.
     * </p>
     */
    @FXML
    public void sortu() {
        if (ipiniIzenAbizenak.getText().isEmpty() || ipiniErabiltzailea.getText().isEmpty()
                || ipiniPasahitza.getText().isEmpty()) {
            irekiAlerta("Errorea", "Datuak bete behar dira",
                    "Izena, erabiltzailea eta pasahitza derrigorrezkoak dira.");
            return;
        }

        String[] izenaAbizena = ipiniIzenAbizenak.getText().split(" ", 2);
        String izena = izenaAbizena[0];
        String abizena = izenaAbizena.length > 1 ? izenaAbizena[1] : "";

        txertatuBezeroa(izena, abizena, ipiniNan.getText(), ipiniEmaila.getText(), ipiniHelbidea.getText(),
                ipiniErabiltzailea.getText(), ipiniPasahitza.getText(), ipiniTelefonoa.getText());

        irekiAlerta("Arrakasta", "Bezeroa sortuta", "Bezeroa ondo sortu da.");
        itxiOraingoLeihoa();
        irekiSaltzaileMenuPrintzipala();
    }

    /**
     * Bezero baten datuak eguneratzen ditu.
     */
    @FXML
    public void aldatu() {
        if (ipiniId.getText().isEmpty()) {
            irekiAlerta("Errorea", "ID bat hautatu behar duzu", "Lehenengo hautatu bezero bat.");
            return;
        }

        String[] izenaAbizena = ipiniIzenAbizenak.getText().split(" ", 2);
        String izena = izenaAbizena[0];
        String abizena = izenaAbizena.length > 1 ? izenaAbizena[1] : "";

        int id = Integer.parseInt(ipiniId.getText());

        eguneratuBezeroa(id, izena, abizena, ipiniNan.getText(), ipiniEmaila.getText(), ipiniHelbidea.getText(),
                ipiniErabiltzailea.getText(), ipiniPasahitza.getText(), ipiniTelefonoa.getText());

        irekiAlerta("Arrakasta", "Bezeroa eguneratuta", "Bezeroa ondo eguneratu da.");
        itxiOraingoLeihoa();
        irekiSaltzaileMenuPrintzipala();
    }

    /**
     * Bezero bat ezabatzen du (baieztapenaren ondoren).
     */
    @FXML
    public void ezabatu() {
        if (ipiniId.getText().isEmpty()) {
            irekiAlerta("Errorea", "ID bat hautatu behar duzu", "Lehenengo hautatu bezero bat.");
            return;
        }
        ezabaketaBaieztatu();
        itxiOraingoLeihoa();
        irekiSaltzaileMenuPrintzipala();
    }

    /**
     * Formularioaren eremu guztiak garbitzen ditu.
     */
    private void garbituEremuak() {
        ipiniId.clear();
        ipiniIzenAbizenak.clear();
        ipiniNan.clear();
        ipiniEmaila.clear();
        ipiniTelefonoa.clear();
        ipiniHelbidea.clear();
        ipiniErabiltzailea.clear();
        ipiniPasahitza.clear();
    }

    /**
     * Ezabaketa baieztatzeko alerta erakusten du.
     */
    private void ezabaketaBaieztatu() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Baieztapena");
        alert.setHeaderText("Bezeroa ezabatu");
        alert.setContentText("Ziur zaude bezero hau ezabatu nahi duzula?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            int id = Integer.parseInt(ipiniId.getText());
            ezabatuBezeroa(id);
            irekiAlerta("Arrakasta", "Bezeroa ezabatuta", "Bezeroa ondo ezabatu da.");
        }
    }
}