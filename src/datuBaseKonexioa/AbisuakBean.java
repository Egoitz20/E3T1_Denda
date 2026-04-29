package datuBaseKonexioa;

import java.time.LocalDateTime;

/**
 * Abisuaren datuak gordetzeko Bean klasea.
 * <p>
 * Klase honek bezero baten abisu baten informazio guztia biltzen du:
 * bezeroaren izena, kontzeptua, deskribapena eta abisuaren data.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class AbisuakBean {
    
    /** Abisua bidali duen bezeroaren izena */
    private String bezeroIzena;
    
    /** Abisuaren kontzeptua */
    private String kontzeptua;
    
    /** Abisuaren deskribapen zehatza */
    private String deskribapena;
    
    /** Abisuaren data eta ordua */
    private LocalDateTime data;

    /**
     * Eraikitzailea.
     * <p>
     * Abisu berria sortzen du data automatikoki uneko datarekin.
     * </p>
     *
     * @param bezeroIzena abisua bidali duen bezeroaren izena
     * @param kontzeptua abisuaren kontzeptua
     * @param deskribapena abisuaren deskribapen zehatza
     */
    public AbisuakBean(String bezeroIzena, String kontzeptua, String deskribapena) {
        this.bezeroIzena = bezeroIzena;
        this.kontzeptua = kontzeptua;
        this.deskribapena = deskribapena;
        this.data = LocalDateTime.now();
    }

    /**
     * Abisua bidali duen bezeroaren izena lortzen du.
     *
     * @return bezeroaren izena
     */
    public String getBezeroIzena() {
        return bezeroIzena;
    }

    /**
     * Abisua bidali duen bezeroaren izena ezartzen du.
     *
     * @param bezeroIzena bezeroaren izen berria
     */
    public void setBezeroIzena(String bezeroIzena) {
        this.bezeroIzena = bezeroIzena;
    }

    /**
     * Abisuaren kontzeptua lortzen du.
     *
     * @return abisuaren kontzeptua
     */
    public String getKontzeptua() {
        return kontzeptua;
    }

    /**
     * Abisuaren kontzeptua ezartzen du.
     *
     * @param kontzeptua abisuaren kontzeptu berria
     */
    public void setKontzeptua(String kontzeptua) {
        this.kontzeptua = kontzeptua;
    }

    /**
     * Abisuaren deskribapena lortzen du.
     *
     * @return abisuaren deskribapena
     */
    public String getDeskribapena() {
        return deskribapena;
    }

    /**
     * Abisuaren deskribapena ezartzen du.
     *
     * @param deskribapena abisuaren deskribapen berria
     */
    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    /**
     * Abisuaren data eta ordua lortzen du.
     *
     * @return abisuaren data eta ordua
     */
    public LocalDateTime getData() {
        return data;
    }

    /**
     * Abisuaren data eta ordua ezartzen du.
     *
     * @param data data eta ordu berria
     */
    public void setData(LocalDateTime data) {
        this.data = data;
    }
}