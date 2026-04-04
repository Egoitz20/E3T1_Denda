package saltzaileLeihoak.saltzaileTaula;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import datuBaseKonexioa.LangileSaltzaileBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;
import saltzaileLeihoak.saltzaileKudeaketa.SaltzaileKudeaketa;

public class SaltzaileTaulaKontrolagailua extends HandlerGlobala {

	// Taula
	@FXML
	private TableView<LangileSaltzaileBean> tableView;

	// Zutabeak
	@FXML
	private TableColumn<LangileSaltzaileBean, Integer> idColumn;

	@FXML
	private TableColumn<LangileSaltzaileBean, String> izenaAbizenakColumn;

	@FXML
	private TableColumn<LangileSaltzaileBean, String> erabiltzaileaColumn;

	@FXML
	private TableColumn<LangileSaltzaileBean, String> pasahitzaColumn;

	@FXML
	private TableColumn<LangileSaltzaileBean, String> emailaColumn;

	@FXML
	private TableColumn<LangileSaltzaileBean, String> telefonoaColumn;

	// Erabiltzaileak bilatzeko idazten duen testu-eremua.
	@FXML
	private TextField bilaketaField;

	// Orrikatze-kontrola botoiekin (1, 2, 3, aurrekoa, hurrengoa).
	@FXML
	private Pagination pagination;

	// Datu-basean kargatutako datu GUZTIAK gordetzen ditu.
	// Zerrenda aldatzen denean, entzuten duen edozein osagai (taula bezala)
	// automatikoki eguneratzen da.
	private ObservableList<LangileSaltzaileBean> saltzaileList = FXCollections.observableArrayList();

	// Filtrazio bilaketa aplikatu ondoren biltegiratzen ditu datuak.
	private ObservableList<LangileSaltzaileBean> filtrazioList = FXCollections.observableArrayList();

	// Orriko zenbat erregistro agertzen diren definitzen du
	private static final int ROWS_PER_PAGE = 10;

	@FXML
	public void itzuli() {
		itxiOraingoLeihoa();
		irekiSaltzaileMenuPrintzipala();
	}

