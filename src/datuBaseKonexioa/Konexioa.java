package datuBaseKonexioa;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Datu-basearekiko konexioa kudeatzeko klasea.
 * <p>
 * Klase honek MySQL datu-basearekin konexioa ezartzen du
 * JDBC erabiliz. Konexioaren parametroak barruan daude definituta.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class Konexioa {

    /**
     * Eraikitzaile lehenetsia.
     */
    public Konexioa() {
    }

    /**
     * Datu-basearekiko konexioa ezartzen du.
     * <p>
     * MySQL driverra kargatzen du, URL, erabiltzailea eta pasahitza
     * erabiliz konexioa sortzen du.
     * </p>
     *
     * @return Datu-baseko konexioa, errorea gertatuz gero {@code null}
     */
    public Connection konektorea() {
        Connection konekzioa = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/e2t1dbaplikazioa";
            String erabiltzailea = "root";
            String pasahitza = "root";
            konekzioa = DriverManager.getConnection(url, erabiltzailea, pasahitza);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return konekzioa;
    }
}