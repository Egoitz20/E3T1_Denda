package datuBaseKonexioa;

/**
 * Eguneraketen kontrola gordetzeko Bean klasea.
 * <p>
 * Klase honek datu-basean egindako eguneraketa guztien erregistroa
 * biltzen du, kontrol mezu gisa.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class EguneraketakBean {

    /** Eguneraketaren kontrol mezu deskriptiboa */
    private String kontrola;

    /**
     * Eraikitzaile lehenetsia.
     */
    public EguneraketakBean() {
    }

    /**
     * Eguneraketaren kontrol mezua lortzen du.
     *
     * @return kontrol mezua
     */
    public String getKontrola() {
        return kontrola;
    }

    /**
     * Eguneraketaren kontrol mezua ezartzen du.
     *
     * @param kontrola kontrol mezu berria
     */
    public void setKontrola(String kontrola) {
        this.kontrola = kontrola;
    }
}