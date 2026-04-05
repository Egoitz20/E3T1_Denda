package datuBaseKonexioa;

import java.time.LocalDateTime;

public class AbisuakBean {
    
    private String bezeroIzena;
    private String kontzeptua;
    private String deskribapena;
    
    public AbisuakBean() {
    }
    
    public AbisuakBean(String bezeroIzena, String kontzeptua, String deskribapena) {
        this.bezeroIzena = bezeroIzena;
        this.kontzeptua = kontzeptua;
    }
    
    public String getBezeroIzena() {
        return bezeroIzena;
    }
    
    public void setBezeroIzena(String bezeroIzena) {
        this.bezeroIzena = bezeroIzena;
    }
    
    public String getKontzeptua() {
        return kontzeptua;
    }
    
    public void setKontzeptua(String kontzeptua) {
        this.kontzeptua = kontzeptua;
    }
    
    public String getDeskribapena() {
        return deskribapena;
    }
    
    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }
}