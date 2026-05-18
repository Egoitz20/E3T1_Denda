package saltzaileLeihoak.saltzaileMenuPrintzipala;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;
import saltzaileLeihoak.abisuak.Abisuak;
import saltzaileLeihoak.bezeroKudeaketa.BezeroKudeaketa;
import saltzaileLeihoak.bezeroTaula.BezeroTaula;
import saltzaileLeihoak.bezeroTaulaEskaerak.BezeroTaulaEskaerak;
import saltzaileLeihoak.eguneraketakKontrola.EguneraketakKontrola;
import saltzaileLeihoak.saltzaileKudeaketa.SaltzaileKudeaketa;
import saltzaileLeihoak.saltzaileTaula.SaltzaileTaula;
import saltzaileLeihoak.txertaketakEzabaketakKontrola.TxertaketakEzabaketakKontrola;

/**
 * Saltzaile menu nagusiaren kontrolagailua.
 * <p>
 * Klase honek saltzailearen menu nagusiko aukera guztiak kudeatzen ditu:
 * saltzaileen eta bezeroen kudeaketa, taulak, abisuak, kontrolak eta saioaren
 * iixtea.
 * </p>
 * 
 * @author AIA
 * @version 2.0
 */
public class SaltzaileMenuPrintzipalaKontrolagailua extends HandlerGlobala {

    /** Saltzailearen izena erakusten duen menua */
    @FXML
    private Menu saltzaileMenu;

    /** Erabiltzaile kudeaketa menua */
    @FXML
    private Menu erabiltzaileKudeaketaMenu;

    /** Saioa hasi duen saltzailearen datuak */
    private LangileSaltzaileBean saltzaileData;

    /** Soilik administratzaileak (ID=1) sartu ahal izango duen IDa */
    private static final int ADMIN_ID = 1;

    /**
     * Eraikitzaile lehenetsia.
     */
    public SaltzaileMenuPrintzipalaKontrolagailua() {
    }

    /**
     * FXMLa kargatzean automatikoki exekutatzen den metodoa.
     * <p>
     * Menuaren elementuak konfiguratzen ditu eta baimenak egiaztatzen ditu.
     * </p>
     */
    @FXML
    public void initialize() {
        // Inicialmente los elementos de Erabiltzaile kudeaketa se ocultan
        // Luego, si el usuario es administrador, se muestran
        ezkutatuErabiltzaileKudeaketa();
    }

    /**
     * Saltzailearen datuak jasotzen ditu eta menuaren testua eguneratzen du.
     * <p>
     * Gainera, saltzailearen IDa egiaztatzen du erabiltzaile kudeaketa
     * atala erakusteko edo ezkutatzeko.
     * </p>
     *
     * @param data saioa hasi duen saltzailearen datuak
     */
    public void setSaltzaileData(LangileSaltzaileBean data) {
        this.saltzaileData = data;
        eguneratuMenuTestua();
        egiaztatuBaimenak();
    }

    /**
     * Saltzailearen IDa eta izena menuaren testuan erakusten ditu.
     */
    private void eguneratuMenuTestua() {
        if (saltzaileMenu != null && saltzaileData != null) {
            String menuText = saltzaileData.getId() + " - " + saltzaileData.getLangileIzenaAbizena();
            saltzaileMenu.setText(menuText);
        }
    }

    /**
     * Saltzailearen baimenak egiaztatzen ditu.
     * <p>
     * ID = 1 duten saltzaileek soilik ikusiko dute "Erabiltzaile kudeaketa" atala.
     * </p>
     */
    private void egiaztatuBaimenak() {
        if (saltzaileData != null) {
            if (saltzaileData.getId() == ADMIN_ID) {
                erakutsiErabiltzaileKudeaketa();
            } else {
                ezkutatuErabiltzaileKudeaketa();
            }
        }
    }

    /**
     * Erabiltzaile kudeaketa atala erakusten du (administratzaileentzat).
     */
    private void erakutsiErabiltzaileKudeaketa() {
        if (erabiltzaileKudeaketaMenu != null) {
            erabiltzaileKudeaketaMenu.setVisible(true);
        }
    }

    /**
     * Erabiltzaile kudeaketa atala ezkutatzen du (administratzaileak ez direnentzat).
     */
    private void ezkutatuErabiltzaileKudeaketa() {
        if (erabiltzaileKudeaketaMenu != null) {
            erabiltzaileKudeaketaMenu.setVisible(false);
        }
    }

    /**
     * Saltzaileen taula irekitzen du.
     * <p>
     * Atal hau ere babestuta dago: soilik administratzaileak (ID=1) sar daitezke.
     * </p>
     */
    @FXML
    public void irekiSaltzaileTaula() {
        // Egiaztatu baimenak berriro (segurtasun kopiarako)
        if (saltzaileData == null || saltzaileData.getId() != ADMIN_ID) {
            irekiAlerta("Baimenik ez", "Sarbide ukatu", "Ez duzu baimenik atal honetara sartzeko.");
            return;
        }
        
        try {
            SaltzaileTaula saltzaileTaula = new SaltzaileTaula();
            Stage newStage = new Stage();
            saltzaileTaula.setSaltzaileData(saltzaileData);
            saltzaileTaula.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea saltzaileen taula irekitzean: " + e.getMessage());
        }
    }

