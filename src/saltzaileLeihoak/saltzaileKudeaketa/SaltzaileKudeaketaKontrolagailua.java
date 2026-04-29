package saltzaileLeihoak.saltzaileKudeaketa;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import kontrolagailuGlobala.HandlerGlobala;

/**
 * Saltzaileen kudeaketa leihoaren kontrolagailua.
 * <p>
 * Klase honek saltzaileak gehitu, aldatu eta ezabatzeko formularioa kudeatzen du.
 * Bi modu ditu: sorkuntza modua (saltzaile berria sortzeko) eta edizio modua
 * (existitzen den saltzaile bat aldatu edo ezabatzeko).
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class SaltzaileKudeaketaKontrolagailua extends HandlerGlobala {

    // Formularioaren eremuak
    @FXML private TextField ipiniId;
    @FXML private TextField ipiniIzenAbizenak;
    @FXML private TextField ipiniErabiltzailea;
    @FXML private TextField ipiniPasahitza;
    @FXML private TextField ipiniEmaila;
    @FXML private TextField ipiniTelefonoa;
    @FXML private TextField ipiniNagusiIzena;
    @FXML private TextField ipiniKontratazioData;
    @FXML private TextField ipiniSoldata;

    // Botoiak
    @FXML private Button sortuButton;
    @FXML private Button aldatuButton;
    @FXML private Button ezabatuButton;

    /** Editatu beharreko saltzailearen datuak */
    private LangileSaltzaileBean saltzaileInfo;
    
    /** Editatzeko moduan dagoen adierazten du */
    private boolean editazekoModoa = false;

    /**
     * Eraikitzaile lehenetsia.
     */
    public SaltzaileKudeaketaKontrolagailua() {
    }

    /**
     * Saltzailearen datuak jasotzen ditu eta interfazea konfiguratzen du moduaren arabera.
     *
     * @param data editatzeko saltzailearen datuak (null bada, sorkuntza modua)
     * @param editMode {@code true} edizio moduan, {@code false} sorkuntza moduan
     */
    public void setSaltzaileData(LangileSaltzaileBean data, boolean editMode) {
        this.saltzaileInfo = data;
        this.editazekoModoa = editMode;

        if (editMode && data != null) {
            kargatuDatuakFormularioan();
            sortuButton.setVisible(false);
            sortuButton.setManaged(false);
            aldatuButton.setVisible(true);
            ezabatuButton.setVisible(true);
        } else {
            sortuButton.setVisible(true);
            sortuButton.setManaged(true);
            aldatuButton.setVisible(false);
            aldatuButton.setManaged(false);
            ezabatuButton.setVisible(false);
            ezabatuButton.setManaged(false);
            garbituEremuak();
        }
    }

    /**
     * Saltzailearen datuak formularioaren eremuetan kargatzen ditu.
     */
    private void kargatuDatuakFormularioan() {
        if (saltzaileInfo != null) {
            ipiniId.setText(String.valueOf(saltzaileInfo.getId()));
            ipiniIzenAbizenak.setText(saltzaileInfo.getLangileIzenaAbizena());
            ipiniErabiltzailea.setText(saltzaileInfo.getErabiltzailea());
            ipiniPasahitza.setText(saltzaileInfo.getPasahitza());
            ipiniEmaila.setText(saltzaileInfo.getEmaila());
            ipiniTelefonoa.setText(saltzaileInfo.getTelefonoa());
            ipiniNagusiIzena.setText(saltzaileInfo.getNagusiIzenaAbizena());

            if (saltzaileInfo.getKontratazio_data() != null) {
                ipiniKontratazioData.setText(saltzaileInfo.getKontratazio_data().toString());
            }
            ipiniSoldata.setText(String.valueOf(saltzaileInfo.getSoldata()));
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
     * Saltzaile berria sortzen du.
     * <p>
     * Eremuak balidatzen ditu eta datu-basean saltzaile berria txertatzen du.
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

        int soldata = 0;
        try {
            soldata = Integer.parseInt(ipiniSoldata.getText());
        } catch (NumberFormatException e) {
            irekiAlerta("Errorea", "Soldata baliozkoa izan behar da", "Mesedez, sartu soldata zenbaki bat.");
            return;
        }

        txertatuSaltzailea(izena, abizena, ipiniErabiltzailea.getText(), ipiniPasahitza.getText(),
                ipiniEmaila.getText(), ipiniTelefonoa.getText(), soldata);

        irekiAlerta("Arrakasta", "Saltzailea sortuta", "Saltzailea ondo sortu da.");
        itxiOraingoLeihoa();
        irekiSaltzaileMenuPrintzipala();
    }

    /**
     * Saltzaile baten datuak eguneratzen ditu.
     */
    @FXML
    public void aldatu() {
        if (ipiniId.getText().isEmpty()) {
            irekiAlerta("Errorea", "ID bat hautatu behar duzu", "Lehenengo hautatu saltzaile bat.");
            return;
        }

        String[] izenaAbizena = ipiniIzenAbizenak.getText().split(" ", 2);
        String izena = izenaAbizena[0];
        String abizena = izenaAbizena.length > 1 ? izenaAbizena[1] : "";

        int id = Integer.parseInt(ipiniId.getText());
        int soldata = Integer.parseInt(ipiniSoldata.getText());

        eguneratuSaltzailea(id, izena, abizena, ipiniErabiltzailea.getText(), ipiniPasahitza.getText(),
                ipiniEmaila.getText(), ipiniTelefonoa.getText(), soldata);

        irekiAlerta("Arrakasta", "Saltzailea eguneratuta", "Saltzailea ondo eguneratu da.");
        itxiOraingoLeihoa();
        irekiSaltzaileMenuPrintzipala();
    }

    /**
     * Saltzaile bat ezabatzen du (baieztapenaren ondoren).
     */
    @FXML
    public void ezabatu() {
        if (ipiniId.getText().isEmpty()) {
            irekiAlerta("Errorea", "ID bat hautatu behar duzu", "Lehenengo hautatu saltzaile bat.");
            return;
        }
        ezabaketaBaieztatu();
    }

    /**
     * Formularioaren eremu guztiak garbitzen ditu.
     */
    private void garbituEremuak() {
        ipiniId.clear();
        ipiniIzenAbizenak.clear();
        ipiniErabiltzailea.clear();
        ipiniPasahitza.clear();
        ipiniEmaila.clear();
        ipiniTelefonoa.clear();
        ipiniNagusiIzena.clear();
        ipiniKontratazioData.clear();
        ipiniSoldata.clear();
    }
    
    /**
     * Ezabaketa baieztatzeko alerta erakusten du.
     */
    private void ezabaketaBaieztatu() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Baieztapena");
        alert.setHeaderText("Saltzailea ezabatu");
        alert.setContentText("Ziur zaude saltzaile hau ezabatu nahi duzula?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            int id = Integer.parseInt(ipiniId.getText());
            ezabatuSaltzailea(id);

            irekiAlerta("Arrakasta", "Saltzailea ezabatuta", "Saltzailea ondo ezabatu da.");
            itxiOraingoLeihoa();
            irekiSaltzaileMenuPrintzipala();
        }
    }
}