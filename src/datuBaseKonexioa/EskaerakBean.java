package datuBaseKonexioa;

import java.sql.Date;

/**
 * Eskaeren datuak gordetzeko Bean klasea.
 * <p>
 * Klase honek bezero baten eskaera baten informazio guztia biltzen du:
 * produktua, kategoria, kopurua, egoera, data eta eskaera kudeatu duen saltzailea.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class EskaerakBean {

    /** Eskaeraren produktuaren izena */
    private String produktu;
    
    /** Produktuaren kategoria */
    private String kategoria;
    
    /** Eskaeraren kopurua */
    private int kopurua;
    
    /** Eskaeraren data */
    private Date eskaera_data;
    
    /** Eskaeraren egoeraren deskribapena */
    private String deskribapena;
    
    /** Eskaera kudeatu duen saltzailearen izena */
    private String saltzailea;

    /**
     * Eskaeraren produktuaren izena lortzen du.
     *
     * @return produktuaren izena
     */
    public String getProduktu() {
        return produktu;
    }

    /**
     * Eskaeraren produktuaren izena ezartzen du.
     *
     * @param produktu produktuaren izen berria
     */
    public void setProduktu(String produktu) {
        this.produktu = produktu;
    }

    /**
     * Eskaeraren produktuaren kategoria lortzen du.
     *
     * @return produktuaren kategoria
     */
    public String getKategoria() {
        return kategoria;
    }

    /**
     * Eskaeraren produktuaren kategoria ezartzen du.
     *
     * @param kategoria produktuaren kategoria berria
     */
    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    /**
     * Eskaeraren kopurua lortzen du.
     *
     * @return eskaeraren kopurua
     */
    public int getKopurua() {
        return kopurua;
    }

    /**
     * Eskaeraren kopurua ezartzen du.
     *
     * @param kopurua eskaeraren kopuru berria
     */
    public void setKopurua(int kopurua) {
        this.kopurua = kopurua;
    }

    /**
     * Eskaeraren data lortzen du.
     *
     * @return eskaeraren data
     */
    public Date getEskaera_data() {
        return eskaera_data;
    }

    /**
     * Eskaeraren data ezartzen du.
     *
     * @param eskaera_data eskaeraren data berria
     */
    public void setEskaera_data(Date eskaera_data) {
        this.eskaera_data = eskaera_data;
    }

    /**
     * Eskaeraren egoeraren deskribapena lortzen du.
     *
     * @return eskaeraren egoeraren deskribapena
     */
    public String getDeskribapena() {
        return deskribapena;
    }

    /**
     * Eskaeraren egoeraren deskribapena ezartzen du.
     *
     * @param deskribapena deskribapen berria
     */
    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    /**
     * Eskaera kudeatu duen saltzailearen izena lortzen du.
     *
     * @return saltzailearen izena
     */
    public String getSaltzailea() {
        return saltzailea;
    }

    /**
     * Eskaera kudeatu duen saltzailearen izena ezartzen du.
     *
     * @param saltzailea saltzailearen izen berria
     */
    public void setSaltzailea(String saltzailea) {
        this.saltzailea = saltzailea;
    }
}