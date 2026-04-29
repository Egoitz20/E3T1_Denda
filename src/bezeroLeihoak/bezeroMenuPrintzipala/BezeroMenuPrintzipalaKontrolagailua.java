package bezeroLeihoak.bezeroMenuPrintzipala;

import datuBaseKonexioa.BezeroBean;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;
import kontrolagailuGlobala.OtzaraGlobala;
import bezeroLeihoak.bezeroArreta.BezeroArreta;
import bezeroLeihoak.cpuTaula.CpuTaula;
import bezeroLeihoak.motherBoardTaula.MotherBoardTaula;
import bezeroLeihoak.otzara.Otzara;
import bezeroLeihoak.ramTaula.RamTaula;
import bezeroLeihoak.saioInformazioa.SaioInformazioa;
import bezeroLeihoak.storageTaula.StorageTaula;
import bezeroLeihoak.videoCardTaula.VideoCardTaula;

/**
 * Bezeroaren menu nagusiaren kontrolagailua.
 * <p>
 * Menu nagusiko ekintzak kudeatzen ditu: dendako atalak irekitzea,
 * bezeroarentzako arreta, saioaren informazioa, otzara eta saioa ixtea.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 * @see HandlerGlobala
 * @see OtzaraGlobala
 */
public class BezeroMenuPrintzipalaKontrolagailua extends HandlerGlobala {

    /**
     * Bezeroaren izena erakusten duen menu elementua.
     */
    @FXML
    private Menu bezeroMenu;

    /**
     * Bezeroaren datuak.
     */
    private BezeroBean bezeroData;

    /**
     * Kontrolagailuaren eraikitzaile hutsa.
     */
    public BezeroMenuPrintzipalaKontrolagailua() {
    }

    /**
     * Bezeroaren datuak ezartzen ditu eta menuko testua eguneratzen du.
     *
     * @param data Bezeroaren datuak
     */
    public void setBezeroData(BezeroBean data) {
        this.bezeroData = data;
        eguneratuBezeroKontua();
    }

    /**
     * Bezeroaren izena menuan eguneratzen du.
     * <p>
     * Bezeroaren izena eta abizena hartu eta menuan erakusten ditu.
     * </p>
     */
    private void eguneratuBezeroKontua() {
        if (bezeroMenu != null && bezeroData != null) {
            String izenaOsoa = bezeroData.getIzena() + " " + bezeroData.getAbizena();
            bezeroMenu.setText("Kaixo, " + izenaOsoa);
        }
    }

    /**
     * Bezeroarentzako arreta atala irekitzen du.
     *
     * @throws Exception Leihoa irekitzean errore bat gertatuz gero
     */
    @FXML
    public void irekiArreta() {
        try {
            BezeroArreta arreta = new BezeroArreta();
            Stage newStage = new Stage();
            arreta.setBezeroData(bezeroData);
            arreta.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea arreta leihoa irekitzean: " + e.getMessage());
        }
    }

    /**
     * Storage produktuen taula irekitzen du.
     *
     * @throws Exception Leihoa irekitzean errore bat gertatuz gero
     */
    @FXML
    public void irekiStorageTaula() {
        try {
            StorageTaula storageTaula = new StorageTaula();
            Stage newStage = new Stage();
            storageTaula.setBezeroData(bezeroData);
            storageTaula.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea storage taula irekitzean: " + e.getMessage());
        }
    }

    /**
     * Mother board produktuen taula irekitzen du.
     *
     * @throws Exception Leihoa irekitzean errore bat gertatuz gero
     */
    @FXML
    public void irekiMotherBoardTaula() {
        try {
            MotherBoardTaula motherBoardTaula = new MotherBoardTaula();
            Stage newStage = new Stage();
            motherBoardTaula.setBezeroData(bezeroData);
            motherBoardTaula.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea mother board taula irekitzean: " + e.getMessage());
        }
    }

    /**
     * Video card produktuen taula irekitzen du.
     *
     * @throws Exception Leihoa irekitzean errore bat gertatuz gero
     */
    @FXML
    public void irekiVideoCardTaula() {
        try {
            VideoCardTaula videoCardTaula = new VideoCardTaula();
            Stage newStage = new Stage();
            videoCardTaula.setBezeroData(bezeroData);
            videoCardTaula.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea video card taula irekitzean: " + e.getMessage());
        }
    }

    /**
     * RAM produktuen taula irekitzen du.
     *
     * @throws Exception Leihoa irekitzean errore bat gertatuz gero
     */
    @FXML
    public void irekiRamTaula() {
        try {
            RamTaula ramTaula = new RamTaula();
            Stage newStage = new Stage();
            ramTaula.setBezeroData(bezeroData);
            ramTaula.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea ram taula irekitzean: " + e.getMessage());
        }
    }

    /**
     * CPU produktuen taula irekitzen du.
     *
     * @throws Exception Leihoa irekitzean errore bat gertatuz gero
     */
    @FXML
    public void irekiCpuTaula() {
        try {
            CpuTaula cpuTaula = new CpuTaula();
            Stage newStage = new Stage();
            cpuTaula.setBezeroData(bezeroData);
            cpuTaula.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea cpu taula irekitzean: " + e.getMessage());
        }
    }

    /**
     * Saioaren informazioa atala irekitzen du.
     *
     * @throws Exception Leihoa irekitzean errore bat gertatuz gero
     */
    @FXML
    public void irekiSaioarenInformazioa() {
        try {
            SaioInformazioa saioInformazioa = new SaioInformazioa();
            Stage newStage = new Stage();
            saioInformazioa.setBezeroData(bezeroData);
            saioInformazioa.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea bezeroen saio informazioa irekitzean: " + e.getMessage());
        }
    }

    /**
     * Otzararen leihoa irekitzen du.
     *
     * @throws Exception Leihoa irekitzean errore bat gertatuz gero
     */
    @FXML
    public void irekiOtzara() {
        try {
            Otzara otzara = new Otzara();
            Stage newStage = new Stage();
            otzara.setBezeroData(bezeroData);
            otzara.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea otzara irekitzean: " + e.getMessage());
        }
    }

    /**
     * Saioa ixten du.
     * <p>
     * Otza garbitu, bezeroa deslogeatu eta login leihoa irekitzen du.
     * </p>
     */
    @FXML
    public void saioaItxi() {
        // Garbitu otzara saioa itxitakoan
        OtzaraGlobala.getInstantzia().garbitu();
        itxiOraingoLeihoa();
        setBezeroLogeatuta(null);
        irekiLogina();
    }
}