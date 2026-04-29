package saltzaileLeihoak.saltzaileMenuPrintzipala;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
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
 * @version 1.0
 */
public class SaltzaileMenuPrintzipalaKontrolagailua extends HandlerGlobala {

    /** Saltzailearen izena erakusten duen menua */
    @FXML
    private Menu saltzaileMenu;

    /** Saioa hasi duen saltzailearen datuak */
    private LangileSaltzaileBean saltzaileData;

    /**
     * Eraikitzaile lehenetsia.
     */
    public SaltzaileMenuPrintzipalaKontrolagailua() {
    }

    /**
     * Saltzailearen datuak jasotzen ditu eta menuaren testua eguneratzen du.
     *
     * @param data saioa hasi duen saltzailearen datuak
     */
    public void setSaltzaileData(LangileSaltzaileBean data) {
        this.saltzaileData = data;
        eguneratuMenuTestua();
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
     * Saltzaileen taula irekitzen du.
     */
    @FXML
    public void irekiSaltzaileTaula() {
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
     * Bezeroen taula irekitzen du.
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
     * Saltzaileen kudeaketa leihoa irekitzen du (gehitu/ezabatu/aldatu).
     */
    @FXML
    public void irekiSaltzaileKudeaketa() {
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
     * Bezeroen eskaeren taula irekitzen du.
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
        setSaltzaileData(null);
        irekiLogina();
    }
}