package kontrolagailuGlobala;

import datuBaseKonexioa.ProduktuBean;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;

public class OtzaraItem extends ProduktuBean {
    
    private final IntegerProperty kantitatea = new SimpleIntegerProperty(0);
    private final DoubleProperty guztira = new SimpleDoubleProperty(0);
    
    public OtzaraItem(ProduktuBean produktua, int kantitatea) {
        setId(produktua.getId());
        setIzena(produktua.getIzena());
        setDeskribapena(produktua.getDeskribapena());
        setSalneurria(produktua.getSalneurria());
        setKantitatea(kantitatea);
        kalkulatuGuztira();
    }
    
    public int getKantitatea() {
        return kantitatea.get();
    }
    
    public void setKantitatea(int value) {
        kantitatea.set(value);
        kalkulatuGuztira();
    }
    
    public IntegerProperty kantitateaProperty() {
        return kantitatea;
    }
    
    public double getGuztira() {
        return guztira.get();
    }
    
    public DoubleProperty guztiraProperty() {
        return guztira;
    }
    
    private void kalkulatuGuztira() {
        guztira.set(getSalneurria() * getKantitatea());
    }
    
    public void gehituKantitatea() {
        setKantitatea(getKantitatea() + 1);
    }
    
    public void kenduKantitatea() {
        if (getKantitatea() > 0) {
            setKantitatea(getKantitatea() - 1);
        }
    }
}