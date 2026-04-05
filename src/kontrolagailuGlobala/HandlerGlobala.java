package kontrolagailuGlobala;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bezeroLeihoak.bezeroMenuPrintzipala.BezeroMenuPrintzipala;
import datuBaseKonexioa.AbisuakBean;
import datuBaseKonexioa.BezeroBean;
import datuBaseKonexioa.EskaerakBean;
import datuBaseKonexioa.Konexioa;
import datuBaseKonexioa.LangileSaltzaileBean;
import datuBaseKonexioa.ProduktuBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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

	/*
	 * -----------------------------------------------BEZERO LEIHOAK HANDLER
	 * -------------------------------------------------------------------------
	 */

	/*-- KATEGORIAKO METODOAK -- */

	protected void konfiguratuKategoriaZutabeak(TableColumn<ProduktuBean, String> izenaColumn,
			TableColumn<ProduktuBean, String> deskribapenaColumn, TableColumn<ProduktuBean, Double> salneurriaColumn) {

		// Produktuaren izena bistaratzen du
		izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));

		// Produktuaren deskribapena bistaratzen du
		deskribapenaColumn.setCellValueFactory(new PropertyValueFactory<>("deskribapena"));

		// Produktuaren salneurria bistaratzen du
		salneurriaColumn.setCellValueFactory(new PropertyValueFactory<>("salneurria"));

	}

	// Azalpena Praktikoa: Supermerkatura joan eta "RAM diren produktu guztiak"
	// eskatzea bezala da.
	// Zerrenda bat ematen dizute, eta zuk erosketa-zerrenda bat sortzen duzu,
	// non produktu bakoitza 0 kopuruarekin hasten den.
	protected void kargatuKategoriaDatuak(ObservableList<ProduktuBean> kategoriaList,
			ObservableList<ProduktuBean> filtrazioList, Map<Integer, Integer> kantitateak, String kategoria) {
		// jasoProduktuak("RAM") -> Datu baseari deitzen dio eta "RAM" kategoriako
		// produktuak eskatzen ditu
		ArrayList<ProduktuBean> kategoriaProduktuak = jasoProduktuak(kategoria);
		// RamList eduki guztia ordezkatzen du lortutako produktuekin
		kategoriaList.setAll(kategoriaProduktuak);
		// Hasieran, filtratutako zerrenda, zerrenda osoaren berdina da
		filtrazioList.setAll(kategoriaList);

		// Kantitateen mapa hasieratzea: produktu bakoitza 0 unitaterekin hasten da
		for (ProduktuBean produktu : kategoriaList) {
			// pufIfAbsent() -> ID honetarako kantitaterik ez badago, bakarrik gehitzen du
			kantitateak.putIfAbsent(produktu.getId(), 0);
		}
	}

	/*
	 * -----------------------------------------------BEZERO LEIHOAK HANDLER AMAIERA
	 * -------------------------------------------------------------------------
	 */

	/*
	 * -----------------------------------------------PAGINATION OROKORRA
	 * -------------------------------------------------------------------------
	 */

	// BEZERO KATEGORIA METODOA

	private static final int ROWS_PER_PAGE = 10;

	protected void konfiguratuBezeroKategoriaPaginazioa(Pagination pagination,
			ObservableList<ProduktuBean> filtrazioList, TableView<ProduktuBean> tableView) {
		int orriGeiketa = (int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE);
		pagination.setPageCount(orriGeiketa == 0 ? 1 : orriGeiketa);
		pagination.setCurrentPageIndex(0);

		pagination.currentPageIndexProperty().addListener((obs, oldIndex, indexBerria) -> {
			eguneratuBezeroKteagoriaOrriaDatuak(indexBerria.intValue(), filtrazioList, tableView);
		});

		eguneratuBezeroKteagoriaOrriaDatuak(0, filtrazioList, tableView);
	}

	protected void eguneratuBezeroKteagoriaOrriaDatuak(int indexOrria, ObservableList<ProduktuBean> filtrazioList,
			TableView<ProduktuBean> tableView) {
		int lehengoErregistroa = indexOrria * ROWS_PER_PAGE;
		int azkenErregistroa = Math.min(lehengoErregistroa + ROWS_PER_PAGE, filtrazioList.size());

		if (lehengoErregistroa < filtrazioList.size()) {
			List<ProduktuBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
			tableView.setItems(FXCollections.observableArrayList(pageData));
		} else {
			tableView.setItems(FXCollections.observableArrayList());
		}
	}

	// SALTZAILE METODOA

	protected void konfiguratuSaltzailePaginazioa(Pagination pagination,
			ObservableList<LangileSaltzaileBean> filtrazioList, TableView<LangileSaltzaileBean> tableView) {
		// Kalkulatu zenbat orrialde behar diren (Adib. 29 erregistro / 10 = 2.9 → 3
		// orri)
		int orriGeiketa = (int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE);

		// if-else bat idazteko modu trinkoa da.

		// if (totalPages == 0) {
		// pagination.setPageCount(1);// Datuak ez badago, gutxienez orri 1
		// } else {
		// pagination.setPageCount(totalPages);// Datuak badagoela, totalPages zenbakia
		// erabiliko du
		// }

		// Formula: baldintza ? baloreEgia : baloreFaltsua

		// totalPages == 0 -> Orri kopurua 0 da?
		// ? 1 -> Egia bada, 1 balorea erabili
		// : totalPages -> Faltsua bada, totalPages balorea erabili
		pagination.setPageCount(orriGeiketa == 0 ? 1 : orriGeiketa); // Gutxienez, orri 1
		pagination.setCurrentPageIndex(0); // Orri 0 hasiko da(Lehengoan)

		// ENTZUN: erabiltzailea orria aldatzen denean
		pagination.currentPageIndexProperty().addListener((obs, oldIndex, indexBerria) -> {
			eguneratuSaltzaileOrriaDatuak(indexBerria.intValue(), filtrazioList, tableView);
		});

		eguneratuSaltzaileOrriaDatuak(0, filtrazioList, tableView); // Lehengo orria bistarako du
	}

	// Kalkulatu zer erregistro dagozkion eskatutako orriari eta taulan erakusten
	// ditu.
	protected void eguneratuSaltzaileOrriaDatuak(int indexOrria, ObservableList<LangileSaltzaileBean> filtrazioList,
			TableView<LangileSaltzaileBean> tableView) {

		// Orri 0 adibidea(lehenengoa):
		// lehengoErregistroa = 0 * 10 = 0
		// azkenErregistroa = min(0+10, 29) = 10
		// 0tik 9ra arte erregistroak bistaratzen ditu (10 erregistro)
		int lehengoErregistroa = indexOrria * ROWS_PER_PAGE; // Oraingo orrialdeko lehen erregistroa
		int azkenErregistroa = Math.min(lehengoErregistroa + ROWS_PER_PAGE, filtrazioList.size());

		if (lehengoErregistroa < filtrazioList.size()) {
			// Bakarrik oraingo orrialdeko erregistroak ateratzen ditu
			// Ateratzen du "Azpi-Lista" bat (zati bat) zerrenda osotik.
			List<LangileSaltzaileBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
			tableView.setItems(FXCollections.observableArrayList(pageData)); // Bistaratzen ditu taulan
		} else {
			tableView.setItems(FXCollections.observableArrayList()); // Ez dago daturik, taula ezer gabe
		}
	}

	// BEZERO METODOA

	protected void konfiguratuBezeroPaginazioa(Pagination pagination, ObservableList<BezeroBean> filtrazioList,
			TableView<BezeroBean> tableView) {
		// Kalkulatu zenbat orrialde behar diren (Adib. 29 erregistro / 10 = 2.9 → 3
		// orri)
		int orriGeiketa = (int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE);

		// if-else bat idazteko modu trinkoa da.

		// if (totalPages == 0) {
		// pagination.setPageCount(1);// Datuak ez badago, gutxienez orri 1
		// } else {
		// pagination.setPageCount(totalPages);// Datuak badagoela, totalPages zenbakia
		// erabiliko du
		// }

		// Formula: baldintza ? baloreEgia : baloreFaltsua

		// totalPages == 0 -> Orri kopurua 0 da?
		// ? 1 -> Egia bada, 1 balorea erabili
		// : totalPages -> Faltsua bada, totalPages balorea erabili
		pagination.setPageCount(orriGeiketa == 0 ? 1 : orriGeiketa); // Gutxienez, orri 1
		pagination.setCurrentPageIndex(0); // Orri 0 hasiko da(Lehengoan)

		// ENTZUN: erabiltzailea orria aldatzen denean
		pagination.currentPageIndexProperty().addListener((obs, oldIndex, indexBerria) -> {
			eguneratuBezeroOrriaDatuak(indexBerria.intValue(), filtrazioList, tableView);
		});

		eguneratuBezeroOrriaDatuak(0, filtrazioList, tableView); // Lehengo orria bistarako du
	}

	// Kalkulatu zer erregistro dagozkion eskatutako orriari eta taulan erakusten
	// ditu.
	protected void eguneratuBezeroOrriaDatuak(int indexOrria, ObservableList<BezeroBean> filtrazioList,
			TableView<BezeroBean> tableView) {

		// Orri 0 adibidea(lehenengoa):
		// lehengoErregistroa = 0 * 10 = 0
		// azkenErregistroa = min(0+10, 29) = 10
		// 0tik 9ra arte erregistroak bistaratzen ditu (10 erregistro)
		int lehengoErregistroa = indexOrria * ROWS_PER_PAGE; // Oraingo orrialdeko lehen erregistroa
		int azkenErregistroa = Math.min(lehengoErregistroa + ROWS_PER_PAGE, filtrazioList.size());

		if (lehengoErregistroa < filtrazioList.size()) {
			// Bakarrik oraingo orrialdeko erregistroak ateratzen ditu
			// Ateratzen du "Azpi-Lista" bat (zati bat) zerrenda osotik.
			List<BezeroBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
			tableView.setItems(FXCollections.observableArrayList(pageData)); // Bistaratzen ditu taulan
		} else {
			tableView.setItems(FXCollections.observableArrayList()); // Ez dago daturik, taula ezer gabe
		}
	}
	
	// ESKAERAK METODOA

		protected void konfiguratuEskaerakPaginazioa(Pagination pagination, ObservableList<EskaerakBean> filtrazioList,
				TableView<EskaerakBean> tableView) {
			// Kalkulatu zenbat orrialde behar diren (Adib. 29 erregistro / 10 = 2.9 → 3
			// orri)
			int orriGeiketa = (int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE);

			// if-else bat idazteko modu trinkoa da.

			// if (totalPages == 0) {
			// pagination.setPageCount(1);// Datuak ez badago, gutxienez orri 1
			// } else {
			// pagination.setPageCount(totalPages);// Datuak badagoela, totalPages zenbakia
			// erabiliko du
			// }

			// Formula: baldintza ? baloreEgia : baloreFaltsua

			// totalPages == 0 -> Orri kopurua 0 da?
			// ? 1 -> Egia bada, 1 balorea erabili
			// : totalPages -> Faltsua bada, totalPages balorea erabili
			pagination.setPageCount(orriGeiketa == 0 ? 1 : orriGeiketa); // Gutxienez, orri 1
			pagination.setCurrentPageIndex(0); // Orri 0 hasiko da(Lehengoan)

			// ENTZUN: erabiltzailea orria aldatzen denean
			pagination.currentPageIndexProperty().addListener((obs, oldIndex, indexBerria) -> {
				eguneratuEskaerakOrriaDatuak(indexBerria.intValue(), filtrazioList, tableView);
			});

			eguneratuEskaerakOrriaDatuak(0, filtrazioList, tableView); // Lehengo orria bistarako du
		}

		// Kalkulatu zer erregistro dagozkion eskatutako orriari eta taulan erakusten
		// ditu.
		protected void eguneratuEskaerakOrriaDatuak(int indexOrria, ObservableList<EskaerakBean> filtrazioList,
				TableView<EskaerakBean> tableView) {

			// Orri 0 adibidea(lehenengoa):
			// lehengoErregistroa = 0 * 10 = 0
			// azkenErregistroa = min(0+10, 29) = 10
			// 0tik 9ra arte erregistroak bistaratzen ditu (10 erregistro)
			int lehengoErregistroa = indexOrria * ROWS_PER_PAGE; // Oraingo orrialdeko lehen erregistroa
			int azkenErregistroa = Math.min(lehengoErregistroa + ROWS_PER_PAGE, filtrazioList.size());

			if (lehengoErregistroa < filtrazioList.size()) {
				// Bakarrik oraingo orrialdeko erregistroak ateratzen ditu
				// Ateratzen du "Azpi-Lista" bat (zati bat) zerrenda osotik.
				List<EskaerakBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
				tableView.setItems(FXCollections.observableArrayList(pageData)); // Bistaratzen ditu taulan
			} else {
				tableView.setItems(FXCollections.observableArrayList()); // Ez dago daturik, taula ezer gabe
			}
		}
		
		
		protected void konfiguratuAbisuakPaginazioa(Pagination pagination, ObservableList<AbisuakBean> filtrazioList,
				TableView<AbisuakBean> tableView) {
			// Kalkulatu zenbat orrialde behar diren (Adib. 29 erregistro / 10 = 2.9 → 3
			// orri)
			int orriGeiketa = (int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE);

			// if-else bat idazteko modu trinkoa da.

			// if (totalPages == 0) {
			// pagination.setPageCount(1);// Datuak ez badago, gutxienez orri 1
			// } else {
			// pagination.setPageCount(totalPages);// Datuak badagoela, totalPages zenbakia
			// erabiliko du
			// }

			// Formula: baldintza ? baloreEgia : baloreFaltsua

			// totalPages == 0 -> Orri kopurua 0 da?
			// ? 1 -> Egia bada, 1 balorea erabili
			// : totalPages -> Faltsua bada, totalPages balorea erabili
			pagination.setPageCount(orriGeiketa == 0 ? 1 : orriGeiketa); // Gutxienez, orri 1
			pagination.setCurrentPageIndex(0); // Orri 0 hasiko da(Lehengoan)

			// ENTZUN: erabiltzailea orria aldatzen denean
			pagination.currentPageIndexProperty().addListener((obs, oldIndex, indexBerria) -> {
				eguneratuAbisuakOrriaDatuak(indexBerria.intValue(), filtrazioList, tableView);
			});

			eguneratuAbisuakOrriaDatuak(0, filtrazioList, tableView); // Lehengo orria bistarako du
		}

		// Kalkulatu zer erregistro dagozkion eskatutako orriari eta taulan erakusten
		// ditu.
		protected void eguneratuAbisuakOrriaDatuak(int indexOrria, ObservableList<AbisuakBean> filtrazioList,
				TableView<AbisuakBean> tableView) {

			// Orri 0 adibidea(lehenengoa):
			// lehengoErregistroa = 0 * 10 = 0
			// azkenErregistroa = min(0+10, 29) = 10
			// 0tik 9ra arte erregistroak bistaratzen ditu (10 erregistro)
			int lehengoErregistroa = indexOrria * ROWS_PER_PAGE; // Oraingo orrialdeko lehen erregistroa
			int azkenErregistroa = Math.min(lehengoErregistroa + ROWS_PER_PAGE, filtrazioList.size());

			if (lehengoErregistroa < filtrazioList.size()) {
				// Bakarrik oraingo orrialdeko erregistroak ateratzen ditu
				// Ateratzen du "Azpi-Lista" bat (zati bat) zerrenda osotik.
				List<AbisuakBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
				tableView.setItems(FXCollections.observableArrayList(pageData)); // Bistaratzen ditu taulan
			} else {
				tableView.setItems(FXCollections.observableArrayList()); // Ez dago daturik, taula ezer gabe
			}
		}
	/*
	 * -----------------------------------------------PAGINATION OROKORRA AMAIERA
	 * -------------------------------------------------------------------------
	 */
}
