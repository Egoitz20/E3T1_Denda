package kontrolagailuGlobala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bezeroLeihoak.bezeroMenuPrintzipala.BezeroMenuPrintzipala;
import datuBaseKonexioa.BezeroBean;
import datuBaseKonexioa.Konexioa;
import datuBaseKonexioa.SaltzaileBean;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import orokorLeihoak.login.Login;
import saltzaileLeihoak.saltzaileMenuPrintzipala.SaltzaileMenuPrintzipala;

public class HandlerGlobala {

	private Stage stage;

	// Logineko stage jasotzeko metodoa
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	// Salbuespen alerta metodoa
	protected void irekiAlerta(String titulua, String goiburua, String mezua) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulua);
		alert.setHeaderText(goiburua);
		alert.setContentText(mezua);
		alert.showAndWait();
	}

	/*
	 * -----------------------------------------------LEIHO
	 * KONTROLA---------------------------------------------------------------------
	 * ----
	 */

	
	// Oraingo leihoa ixten da
	protected void itxiOraingoLeihoa() {
		if (stage != null) {
			stage.close();
		}
	}

	// Oraingo leihoa atera eta ikusiko da
	protected void ikusiLeihoa() {
		if (stage != null) {
			stage.show();
		}
	}

	// Oraingo leihoa okultatu egingo da
	protected void okultatuLeihoa() {
		if (stage != null) {
			stage.hide();
		}
	}

	// SaltzaileMenuPrintzipala irekitzeko metodoa
	protected void irekiSaltzaileMenuPrintzipala() {
		try {
			SaltzaileMenuPrintzipala saltzaileMenu = new SaltzaileMenuPrintzipala();

			// Sortu Stage berri bat leiho -rako
			Stage newStage = new Stage();

			// Pistu leihoa deitzen start metodoari
			saltzaileMenu.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea saltzailearen menua irekitzean: " + e.getMessage());
		}
	}

	// BezeroMenuPrintzipala irekitzeko metodoa
	protected void irekiBezeroMenuPrintzipala() {
		try {
			BezeroMenuPrintzipala bezeroMenu = new BezeroMenuPrintzipala();
			Stage newStage = new Stage();

			bezeroMenu.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea bezeroaren menua irekitzean: " + e.getMessage());
		}
	}

	protected void irekiLogina() {
		try {
			Login logina = new Login();
			Stage newStage = new Stage();

			logina.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea loginaren leihoa irekitzean: " + e.getMessage());

		}
	}

	/*
	 * -----------------------------------------------LEIHO KONTROLA
	 * AMAIERA----------------------------------------------------------------------
	 * ---
	 */

	/*
	 * -----------------------------------------------KONTSULTAK--------------------
	 * -----------------------------------------------------
	 */

	private static final String BEZERO_TAULA = "SELECT * FROM BEZERO";
	private static final String SALTZAILE_TAULA = "SELECT * FROM SALTZAILE";

	// Bezeroak datu-basetik jasotzeko metodoa
	protected ArrayList<BezeroBean> jasoBezeroak() {

		Konexioa db = new Konexioa();
		Connection konexioa = null;
		Statement stmt = null;
		ResultSet rs = null;
		BezeroBean erregistro;
		ArrayList<BezeroBean> bezeroTaula = new ArrayList<BezeroBean>();

		try {
			konexioa = db.konektorea();
			stmt = konexioa.createStatement();
			rs = stmt.executeQuery(BEZERO_TAULA);

			while (rs.next()) {
				erregistro = new BezeroBean();
				erregistro.setId(rs.getInt("ID"));
				erregistro.setIzena(rs.getString("IZENA"));
				erregistro.setAbizena(rs.getString("ABIZENA"));
				erregistro.setHelbidea(rs.getString("HELBIDEA"));
				erregistro.setEmaila(rs.getString("EMAILA"));
				erregistro.setErabiltzaile(rs.getString("ERABILTZAILEA"));
				erregistro.setPasahitza(rs.getString("PASAHITZA"));
				bezeroTaula.add(erregistro);
			}

			rs.close();
			stmt.close();
			konexioa.close();
		} catch (SQLException e) {
			System.out.println("Errorea: " + e);
			System.out.println("Errorea: " + e.getCause());
		}
		return bezeroTaula;
	}

	// Saltzaileak datu-basetik jasotzeko metodoa
	protected ArrayList<SaltzaileBean> jasoSaltzaileak() {

		Konexioa db = new Konexioa();
		Connection konexioa = null;
		Statement stmt = null;
		ResultSet rs = null;
		SaltzaileBean erregistro;
		ArrayList<SaltzaileBean> saltzaileTaula = new ArrayList<SaltzaileBean>();

		try {
			konexioa = db.konektorea();
			stmt = konexioa.createStatement();
			rs = stmt.executeQuery(SALTZAILE_TAULA);
			while (rs.next()) {
				erregistro = new SaltzaileBean();
				erregistro.setId(rs.getInt("ID"));
				erregistro.setErabiltzailea(rs.getString("ERABILTZAILEA"));
				erregistro.setPasahitza(rs.getString("PASAHITZA"));
				saltzaileTaula.add(erregistro);
			}

			rs.close();
			stmt.close();
			konexioa.close();

		} catch (SQLException e) {
			System.out.println("Errorea: " + e);
			System.out.println("Errorea: " + e.getCause());
		}

		return saltzaileTaula;

	}

	/*
	 * -----------------------------------------------KONTSULTAK
	 * AMAIERA----------------------------------------------------------------------
	 * ---
	 */

	/*
	 * -----------------------------------------------TXERTAKETAK-------------------
	 * ------------------------------------------------------
	 */

	private static final String ERABILTZAILE_GEHIKETA = "INSERT INTO BEZERO (IZENA, ABIZENA, EMAILA, ERABILTZAILEA, PASAHITZA) VALUES (?, ?, ?, ?, ?)";

	protected void bezeroBerriaSortu(String erabiltzailea, String pasahitza) {
		Konexioa db = new Konexioa();
		Connection konexioa = null;
		PreparedStatement stmt = null;
		try {
			konexioa = db.konektorea();
			stmt = konexioa.prepareStatement(ERABILTZAILE_GEHIKETA);

			stmt.setString(1, "Izena");
			stmt.setString(2, "Abizena");
			stmt.setString(3, "Emaila");
			stmt.setString(4, erabiltzailea);
			stmt.setString(5, pasahitza);

			stmt.executeUpdate();

			stmt.close();
			konexioa.close();
		} catch (SQLException e) {
			System.out.println("ERROREA: " + e);
			System.out.println("ERROREA: " + e.getCause());
		}

	};

	/*
	 * -----------------------------------------------TXERTAKETAK
	 * AMAIERA----------------------------------------------------------------------
	 * ---
	 */

}
