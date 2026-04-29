package datuBaseKonexioa;

/**
 * Produktuaren datuak gordetzeko Bean klasea.
 * <p>
 * Klase honek produktu baten informazio guztia biltzen du:
 * identifikazioa, izena, deskribapena eta salneurria.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class ProduktuBean {

    /** Produktuaren identifikatzaile bakarra */
    private int id;
    
    /** Produktuaren izena */
    private String izena;
    
    /** Produktuaren deskribapena */
    private String deskribapena;
    
    /** Produktuaren salneurria (BEZ gabe) */
    private double salneurria;

    /**
     * Produktuaren izena lortzen du.
     *
     * @return produktuaren izena
     */
    public String getIzena() {
        return izena;
    }

    /**
     * Produktuaren izena ezartzen du.
     *
     * @param izena produktuaren izen berria
     */
    public void setIzena(String izena) {
        this.izena = izena;
    }

    /**
     * Produktuaren deskribapena lortzen du.
     *
     * @return produktuaren deskribapena
     */
    public String getDeskribapena() {
        return deskribapena;
    }

    /**
     * Produktuaren deskribapena ezartzen du.
     *
     * @param deskribapena produktuaren deskribapen berria
     */
    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    /**
     * Produktuaren salneurria lortzen du.
     *
     * @return produktuaren salneurria
     */
    public double getSalneurria() {
        return salneurria;
    }

    /**
     * Produktuaren salneurria ezartzen du.
     *
     * @param salneurria produktuaren salneurri berria
     */
    public void setSalneurria(double salneurria) {
        this.salneurria = salneurria;
    }

    /**
     * Produktuaren IDa lortzen du.
     *
     * @return produktuaren IDa
     */
    public int getId() {
        return id;
    }

    /**
     * Produktuaren IDa ezartzen du.
     *
     * @param id produktuaren ID berria
     */
    public void setId(int id) {
        this.id = id;
    }
}