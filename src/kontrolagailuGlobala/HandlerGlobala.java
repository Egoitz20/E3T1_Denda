package kontrolagailuGlobala;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bezeroLeihoak.bezeroMenuPrintzipala.BezeroMenuPrintzipala;
import datuBaseKonexioa.BezeroBean;
import datuBaseKonexioa.EskaerakBean;
import datuBaseKonexioa.Konexioa;
import datuBaseKonexioa.LangileSaltzaileBean;
import datuBaseKonexioa.ProduktuBean;
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

	private static final String BEZERO_TAULA = "SELECT * FROM BISTA_BEZERO_BEZEROTELEFONO";
	private static final String LANGILESALTZAILE_TAULA = "SELECT * FROM BISTA_LANGILESALTZAILE";

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
				erregistro.setNan(rs.getString("NAN"));
				erregistro.setEmaila(rs.getString("EMAILA"));
				erregistro.setHelbidea(rs.getString("HELBIDEA"));
				erregistro.setZenbakia(rs.getString("ZENBAKIA"));
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

	// Saltzaileak informazio gehiago datu-basetik jasotzeko metodoa (INNER JOIN
	// Langile taulatik))
	protected ArrayList<LangileSaltzaileBean> jasoLangileSaltzaileak() {

		Konexioa db = new Konexioa();
		Connection konexioa = null;
		Statement stmt = null;
		ResultSet rs = null;
		LangileSaltzaileBean erregistro;
		ArrayList<LangileSaltzaileBean> saltzaileTaula = new ArrayList<LangileSaltzaileBean>();

		try {
			konexioa = db.konektorea();
			stmt = konexioa.createStatement();
			rs = stmt.executeQuery(LANGILESALTZAILE_TAULA);
			while (rs.next()) {
				erregistro = new LangileSaltzaileBean();
				erregistro.setId(rs.getInt("ID"));
				erregistro.setLangileIzenaAbizena(rs.getString("LANGILE_IZENA_ABIZENAK"));
				erregistro.setEmaila(rs.getString("EMAILA"));
				erregistro.setTelefonoa(rs.getString("TELEFONOA"));
				erregistro.setKontratazio_data(rs.getDate("KONTRATAZIO_DATA"));
				erregistro.setId_nagusi(rs.getInt("ID_NAGUSI"));
				erregistro.setNagusiIzenaAbizena(rs.getString("NAGUSI_IZENA_ABIZENAK"));
				erregistro.setSoldata(rs.getInt("SOLDATA"));
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
			e.printStackTrace();
		}

		return saltzaileTaula;

	}

	private static final String ESKAERA_PRODUKTU_TAULA = "SELECT PRODUKTU.IZENA AS PRODUKTU, KATEGORIA.IZENA AS KATEGORIA, "
			+ "ESKARI_LERRO.KOPURUA, ESKARI.ESKAERA_DATA AS DATA, ESKARI_EGOERA.DESKRIBAPENA AS DESKRIBAPEN, "
			+ "CONCAT(LANGILE.IZENA, ' ', LANGILE.ABIZENA) AS SALTZAILEA " + "FROM PRODUKTU "
			+ "INNER JOIN KATEGORIA ON PRODUKTU.ID_KATEGORIA = KATEGORIA.ID "
			+ "INNER JOIN ESKARI_LERRO ON PRODUKTU.ID = ESKARI_LERRO.ID_PRODUKTU "
			+ "INNER JOIN ESKARI ON ESKARI_LERRO.ID_ESKARI = ESKARI.ID "
			+ "INNER JOIN ESKARI_EGOERA ON ESKARI.ID_EGOERA = ESKARI_EGOERA.ID "
			+ "INNER JOIN SALTZAILE ON ESKARI.ID_SALTZAILE = SALTZAILE.ID "
			+ "INNER JOIN LANGILE ON SALTZAILE.ID = LANGILE.ID " + "INNER JOIN BEZERO ON ESKARI.ID_BEZERO = BEZERO.ID "
			+ "WHERE BEZERO.IZENA = ? AND BEZERO.ABIZENA = ?";

	// Bezeroen eskatutako produktuak jasotzeko metodoa
	protected ArrayList<EskaerakBean> jasoBezeroenProduktuEskaerak(String izena, String abizena) {

		Konexioa db = new Konexioa();
		Connection konexioa = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EskaerakBean erregistro;
		ArrayList<EskaerakBean> produktuEskaeraTaula = new ArrayList<EskaerakBean>();

		try {
			konexioa = db.konektorea();
			pstmt = konexioa.prepareStatement(ESKAERA_PRODUKTU_TAULA);

			pstmt.setString(1, izena);
			pstmt.setString(2, abizena);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				erregistro = new EskaerakBean();
				erregistro.setProduktu(rs.getString("PRODUKTU"));
				erregistro.setKategoria(rs.getString("KATEGORIA"));
				erregistro.setKopurua(rs.getInt("KOPURUA"));
				erregistro.setEskaera_data(rs.getDate("DATA"));
				erregistro.setDeskribapena(rs.getString("DESKRIBAPEN"));
				erregistro.setSaltzailea(rs.getString("SALTZAILEA"));
				produktuEskaeraTaula.add(erregistro);
			}

			rs.close();
			pstmt.close();
			konexioa.close();

		} catch (SQLException e) {
			System.out.println("Errorea: " + e);
			System.out.println("Errorea: " + e.getCause());
			e.printStackTrace();
		}

		return produktuEskaeraTaula;
	}

	private static final String DENDAKO_PRODUKTUAK = "SELECT PRODUKTU.ID, PRODUKTU.IZENA, PRODUKTU.DESKRIBAPENA, PRODUKTU.SALNEURRIA FROM PRODUKTU \r\n"
			+ "INNER JOIN KATEGORIA ON PRODUKTU.ID_KATEGORIA = KATEGORIA.ID\r\n" + "WHERE KATEGORIA.IZENA = ?";

	protected ArrayList<ProduktuBean> jasoProduktuak(String kategoria) {

		Konexioa db = new Konexioa();
		Connection konexioa = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProduktuBean erregistro;
		ArrayList<ProduktuBean> produktuTaula = new ArrayList<ProduktuBean>();

		try {
			konexioa = db.konektorea();
			pstmt = konexioa.prepareStatement(DENDAKO_PRODUKTUAK);

			pstmt.setString(1, kategoria);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				erregistro = new ProduktuBean();
				erregistro.setId(rs.getInt("PRODUKTU.ID"));
				erregistro.setIzena(rs.getString("PRODUKTU.IZENA"));
				erregistro.setDeskribapena(rs.getString("PRODUKTU.DESKRIBAPENA"));
				erregistro.setSalneurria(rs.getDouble("PRODUKTU.SALNEURRIA"));
				produktuTaula.add(erregistro);
			}

			rs.close();
			pstmt.close();
			konexioa.close();

		} catch (SQLException e) {
			System.out.println("Errorea: " + e);
			System.out.println("Errorea: " + e.getCause());
			e.printStackTrace();
		}

		return produktuTaula;
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

	// Bezero Errergistroa
	private static final String ERABILTZAILE_GEHIKETA = "INSERT INTO BEZERO (IZENA, ABIZENA, EMAILA, ERABILTZAILEA, PASAHITZA) VALUES (?, ?, ?, ?, ?)";

	protected void erregistroBezeroBerriaSortu(String erabiltzailea, String pasahitza) {
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
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (konexioa != null)
					konexioa.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	};

	protected void txertatuBezeroa(String izena, String abizena, String nan, String emaila, String helbidea,
			String erabiltzailea, String pasahitza, String zenbakia) {

		Konexioa db = new Konexioa();
		Connection konexioa = null;
		CallableStatement cs = null;

		String sql = "{CALL BEZERO_GEHIKETA(?, ?, ?, ?, ?, ?, ?, ?)}";

		try {

			konexioa = db.konektorea();
			cs = konexioa.prepareCall(sql);

			cs.setString(1, izena);
			cs.setString(2, abizena);
			cs.setString(3, nan);
			cs.setString(4, emaila);
			cs.setString(5, helbidea);
			cs.setString(6, erabiltzailea);
			cs.setString(7, pasahitza);
			cs.setString(8, zenbakia);

			cs.execute();

		} catch (SQLException e) {
			System.out.println("Errorea: " + e);
			System.out.println("Errorea: " + e.getCause());
		} finally {
			try {
				if (cs != null)
					cs.close();
				if (konexioa != null)
					konexioa.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// Saltzaile sortzeko metodoa
	protected void txertatuSaltzailea(String izena, String abizena, String erabiltzailea, String pasahitza,
			String emaila, String telefonoa, int soldata) {

		Konexioa db = new Konexioa();
		Connection konexioa = null;
		CallableStatement cs = null;

		String sql = "{CALL SALTZAILE_GEHIKETA(?, ?, ?, ?, ?, ?, ?)}";

		try {

			konexioa = db.konektorea();
			cs = konexioa.prepareCall(sql);

			cs.setString(1, izena);
			cs.setString(2, abizena);
			cs.setString(3, erabiltzailea);
			cs.setString(4, pasahitza);
			cs.setString(5, emaila);
			cs.setString(6, telefonoa);
			cs.setInt(7, soldata);

			cs.execute();

		} catch (SQLException e) {
			System.out.println("Errorea: " + e);
			System.out.println("Errorea: " + e.getCause());
		} finally {
			try {
				if (cs != null)
					cs.close();
				if (konexioa != null)
					konexioa.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * -----------------------------------------------TXERTAKETAK
	 * AMAIERA----------------------------------------------------------------------
	 * ---
	 */

	/*
	 * -----------------------------------------------ALDAKETAK-------------------
	 * ------------------------------------------------------
	 */
	protected void eguneratuSaltzailea(int id, String izena, String abizena, String erabiltzailea, String pasahitza,
			String emaila, String telefonoa, int soldata) {
		Konexioa db = new Konexioa();
		Connection konexioa = null;
		CallableStatement cs = null;

		String sql = "{CALL SALTZAILE_ALDAKETA(?, ?, ?, ?, ?, ?, ?, ?)}";

		try {
			konexioa = db.konektorea();
			cs = konexioa.prepareCall(sql);

			cs.setInt(1, id);
			cs.setString(2, izena);
			cs.setString(3, abizena);
			cs.setString(4, erabiltzailea);
			cs.setString(5, pasahitza);
			cs.setString(6, emaila);
			cs.setString(7, telefonoa);
			cs.setInt(8, soldata);

			cs.execute();

			System.out.println("Saltzailea eguneratuta. ID: " + id);

		} catch (SQLException e) {
			System.out.println("Errorea saltzailea eguneratzean: " + e);
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da saltzailea eguneratu", "Datu-baseko errorea: " + e.getMessage());
		} finally {
			try {
				if (cs != null)
					cs.close();
				if (konexioa != null)
					konexioa.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void eguneratuBezeroa(int id, String izena, String abizena, String nan, String emaila, String helbidea,
			String erabiltzailea, String pasahitza, String zenbakia) {

		Konexioa db = new Konexioa();
		Connection konexioa = null;
		CallableStatement cs = null;

		String sql = "{CALL BEZERO_ALDAKETA(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

		try {
			konexioa = db.konektorea();
			cs = konexioa.prepareCall(sql);

			cs.setInt(1, id);
			cs.setString(2, izena);
			cs.setString(3, abizena);
			cs.setString(4, nan);
			cs.setString(5, emaila);
			cs.setString(6, helbidea);
			cs.setString(7, erabiltzailea);
			cs.setString(8, pasahitza);
			cs.setString(9, zenbakia);

			cs.execute();

			System.out.println("Saltzailea eguneratuta. ID: " + id);

		} catch (SQLException e) {
			System.out.println("Errorea saltzailea eguneratzean: " + e);
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da saltzailea eguneratu", "Datu-baseko errorea: " + e.getMessage());
		} finally {
			try {
				if (cs != null)
					cs.close();
				if (konexioa != null)
					konexioa.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * -----------------------------------------------ALDAKETAK
	 * AMAIERA-------------------
	 * ------------------------------------------------------
	 */

	/*
	 * -----------------------------------------------EZABAKETAK-------------------
	 * ------------------------------------------------------
	 */

	// Método para eliminar usando procedimiento
	protected void ezabatuSaltzailea(int id) {
		Konexioa db = new Konexioa();
		Connection konexioa = null;
		CallableStatement cs = null;

		String sql = "{CALL SALTZAILE_EZABATU(?)}";

		try {
			konexioa = db.konektorea();
			cs = konexioa.prepareCall(sql);
			cs.setInt(1, id);
			cs.execute();

			System.out.println("Saltzailea ezabatuta. ID: " + id);

		} catch (SQLException e) {
			System.out.println("Errorea saltzailea ezabatzean: " + e);
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da saltzailea ezabatu", "Datu-baseko errorea: " + e.getMessage());
		} finally {
			try {
				if (cs != null)
					cs.close();
				if (konexioa != null)
					konexioa.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Método para eliminar usando procedimiento
	protected void ezabatuBezeroa(int id) {
		Konexioa db = new Konexioa();
		Connection konexioa = null;
		CallableStatement cs = null;

		String sql = "{CALL BEZERO_EZABATU(?)}";

		try {
			konexioa = db.konektorea();
			cs = konexioa.prepareCall(sql);
			cs.setInt(1, id);
			cs.execute();

			System.out.println("Saltzailea ezabatuta. ID: " + id);

		} catch (SQLException e) {
			System.out.println("Errorea saltzailea ezabatzean: " + e);
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da saltzailea ezabatu", "Datu-baseko errorea: " + e.getMessage());
		} finally {
			try {
				if (cs != null)
					cs.close();
				if (konexioa != null)
					konexioa.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * -----------------------------------------------EZABAKETAK
	 * AMAIERA-------------------
	 * ------------------------------------------------------
	 */

}
