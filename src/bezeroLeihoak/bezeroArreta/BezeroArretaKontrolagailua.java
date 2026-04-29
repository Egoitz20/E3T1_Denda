package bezeroLeihoak.bezeroArreta;

import datuBaseKonexioa.BezeroBean;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import kontrolagailuGlobala.AbisuakGlobala;
import kontrolagailuGlobala.HandlerGlobala;

/**
 * Bezeroarentzako arretaren kontrolagailua.
 * <p>
 * Bezeroaren abisuak kudeatzen ditu: kontzeptua eta deskribapena
 * bidali aurretik balidatzen ditu eta abisuak gordetzen ditu.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 * @see HandlerGlobala
 * @see AbisuakGlobala
 */
public class BezeroArretaKontrolagailua extends HandlerGlobala {

    /**
     * Abisuaren kontzeptua idazteko testu eremua.
     */
    @FXML
    private TextArea kontzeptua;

    /**
     * Abisuaren deskribapena idazteko testu eremua.
     */
    @FXML
    private TextArea deskribapena;

    /**
     * Bezeroaren datuak.
     */
    private BezeroBean bezeroData;

    /**
     * Eraikitzaile hutsa.
     */
    public BezeroArretaKontrolagailua() {
    }

    /**
     * Bezeroaren datuak ezartzen ditu.
     *
     * @param data Bezeroaren datuak
     */
    public void setBezeroData(BezeroBean data) {
        this.bezeroData = data;
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
     * Abisua saltzaileei bidaltzen die.
     * <p>
     * Eremuak hutsik dauden egiaztatzen du, abisua gordetzen du
     * eta konfirmazio mezua erakusten du.
     * </p>
     */
    @FXML
    public void bidaliSaltzaileeiArazoa() {

        // Bezero izena osoa lortu
        String bezeroIzenaOsoa = bezeroData.getIzena() + " " + bezeroData.getAbizena();

        // Eremu testua lortu
        String kontzeptuaText = kontzeptua.getText();
        String deskribapenaText = deskribapena.getText();

        // Eremuak ez dagoela hutsik komprobatu
        if (kontzeptuaText.isEmpty() || deskribapenaText.isEmpty()) {
            irekiAlerta("Errorea", "Kontzeptua eta deskribapena bete behar dira",
                       "Mesedez, bete bi eremuak abisua bidali aurretik.");
            return;
        }

        // Almazena globala gorde abisua
        AbisuakGlobala.getInstantzia().gehituAbisua(bezeroIzenaOsoa, kontzeptuaText, deskribapenaText);

        // Baieztapen abisua bistartau
        irekiAlerta("Arrakasta", "Abisua bidalita",
                   "Zure abisua ondo bidali da. Eskerrik asko!");

        // Eremuak garbitu
        kontzeptua.clear();
        deskribapena.clear();

        // Menu printzipalara joan
        itzuli();
    }
}