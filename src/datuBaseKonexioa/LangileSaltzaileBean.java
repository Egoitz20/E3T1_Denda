package datuBaseKonexioa;

import java.sql.Date;

/**
 * Langile/Saltzailearen datuak gordetzeko Bean klasea.
 * <p>
 * Klase honek saltzaile baten informazio guztia biltzen du: identifikazioa,
 * kontaktu datuak, kontratazio informazioa, nagusiaren erreferentziak,
 * soldata eta saioa hasteko kredentzialak.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class LangileSaltzaileBean {

    /** Saltzailearen identifikatzaile bakarra */
    private int id;
    
    /** Saltzailearen izen eta abizenak konbinatuta */
    private String langileIzenaAbizena;
    
    /** Saltzailearen helbide elektronikoa */
    private String emaila;
    
    /** Saltzailearen telefono zenbakia */
    private String telefonoa;
    
    /** Saltzailearen kontratazio data */
    private Date kontratazio_data;
    
    /** Saltzailearen nagusiaren identifikatzailea */
    private int id_nagusi;
    
    /** Saltzailearen nagusiaren izen eta abizenak */
    private String nagusiIzenaAbizena;
    
    /** Saltzailearen soldata */
    private int soldata;
    
    /** Saltzailearen erabiltzaile izena (saioa hasteko) */
    private String erabiltzailea;
    
    /** Saltzailearen pasahitza (saioa hasteko) */
    private String pasahitza;

    /**
     * Saltzailearen IDa lortzen du.
     *
     * @return saltzailearen IDa
     */
    public int getId() {
        return id;
    }

    /**
     * Saltzailearen IDa ezartzen du.
     *
     * @param id saltzailearen ID berria
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Saltzailearen izen eta abizenak lortzen du.
     *
     * @return saltzailearen izen eta abizenak
     */
    public String getLangileIzenaAbizena() {
        return langileIzenaAbizena;
    }

    /**
     * Saltzailearen izen eta abizenak ezartzen du.
     *
     * @param langileIzenaAbizena izen eta abizen berriak
     */
    public void setLangileIzenaAbizena(String langileIzenaAbizena) {
        this.langileIzenaAbizena = langileIzenaAbizena;
    }

    /**
     * Saltzailearen emaila lortzen du.
     *
     * @return saltzailearen emaila
     */
    public String getEmaila() {
        return emaila;
    }

    /**
     * Saltzailearen emaila ezartzen du.
     *
     * @param emaila saltzailearen email berria
     */
    public void setEmaila(String emaila) {
        this.emaila = emaila;
    }

    /**
     * Saltzailearen telefonoa lortzen du.
     *
     * @return saltzailearen telefonoa
     */
    public String getTelefonoa() {
        return telefonoa;
    }

    /**
     * Saltzailearen telefonoa ezartzen du.
     *
     * @param telefonoa saltzailearen telefono berria
     */
    public void setTelefonoa(String telefonoa) {
        this.telefonoa = telefonoa;
    }

    /**
     * Saltzailearen kontratazio data lortzen du.
     *
     * @return saltzailearen kontratazio data
     */
    public Date getKontratazio_data() {
        return kontratazio_data;
    }

    /**
     * Saltzailearen kontratazio data ezartzen du.
     *
     * @param kontratazio_data kontratazio data berria
     */
    public void setKontratazio_data(Date kontratazio_data) {
        this.kontratazio_data = kontratazio_data;
    }

    /**
     * Saltzailearen nagusiaren IDa lortzen du.
     *
     * @return nagusiaren IDa
     */
    public int getId_nagusi() {
        return id_nagusi;
    }

    /**
     * Saltzailearen nagusiaren IDa ezartzen du.
     *
     * @param id_nagusi nagusiaren ID berria
     */
    public void setId_nagusi(int id_nagusi) {
        this.id_nagusi = id_nagusi;
    }

    /**
     * Saltzailearen nagusiaren izen eta abizenak lortzen du.
     *
     * @return nagusiaren izen eta abizenak
     */
    public String getNagusiIzenaAbizena() {
        return nagusiIzenaAbizena;
    }

    /**
     * Saltzailearen nagusiaren izen eta abizenak ezartzen du.
     *
     * @param nagusiIzenaAbizena nagusiaren izen eta abizen berriak
     */
    public void setNagusiIzenaAbizena(String nagusiIzenaAbizena) {
        this.nagusiIzenaAbizena = nagusiIzenaAbizena;
    }

    /**
     * Saltzailearen soldata lortzen du.
     *
     * @return saltzailearen soldata
     */
    public int getSoldata() {
        return soldata;
    }

    /**
     * Saltzailearen soldata ezartzen du.
     *
     * @param soldata saltzailearen soldata berria
     */
    public void setSoldata(int soldata) {
        this.soldata = soldata;
    }

    /**
     * Saltzailearen erabiltzaile izena lortzen du.
     *
     * @return saltzailearen erabiltzaile izena
     */
    public String getErabiltzailea() {
        return erabiltzailea;
    }

    /**
     * Saltzailearen erabiltzaile izena ezartzen du.
     *
     * @param erabiltzailea erabiltzaile izen berria
     */
    public void setErabiltzailea(String erabiltzailea) {
        this.erabiltzailea = erabiltzailea;
    }

    /**
     * Saltzailearen pasahitza lortzen du.
     *
     * @return saltzailearen pasahitza
     */
    public String getPasahitza() {
        return pasahitza;
    }

    /**
     * Saltzailearen pasahitza ezartzen du.
     *
     * @param pasahitza saltzailearen pasahitz berria
     */
    public void setPasahitza(String pasahitza) {
        this.pasahitza = pasahitza;
    }
}