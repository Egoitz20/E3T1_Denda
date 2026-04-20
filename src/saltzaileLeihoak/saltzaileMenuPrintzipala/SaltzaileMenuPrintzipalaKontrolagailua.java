package saltzaileLeihoak.saltzaileMenuPrintzipala;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;
import saltzaileLeihoak.abisuak.Abisuak;
import saltzaileLeihoak.bezeroKudeaketa.BezeroKudeaketa;
import saltzaileLeihoak.bezeroTaula.BezeroTaula;
import saltzaileLeihoak.bezeroTaulaEskaerak.BezeroTaulaEskaerak;
import saltzaileLeihoak.eguneraketakKontrola.EguneraketakKontrola;
import saltzaileLeihoak.saltzaileKudeaketa.SaltzaileKudeaketa;
import saltzaileLeihoak.saltzaileTaula.SaltzaileTaula;
import saltzaileLeihoak.txertaketakEzabaketakKontrola.TxertaketakEzabaketakKontrola;

public class SaltzaileMenuPrintzipalaKontrolagailua extends HandlerGlobala {

	@FXML
	private Menu saltzaileMenu;

	private LangileSaltzaileBean saltzaileData; // Logeatutako saltzailearen datuak

	public SaltzaileMenuPrintzipalaKontrolagailua() {
	}

	// Leihotik saltzailearen datuak jasotzeko metodoa
	public void setSaltzaileData(LangileSaltzaileBean data) {
		this.saltzaileData = data;
		eguneratuMenuTestua(); // Menuaren testua eguneratzen du
	}

	// Saltzailearen ID eta Izena menu testua eguneraketa metodoa
	private void eguneratuMenuTestua() {
		if (saltzaileMenu != null && saltzaileData != null) {
			// ID eta izena osoa separatuta bistaratu
			String menuText = saltzaileData.getId() + " - " + saltzaileData.getLangileIzenaAbizena();
			saltzaileMenu.setText(menuText);
		}
	}

	// Saltzaile taula irekitzeko metodoa
	@FXML
	public void irekiSaltzaileTaula() {
		try {
			SaltzaileTaula saltzaileTaula = new SaltzaileTaula();
			Stage newStage = new Stage();
			saltzaileTaula.setSaltzaileData(saltzaileData);
			saltzaileTaula.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea saltzaileen taula irekitzean: " + e.getMessage());
		}
	}

	// Bezero taula irekitzeko metodoa
	@FXML
	public void irekiBezeroTaula() {
		try {
			BezeroTaula bezeroTaula = new BezeroTaula();
			Stage newStage = new Stage();
			bezeroTaula.setSaltzaileData(saltzaileData);
			bezeroTaula.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea bezeroen taula irekitzean: " + e.getMessage());
		}
	}

	// Bezero kudeaketa irekitzeko metodoa
	@FXML
	public void irekiBezeroKudeaketa() {
		try {
			BezeroKudeaketa bezeroKudeaketa = new BezeroKudeaketa();
			Stage newStage = new Stage();
			bezeroKudeaketa.start(newStage);
			bezeroKudeaketa.setSaltzaileData(saltzaileData);
			
			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea bezeroen taula irekitzean: " + e.getMessage());
		}
	}

	// Saltzaile kudeaketa irekitzeko metodoa
	@FXML
	public void irekiSaltzaileKudeaketa() {
		try {
			SaltzaileKudeaketa saltzaileKudeaketa = new SaltzaileKudeaketa();
			Stage newStage = new Stage();
			saltzaileKudeaketa.start(newStage);
			saltzaileKudeaketa.setSaltzaileData(saltzaileData);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea saltzaileen kudeaketa irekitzean: " + e.getMessage());
		}
	}

	// Bezero taula eskaerak irekitzeko metodoa
	@FXML
	public void irekiBezeroTaulaEskaerak() {
		try {
			BezeroTaulaEskaerak bezeroTaulaEskaerak = new BezeroTaulaEskaerak();
			Stage newStage = new Stage();
			bezeroTaulaEskaerak.start(newStage);
			bezeroTaulaEskaerak.setSaltzaileData(saltzaileData);
			
			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea bezeroen taula eskaerak irekitzean: " + e.getMessage());
		}
	}

	// Abisuak irekitzeko metodoa
	@FXML
	public void irekiAbisuak() {
		try {
			Abisuak abisuak = new Abisuak();
			Stage newStage = new Stage();
			abisuak.start(newStage);
			abisuak.setSaltzaileData(saltzaileData);
			
			itxiOraingoLeihoa();
		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea abisuak irekitzean: " + e.getMessage());
		}
	}

	@FXML
	public void irekiEguneraketak() {
		try {
			EguneraketakKontrola eguneraketaKontrola = new EguneraketakKontrola();
			Stage newStage = new Stage();
			eguneraketaKontrola.start(newStage);
			eguneraketaKontrola.setSaltzaileData(saltzaileData);
			
			itxiOraingoLeihoa();
		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea eguneraketak kontrola irekitzean: " + e.getMessage());
		}
	}

	@FXML
	public void irekiTxerteketakEzabaketak() {
		try {
			TxertaketakEzabaketakKontrola txertatuEzabatuKontrola = new TxertaketakEzabaketakKontrola();
			Stage newStage = new Stage();
			txertatuEzabatuKontrola.start(newStage);
			txertatuEzabatuKontrola.setSaltzaileData(saltzaileData);
			
			itxiOraingoLeihoa();
		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea txertaketak eta ezabaketak kontrola irekitzean: " + e.getMessage());
		}

	}

	// Saioa ixteko metodoa
	@FXML
	public void saioaIxten() {
		// Oraingo leihoa ixten da
		itxiOraingoLeihoa();
		// Sailtzaileko datuak garbitu
		setSaltzaileData(null);
		irekiLogina();
	}
}