	// Metodo hau AUTOMATIKOKI exekutatzen da FXML kargatzen denean.
	// Dena konfiguratzeko sarrera-puntua da.
	@FXML
	public void initialize() {
		konfiguratuZutabeak(); // 1. Konfiguratu zutabe bakoitzean zer datu doan
		kargatuDatuak(); // 2. Datu-baseko datuak kargatzen ditu
		konfiguratuSaltzailePaginazioa(pagination, filtrazioList, tableView); // 3. Konfiguratu orri-botoiak
		konfiguratuBilaketa(); // 4. Bilaketa-eremua konfiguratzen du

		tableView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 1) { // Un solo clic
				LangileSaltzaileBean selectedItem = tableView.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					irekiSaltzaileKudeaketa(selectedItem);
				}
			}
		});
	}

	private void irekiSaltzaileKudeaketa(LangileSaltzaileBean saltzailea) {
		try {
			SaltzaileKudeaketa kudeaketa = new SaltzaileKudeaketa();
			Stage newStage = new Stage();

			// Pasar los datos a la nueva ventana
			kudeaketa.setSaltzaileData(saltzailea);
			kudeaketa.start(newStage);

			itxiOraingoLeihoa();

			// Cuando se cierre la ventana de gestión, recargar los datos
			newStage.setOnHidden(event -> {
				irekiSaltzaileMenuPrintzipala();
			});

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea saltzailearen kudeaketa irekitzean: " + e.getMessage());
		}
	}

	// Konekta ezazu zutabe bakoitza LangileSaltzaileBean objektuaren atributu
	// espezifiko batekin
	private void konfiguratuZutabeak() {
		// "new PropertyValueFactory < > (" id ")" bilatu automatikoki klasean "getId
		// ()" metodoa.
		// Horrela, ID zutabeko gelaxka bakoitzak "getId ()" balioa erakutsiko du.
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		izenaAbizenakColumn.setCellValueFactory(new PropertyValueFactory<>("langileIzenaAbizena"));
		erabiltzaileaColumn.setCellValueFactory(new PropertyValueFactory<>("erabiltzailea"));
		pasahitzaColumn.setCellValueFactory(new PropertyValueFactory<>("pasahitza"));
		emailaColumn.setCellValueFactory(new PropertyValueFactory<>("emaila"));
		telefonoaColumn.setCellValueFactory(new PropertyValueFactory<>("telefonoa"));
	}

	// Datu-Basetik datuak lortzen ditu
	private void kargatuDatuak() {
		ArrayList<LangileSaltzaileBean> saltzaileak = jasoLangileSaltzaileak();

		// "setAll()" "ObservableList"-eko metodo bat da,
		// zerrendako eduki GUZTIA ordezkatzen duena pasatzen dizkiozun elementuekin.

		// ArrayList-eko forma (setAll gabe)
		// saltzaileList.clear(); -> Lista husten du
		// saltzaileList.addAll(saltzaileak); -> Elementu berriak geitzen ditu

		// Con setAll (Linea batean)
		// saltzaileList.setAll(saltzaileak); -> clear() + addAll() egiten du pausu
		// batean

		// Adibide praktikoa:
		// Demagun saltzaileList daukadala [A, B, C]
		// saltzaileList.setAll([X, Y, Z]);
		// EMAITZA: saltzaileList orain dauka [X, Y, Z]
		// Antzinakoak [A, B, C] desagertu egiten dira

		saltzaileList.setAll(saltzaileak); // Datu DENAK saltzaileList gordetzen dira
		filtrazioList.setAll(saltzaileList); // Hasieran, filtrazioa = denak
	}

	// Erabiltzaileak bilaketa-eremuan idazten duena entzuten du.
	private void konfiguratuBilaketa() {
		// Erabiltzaileak idazten duenean, datuak iragazten ditu
		bilaketaField.textProperty().addListener((observable, oldValue, jasoBilatzekoTestua) -> {
			if (jasoBilatzekoTestua == null || jasoBilatzekoTestua.trim().isEmpty()) {
				// Eremua ezer gabe baldin badago, datu denak bistaratu
				filtrazioList.setAll(saltzaileList);
			} else {
				String aurkituIdatzitakoTestua = jasoBilatzekoTestua.toLowerCase().trim();

				// Bilatu 3 eremutan: izena, erabiltzailea eta ID

				// saltzaileList.stream -> Zerrenda datuen "fluxu" (stream) bihurtzen du.
				// Elementuak banan banan prozetazen dira
				// .filter(s -> ...) -> Fluxuko elementu bakoitza aztertzen du, eta baldintza
				// betetzen dutenei bakarrik uzten die pasatzen.
				// "s" -> elementu indibiduala da (LangileSaltzaileBean bat)
				// "->" -> "halako" edo "non"
				// Barruan: "..." -> Beteko behar den baldintza

				List<LangileSaltzaileBean> filtrados = saltzaileList.stream()
						.filter(s -> s.getLangileIzenaAbizena() != null // Izena ez da null?
								&& s.getLangileIzenaAbizena().toLowerCase().contains(aurkituIdatzitakoTestua) // ETA
																												// izena
																												// dauka
																												// bilatutakoa?
								// Adibide:
								// s.getIzenaAbizena() = "Poppy Jordan"
								// searchLower = "pop"
								// "poppy jordan".contains("pop") = TRUE

								|| s.getErabiltzailea() != null // EDO erabiltzailea ez da null?
										&& s.getErabiltzailea().toLowerCase().contains(aurkituIdatzitakoTestua) // ETA
																												// erabiltzailea
																												// dauka
																												// bilatutakoa?
				// Adibide:
				// s.getErabiltzailea() = "pjordan"
				// searchLower = "jor"
				// "pjordan".contains("jor") = TRUE

								|| String.valueOf(s.getId()).contains(aurkituIdatzitakoTestua)) // ID (int)
																								// testu(String)
																								// bihurtzen du, eta
																								// bilatzen den testua
																								// daukan bilatzen du.
						// Adibide:
						// s.getId() = 51
						// String.valueOf(51) = "51"
						// searchLower = "5"
						// "51".contains("5") = TRUE

						.collect(Collectors.toList()); // Filtrazioa pasatu zuten elementu guztiak jaso eta zerrenda
														// berri batean sartzen ditu.
				filtrazioList.setAll(filtrados); // Ordezkatu "filtrazioList" eduki GUZTIA bilaketaren emaitzekin.

			}

			// Berriro kalkulatu zenbat orrialde behar diren datu berriak iragazita, eta
			// konfiguratu orrikatzeko botoiak.
			pagination.setPageCount((int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE));
			pagination.setCurrentPageIndex(0);

			// Taulan erakusten ditu lehen orriko datuak.
			// Datu berriekin "filtrazioList" eguneratu eta orrikatzea konfiguratu dugun
			// arren,
			// taulak datu zaharrak erakusten jarraitzen du.
			// Lerro hori da taula benetan eguneratzen duena.
			eguneratuSaltzaileOrriaDatuak(0, filtrazioList, tableView); // Datu berriak biztaratzen dugu
		});
	}
}