    /**
     * Saltzaileen kudeaketa leihoa irekitzen du (gehitu/ezabatu/aldatu).
     * <p>
     * Atal hau ere babestuta dago: soilik administratzaileak (ID=1) sar daitezke.
     * </p>
     */
    @FXML
    public void irekiSaltzaileKudeaketa() {
        // Egiaztatu baimenak berriro (segurtasun kopiarako)
        if (saltzaileData == null || saltzaileData.getId() != ADMIN_ID) {
            irekiAlerta("Baimenik ez", "Sarbide ukatu", "Ez duzu baimenik atal honetara sartzeko.");
            return;
        }
        
        try {
            SaltzaileKudeaketa saltzaileKudeaketa = new SaltzaileKudeaketa();
            Stage newStage = new Stage();
            saltzaileKudeaketa.start(newStage);
            saltzaileKudeaketa.setSaltzaileData(saltzaileData);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea saltzaileen kudeaketa irekitzean: " + e.getMessage());
        }
    }

    /**
     * Bezeroen taula irekitzen du.
     * <p>
     * Atal hau guztientzat irekita dago (baimenik gabe).
     * </p>
     */
    @FXML
    public void irekiBezeroTaula() {
        try {
            BezeroTaula bezeroTaula = new BezeroTaula();
            Stage newStage = new Stage();
            bezeroTaula.setSaltzaileData(saltzaileData);
            bezeroTaula.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki", 
                    "Errorea bezeroen taula irekitzean: " + e.getMessage());
        }
    }

    /**
     * Bezeroen kudeaketa leihoa irekitzen du (gehitu/ezabatu/aldatu).
     * <p>
     * Atal hau guztientzat irekita dago (baimenik gabe).
     * </p>
     */
    @FXML
    public void irekiBezeroKudeaketa() {
        try {
            BezeroKudeaketa bezeroKudeaketa = new BezeroKudeaketa();
            Stage newStage = new Stage();
            bezeroKudeaketa.start(newStage);
            bezeroKudeaketa.setSaltzaileData(saltzaileData);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki", 
                    "Errorea bezeroen kudeaketa irekitzean: " + e.getMessage());
        }
    }

    /**
     * Bezeroen eskaeren taula irekitzen du.
     * <p>
     * Atal hau guztientzat irekita dago (baimenik gabe).
     * </p>
     */
    @FXML
    public void irekiBezeroTaulaEskaerak() {
        try {
            BezeroTaulaEskaerak bezeroTaulaEskaerak = new BezeroTaulaEskaerak();
            Stage newStage = new Stage();
            bezeroTaulaEskaerak.start(newStage);
            bezeroTaulaEskaerak.setSaltzaileData(saltzaileData);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea bezeroen eskaera taula irekitzean: " + e.getMessage());
        }
    }

    /**
     * Abisuen leihoa irekitzen du.
     */
    @FXML
    public void irekiAbisuak() {
        try {
            Abisuak abisuak = new Abisuak();
            Stage newStage = new Stage();
            abisuak.start(newStage);
            abisuak.setSaltzaileData(saltzaileData);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki", 
                    "Errorea abisuak irekitzean: " + e.getMessage());
        }
    }

    /**
     * Eguneraketen kontrola leihoa irekitzen du.
     */
    @FXML
    public void irekiEguneraketak() {
        try {
            EguneraketakKontrola eguneraketaKontrola = new EguneraketakKontrola();
            Stage newStage = new Stage();
            eguneraketaKontrola.start(newStage);
            eguneraketaKontrola.setSaltzaileData(saltzaileData);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea eguneraketak kontrola irekitzean: " + e.getMessage());
        }
    }

    /**
     * Txertaketen eta ezabaketen kontrola leihoa irekitzen du.
     */
    @FXML
    public void irekiTxerteketakEzabaketak() {
        try {
            TxertaketakEzabaketakKontrola txertatuEzabatuKontrola = new TxertaketakEzabaketakKontrola();
            Stage newStage = new Stage();
            txertatuEzabatuKontrola.start(newStage);
            txertatuEzabatuKontrola.setSaltzaileData(saltzaileData);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea txertaketak eta ezabaketak kontrola irekitzean: " + e.getMessage());
        }
    }

    /**
     * Saioa ixten du eta login leihoa irekitzen du.
     */
    @FXML
    public void saioaIxten() {
        itxiOraingoLeihoa();
        setSaltzaileLogeatuta(null);
        irekiLogina();
    }
}