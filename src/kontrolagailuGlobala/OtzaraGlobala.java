package kontrolagailuGlobala;

import datuBaseKonexioa.ProduktuBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Otzara Globala Singleton klasea.
 * <p>
 * Klase honek erabiltzailearen erosketa-otzarako produktuak kudeatzen ditu
 * aplikazio mailan. Singleton patroia erabiltzen du otzara bakarra izateko.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class OtzaraGlobala {

    /** Singleton instantzia bakarra */
    private static OtzaraGlobala instantzia;
    
    /** Otzarako produktuak gordetzeko zerrenda behatzailea */
    private final ObservableList<OtzaraItem> produktuak = FXCollections.observableArrayList();

    /**
     * Eraikitzaile pribatua (Singleton patroia).
     */
    private OtzaraGlobala() {
    }

    /**
     * Singleton instantzia lortzen du.
     *
     * @return OtzaraGlobala instantzia bakarra
     */
    public static OtzaraGlobala getInstantzia() {
        if (instantzia == null) {
            instantzia = new OtzaraGlobala();
        }
        return instantzia;
    }

    /**
     * Otzarako produktuak lortzen ditu.
     *
     * @return Produktuen zerrenda
     */
    public ObservableList<OtzaraItem> getProduktuak() {
        return produktuak;
    }

    /**
     * Produktua otzaran gehitzen du.
     * <p>
     * Produktua otzaran dagoeneko badago, kantitatea 1 handitzen du.
     * Bestela, produktu berria sortzen du 1 kantitatearekin.
     * </p>
     *
     * @param produktua gehitu nahi den produktua
     * @param kantitatea gehitu nahi den kantitatea
     */
    public void gehituProduktua(ProduktuBean produktua, int kantitatea) {
        for (OtzaraItem item : produktuak) {
            if (item.getId() == produktua.getId()) {
                item.gehituKantitatea();
                return;
            }
        }
        produktuak.add(new OtzaraItem(produktua, kantitatea));
    }

    /**
     * Produktua otzaratik kentzen du.
     *
     * @param item kendu nahi den produktua
     */
    public void kenduProduktua(OtzaraItem item) {
        produktuak.remove(item);
    }

    /**
     * Otzarako produktuen guztirako balioa kalkulatzen du (BEZ gabe).
     *
     * @return guztirako balioa BEZ gabe
     */
    public double getTotala() {
        return produktuak.stream().mapToDouble(OtzaraItem::getGuztira).sum();
    }

    /**
     * BEZa kalkulatzen du (%21).
     *
     * @return BEZa
     */
    public double getIva() {
        return getTotala() * 0.21;
    }

    /**
     * Otzarako guztirako balioa kalkulatzen du (BEZarekin).
     *
     * @return guztirako balioa BEZarekin
     */
    public double getGuztiraIva() {
        return getTotala() + getIva();
    }

    /**
     * Otzara osoa garbitzen du.
     */
    public void garbitu() {
        produktuak.clear();
    }
}