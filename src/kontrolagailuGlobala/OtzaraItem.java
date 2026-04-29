package kontrolagailuGlobala;

import datuBaseKonexioa.ProduktuBean;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;

/**
 * Otzara Item klasea.
 * <p>
 * Klase honek otzaran dauden produktu bakoitzaren informazioa kudeatzen du,
 * kantitatea eta guztirako balioa barne. ProduktuBean-etik heredatzen du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class OtzaraItem extends ProduktuBean {
    
    /** Produktuaren kantitatea (behatzailea) */
    private final IntegerProperty kantitatea = new SimpleIntegerProperty(0);
    
    /** Produktuaren guztirako balioa (behatzailea) */
    private final DoubleProperty guztira = new SimpleDoubleProperty(0);
    
    /**
     * Eraikitzailea.
     * <p>
     * OtzaraItem berria sortzen du produktu eta kantitate jakin batekin.
     * </p>
     *
     * @param produktua otzaran gehitu nahi den produktua
     * @param kantitatea produktuaren kantitatea
     */
    public OtzaraItem(ProduktuBean produktua, int kantitatea) {
        setId(produktua.getId());
        setIzena(produktua.getIzena());
        setDeskribapena(produktua.getDeskribapena());
        setSalneurria(produktua.getSalneurria());
        setKantitatea(kantitatea);
        kalkulatuGuztira();
    }
    
    /**
     * Kantitatea lortzen du.
     *
     * @return produktuaren kantitatea
     */
    public int getKantitatea() {
        return kantitatea.get();
    }
    
    /**
     * Kantitatea ezartzen du.
     *
     * @param value kantitate berria
     */
    public void setKantitatea(int value) {
        kantitatea.set(value);
        kalkulatuGuztira();
    }
    
    /**
     * Kantitatearen property-a lortzen du (behatzailea).
     *
     * @return kantitatearen property-a
     */
    public IntegerProperty kantitateaProperty() {
        return kantitatea;
    }
    
    /**
     * Produktuaren guztirako balioa lortzen du (salneurria * kantitatea).
     *
     * @return guztirako balioa
     */
    public double getGuztira() {
        return guztira.get();
    }
    
    /**
     * Guztirako balioaren property-a lortzen du (behatzailea).
     *
     * @return guztirako balioaren property-a
     */
    public DoubleProperty guztiraProperty() {
        return guztira;
    }
    
    /**
     * Guztirako balioa kalkulatzen du (salneurria * kantitatea).
     */
    private void kalkulatuGuztira() {
        guztira.set(getSalneurria() * getKantitatea());
    }
    
    /**
     * Kantitatea 1 gehitzen du.
     */
    public void gehituKantitatea() {
        setKantitatea(getKantitatea() + 1);
    }
    
    /**
     * Kantitatea 1 kentzen du (0 baino handiagoa bada).
     */
    public void kenduKantitatea() {
        if (getKantitatea() > 0) {
            setKantitatea(getKantitatea() - 1);
        }
    }
}