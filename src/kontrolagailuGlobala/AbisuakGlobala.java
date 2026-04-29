package kontrolagailuGlobala;

import datuBaseKonexioa.AbisuakBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Abisuen Singleton klasea.
 * <p>
 * Klase honek bezeroen abisu guztiak kudeatzen ditu aplikazio mailan.
 * Singleton patroia erabiltzen du abisu-zerrenda bakarra izateko eta
 * aplikazioaren edozein lekutik atzitzeko aukera emateko.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class AbisuakGlobala {

    /** Singleton instantzia bakarra */
    private static AbisuakGlobala instantzia;
    
    /** Abisu guztiak gordetzeko zerrenda behatzailea */
    private final ObservableList<AbisuakBean> abisuak = FXCollections.observableArrayList();

    /**
     * Eraikitzaile pribatua (Singleton patroia).
     * <p>
     * Klase honen instantzia bakarra sortzea baimentzen du.
     * </p>
     */
    private AbisuakGlobala() {
    }

    /**
     * Singleton instantzia lortzen du.
     * <p>
     * Existitzen ez bada, lehenengoz sortzen du.
     * </p>
     *
     * @return AbisuakGlobala instantzia bakarra
     */
    public static AbisuakGlobala getInstantzia() {
        if (instantzia == null) {
            instantzia = new AbisuakGlobala();
        }
        return instantzia;
    }

    /**
     * Abisu zerrenda lortzen du.
     *
     * @return Abisu zerrenda behatzailea
     */
    public ObservableList<AbisuakBean> getAbisuak() {
        return abisuak;
    }

    /**
     * Abisu berria gehitzen du.
     * <p>
     * Abisua zerrendaren hasieran gehitzen da (berriena goian agertzeko).
     * </p>
     *
     * @param bezeroIzena abisua bidali duen bezeroaren izena
     * @param kontzeptua abisuaren kontzeptua
     * @param deskribapena abisuaren deskribapen zehatza
     */
    public void gehituAbisua(String bezeroIzena, String kontzeptua, String deskribapena) {
        AbisuakBean abisua = new AbisuakBean(bezeroIzena, kontzeptua, deskribapena);
        abisuak.add(0, abisua); // Hasieran gehitzen du (berriena goian)
    }

    /**
     * Abisu zerrenda osoa garbitzen du.
     */
    public void garbitu() {
        abisuak.clear();
    }
}