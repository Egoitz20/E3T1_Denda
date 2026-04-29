package datuBaseKonexioa;

/**
 * Txertaketen eta ezabaketen kontrola gordetzeko Bean klasea.
 * <p>
 * Klase honek datu-basean egindako txertaketa eta ezabaketa guztien
 * erregistroa biltzen du, bi zutabetan banatuta: txertaketak eta ezabaketak.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class TxertaketakEzabaketakKontrolaBean {

    /** Txertaketa eragiketaren deskribapena */
    private String txertaketak;
    
    /** Ezabaketa eragiketaren deskribapena */
    private String ezabaketak;

    /**
     * Eraikitzaile lehenetsia.
     */
    public TxertaketakEzabaketakKontrolaBean() {
    }

    /**
     * Txertaketa eragiketaren deskribapena lortzen du.
     *
     * @return txertaketaren deskribapena
     */
    public String getTxertaketak() {
        return txertaketak;
    }

    /**
     * Txertaketa eragiketaren deskribapena ezartzen du.
     *
     * @param txertaketak txertaketaren deskribapen berria
     */
    public void setTxertaketak(String txertaketak) {
        this.txertaketak = txertaketak;
    }

    /**
     * Ezabaketa eragiketaren deskribapena lortzen du.
     *
     * @return ezabaketaren deskribapena
     */
    public String getEzabaketak() {
        return ezabaketak;
    }

    /**
     * Ezabaketa eragiketaren deskribapena ezartzen du.
     *
     * @param ezabaketak ezabaketaren deskribapen berria
     */
    public void setEzabaketak(String ezabaketak) {
        this.ezabaketak = ezabaketak;
    }
}