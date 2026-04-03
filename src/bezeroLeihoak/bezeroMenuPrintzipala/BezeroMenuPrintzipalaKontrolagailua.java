package bezeroLeihoak.bezeroMenuPrintzipala;

import bezeroLeihoak.bezeroArreta.BezeroArreta;
import bezeroLeihoak.cpuTaula.CpuTaula;
import bezeroLeihoak.motherBoardTaula.MotherBoardTaula;
import bezeroLeihoak.otzara.Otzara;
import bezeroLeihoak.ramTaula.RamTaula;
import bezeroLeihoak.saioInformazioa.SaioInformazioa;
import bezeroLeihoak.storageTaula.StorageTaula;
import bezeroLeihoak.videoCardTaula.VideoCardTaula;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;

public class BezeroMenuPrintzipalaKontrolagailua extends HandlerGlobala {

	public BezeroMenuPrintzipalaKontrolagailua() {
	}

	@FXML
	public void irekiArreta() {
		try {
			BezeroArreta arreta = new BezeroArreta();
			Stage newStage = new Stage();
			arreta.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea arreta leihoa irekitzean: " + e.getMessage());
		}
	}

	@FXML
	public void irekiStorageTaula() {
		try {
			StorageTaula storageTaula = new StorageTaula();
			Stage newStage = new Stage();
			storageTaula.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea storage taula irekitzean: " + e.getMessage());
		}
	}

	@FXML
	public void irekiMotherBoardTaula() {
		try {
			MotherBoardTaula motherBoardTaula = new MotherBoardTaula();
			Stage newStage = new Stage();
			motherBoardTaula.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea mother board taula irekitzean: " + e.getMessage());
		}
	}

	@FXML
	public void irekiVideoCardTaula() {
		try {
			VideoCardTaula videoCardTaula = new VideoCardTaula();
			Stage newStage = new Stage();
			videoCardTaula.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea video card taula irekitzean: " + e.getMessage());
		}
	}

	@FXML
	public void irekiRamTaula() {
		try {
			RamTaula ramTaula = new RamTaula();
			Stage newStage = new Stage();
			ramTaula.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea ram taula irekitzean: " + e.getMessage());
		}
	}

	@FXML
	public void irekiCpuTaula() {
		try {
			CpuTaula cpuTaula = new CpuTaula();
			Stage newStage = new Stage();
			cpuTaula.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea cpu taula irekitzean: " + e.getMessage());
		}
	}

	@FXML
	public void irekiSaioarenInformazioa() {
		try {
			SaioInformazioa saioInformazioa = new SaioInformazioa();
			Stage newStage = new Stage();
			saioInformazioa.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea bezeroen saio informazioa irekitzean: " + e.getMessage());
		}
	}

	@FXML
	public void irekiOtzara() {
		try {
			Otzara otzara = new Otzara();
			Stage newStage = new Stage();
			otzara.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki", "Errorea otzara irekitzean: " + e.getMessage());
		}
	}

	@FXML
	public void saioaItxi() {
		itxiOraingoLeihoa();
		irekiLogina();
	}

}
