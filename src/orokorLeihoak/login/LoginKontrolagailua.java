package orokorLeihoak.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datuBaseKonexioa.BezeroBean;
import datuBaseKonexioa.Konexioa;
import datuBaseKonexioa.SaltzaileBean;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import orokorLeihoak.erregistratu.Erregistratu;
import partekatutakoMetodoak.ErrepikatutakoMetodoak;

public class LoginKontrolagailua extends ErrepikatutakoMetodoak {

	private static final String SALTZAILE_TAULA = "SELECT * FROM SALTZAILE";
	private static final String BEZERO_TAULA = "SELECT * FROM BEZERO";

	@FXML
	private TextField erabiltzaileak;

	@FXML
	private PasswordField pasahitza;

	public LoginKontrolagailua() {
	}

	// 'Sartu' botoia sakatzen denean metodoa.
	@FXML
	public void sartu() {

		String jasotakoErabiltzailea = erabiltzaileak.getText();
		String jasotakoPasahitza = pasahitza.getText();

		// Kampoak hutsik ez dagoela konprobatuko dira
		if (jasotakoErabiltzailea.isEmpty() || jasotakoPasahitza.isEmpty()) {

			irekiAlerta("Errorea", "Erabiltzailea eta pasahitza bete behar dira",
					"Mesedez, bete erabiltzailea eta pasahitza.");
			return;
		}

		// Lehenengo, saltzaile bada konprobatuko da.
		if (konprobatuSaltzailea(jasotakoErabiltzailea, jasotakoPasahitza))
			irekiSaltzaileMenuPrintzipala();

		// Ez bada, bezeroa bada konprobatuko da.
		else if (konprobatuBezeroa(jasotakoErabiltzailea, jasotakoPasahitza))
			irekiBezeroMenuPrintzipala();
		
		// Biak ez badira, salbuespen bat jasoko da.
		else
			irekiAlerta("Errorea", "Erabiltzailea edo pasahitza okerra",
					"Sartutako erabiltzailea edo pasahitza ez da zuzena.");
	};

	// 'Erregistratu' botoia sakatzen denean metodoa
	@FXML
	public void erregistratu() {
		irekiErregistratu();
	};

	// Saltzailea konprobatzeko metodoa
	private boolean konprobatuSaltzailea(String erabiltzailea, String pasahitza) {
		ArrayList<SaltzaileBean> saltzaileak = jasoSaltzaileak();

		for (SaltzaileBean saltzaile : saltzaileak) {
			if (saltzaile.getErabiltzailea().equals(erabiltzailea) && saltzaile.getPasahitza().equals(pasahitza)) {
				return true;
			}
		}
		return false;
	}

	// Bezero konprobatzeko metodoa
	private boolean konprobatuBezeroa(String emaila, String izena) {
		ArrayList<BezeroBean> bezeroak = jasoBezeroak();

		for (BezeroBean bezero : bezeroak) {
			if (bezero.getEmaila().equals(emaila) && bezero.getIzena().equals(izena)) {
				return true;
			}
		}
		return false;
	};

	private void irekiErregistratu() {
		try {
			Erregistratu erregitratuLeihoa = new Erregistratu();
			Stage newStage = new Stage();

			erregitratuLeihoa.start(newStage);

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea bezeroaren menua irekitzean: " + e.getMessage());
		}
	}

	// Saltzaileak datu-basetik jasotzeko metodoa
	private ArrayList<SaltzaileBean> jasoSaltzaileak() {

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

	// Bezeroak datu-basetik jasotzeko metodoa
	private ArrayList<BezeroBean> jasoBezeroak() {

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

}
