package datuBaseKonexioa;

/**
 * Bezeroaren datuak gordetzeko Bean klasea.
 * <p>
 * Klase honek bezero baten informazio guztia biltzen du: identifikazioa,
 * kontaktu datuak eta saioa hasteko kredentzialak.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class BezeroBean {

    /** Bezeroaren identifikatzaile bakarra */
    private int id;
    
    /** Bezeroaren izena */
    private String izena;
    
    /** Bezeroaren abizena */
    private String abizena;
    
    /** Bezeroaren helbidea */
    private String helbidea;
    
    /** Bezeroaren helbide elektronikoa */
    private String emaila;
    
    /** Bezeroaren NANa */
    private String nan;
    
    /** Bezeroaren telefono zenbakia */
    private String zenbakia;
    
    /** Bezeroaren erabiltzaile izena (saioa hasteko) */
    private String erabiltzaile;
    
    /** Bezeroaren pasahitza (saioa hasteko) */
    private String pasahitza;

    /**
     * Bezeroaren IDa lortzen du.
     *
     * @return bezeroaren IDa
     */
    public int getId() {
        return id;
    }

    /**
     * Bezeroaren IDa ezartzen du.
     *
     * @param id bezeroaren ID berria
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Bezeroaren izena lortzen du.
     *
     * @return bezeroaren izena
     */
    public String getIzena() {
        return izena;
    }

    /**
     * Bezeroaren izena ezartzen du.
     *
     * @param izena bezeroaren izen berria
     */
    public void setIzena(String izena) {
        this.izena = izena;
    }

    /**
     * Bezeroaren abizena lortzen du.
     *
     * @return bezeroaren abizena
     */
    public String getAbizena() {
        return abizena;
    }

    /**
     * Bezeroaren abizena ezartzen du.
     *
     * @param abizena bezeroaren abizen berria
     */
    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }

    /**
     * Bezeroaren helbidea lortzen du.
     *
     * @return bezeroaren helbidea
     */
    public String getHelbidea() {
        return helbidea;
    }

    /**
     * Bezeroaren helbidea ezartzen du.
     *
     * @param helbidea bezeroaren helbide berria
     */
    public void setHelbidea(String helbidea) {
        this.helbidea = helbidea;
    }

    /**
     * Bezeroaren emaila lortzen du.
     *
     * @return bezeroaren emaila
     */
    public String getEmaila() {
        return emaila;
    }

    /**
     * Bezeroaren emaila ezartzen du.
     *
     * @param emaila bezeroaren email berria
     */
    public void setEmaila(String emaila) {
        this.emaila = emaila;
    }

    /**
     * Bezeroaren erabiltzaile izena lortzen du.
     *
     * @return bezeroaren erabiltzaile izena
     */
    public String getErabiltzaile() {
        return erabiltzaile;
    }

    /**
     * Bezeroaren erabiltzaile izena ezartzen du.
     *
     * @param erabiltzaile bezeroaren erabiltzaile izen berria
     */
    public void setErabiltzaile(String erabiltzaile) {
        this.erabiltzaile = erabiltzaile;
    }

    /**
     * Bezeroaren pasahitza lortzen du.
     *
     * @return bezeroaren pasahitza
     */
    public String getPasahitza() {
        return pasahitza;
    }

    /**
     * Bezeroaren pasahitza ezartzen du.
     *
     * @param pasahitza bezeroaren pasahitz berria
     */
    public void setPasahitza(String pasahitza) {
        this.pasahitza = pasahitza;
    }

    /**
     * Bezeroaren NANa lortzen du.
     *
     * @return bezeroaren NANa
     */
    public String getNan() {
        return nan;
    }

    /**
     * Bezeroaren NANa ezartzen du.
     *
     * @param nan bezeroaren NAN berria
     */
    public void setNan(String nan) {
        this.nan = nan;
    }

    /**
     * Bezeroaren telefono zenbakia lortzen du.
     *
     * @return bezeroaren telefono zenbakia
     */
    public String getZenbakia() {
        return zenbakia;
    }

    /**
     * Bezeroaren telefono zenbakia ezartzen du.
     *
     * @param zenbakia bezeroaren telefono zenbaki berria
     */
    public void setZenbakia(String zenbakia) {
        this.zenbakia = zenbakia;
    }
}