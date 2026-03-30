package datuBaseKonexioa;

import java.sql.Connection;
import java.sql.DriverManager;

public class Konexioa {

	public Konexioa() {

	}

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
