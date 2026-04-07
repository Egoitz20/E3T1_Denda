package datuBaseKonexioa;

import java.time.LocalDateTime;

public class AbisuakBean {
    private String bezeroIzena;
    private String kontzeptua;
    private String deskribapena;
    private LocalDateTime data;  // Campo para la fecha

    public AbisuakBean(String bezeroIzena, String kontzeptua, String deskribapena) {
        this.bezeroIzena = bezeroIzena;
        this.kontzeptua = kontzeptua;
        this.deskribapena = deskribapena;
        this.data = LocalDateTime.now();  // Fecha actual
    }

    // Getters y Setters
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}