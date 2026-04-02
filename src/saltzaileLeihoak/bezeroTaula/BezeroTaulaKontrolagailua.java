package saltzaileLeihoak.bezeroTaula;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import datuBaseKonexioa.BezeroBean;
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
import saltzaileLeihoak.bezeroKudeaketa.BezeroKudeaketa;

public class BezeroTaulaKontrolagailua extends HandlerGlobala {

	// Taula
	@FXML
	private TableView<BezeroBean> tableView;

	// Zutabeak
	@FXML
	private TableColumn<BezeroBean, String> izenaColumn;

	@FXML
	private TableColumn<BezeroBean, String> emailaColumn;

	@FXML
	private TableColumn<BezeroBean, String> helbideaColumn;

	// Bilaketa
	@FXML
	private TextField bilaketaField;

	// Paginazioa
	@FXML
	private Pagination pagination;

	// Datu listak
	private ObservableList<BezeroBean> bezeroList = FXCollections.observableArrayList();
	private ObservableList<BezeroBean> filtrazioList = FXCollections.observableArrayList();

	private static final int ROWS_PER_PAGE = 10;

	@FXML
	public void itzuli() {
		itxiOraingoLeihoa();
		irekiSaltzaileMenuPrintzipala();
	}

	@FXML
	public void initialize() {
		konfiguratuZutabeak();
		kargatuDatuak();
		konfiguratuPaginazioa();
		konfiguratuBilaketa();
		
		tableView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 1) { // Un solo clic
				BezeroBean selectedItem = tableView.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					irekiBezeroKudeaketa(selectedItem);
				}
			}
		});
	}
	
	private void irekiBezeroKudeaketa(BezeroBean saltzailea) {
		try {
			BezeroKudeaketa kudeaketa = new BezeroKudeaketa();
			Stage newStage = new Stage();

			// Pasar los datos a la nueva ventana
			kudeaketa.setBezeroData(saltzailea);
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

	private void konfiguratuZutabeak() {
		izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));
		emailaColumn.setCellValueFactory(new PropertyValueFactory<>("emaila"));
		helbideaColumn.setCellValueFactory(new PropertyValueFactory<>("helbidea"));
	}

	private void kargatuDatuak() {
		ArrayList<BezeroBean> bezeroak = jasoBezeroak();
		bezeroList.setAll(bezeroak);
		filtrazioList.setAll(bezeroList);
	}

	private void konfiguratuPaginazioa() {
		int orriGeiketa = (int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE);
		pagination.setPageCount(orriGeiketa == 0 ? 1 : orriGeiketa);
		pagination.setCurrentPageIndex(0);

		pagination.currentPageIndexProperty().addListener((obs, oldIndex, indexBerria) -> {
			eguneratuOrriaDatuak(indexBerria.intValue());
		});

		eguneratuOrriaDatuak(0);
	}

	private void eguneratuOrriaDatuak(int indexOrria) {
		int lehengoErregistroa = indexOrria * ROWS_PER_PAGE;
		int azkenErregistroa = Math.min(lehengoErregistroa + ROWS_PER_PAGE, filtrazioList.size());

		if (lehengoErregistroa < filtrazioList.size()) {
			List<BezeroBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
			tableView.setItems(FXCollections.observableArrayList(pageData));
		} else {
			tableView.setItems(FXCollections.observableArrayList());
		}
	}

	private void konfiguratuBilaketa() {
		bilaketaField.textProperty().addListener((observable, oldValue, jasoBilatzekoTestua) -> {
			if (jasoBilatzekoTestua == null || jasoBilatzekoTestua.trim().isEmpty()) {
				filtrazioList.setAll(bezeroList);
			} else {
				String aurkituIdatzitakoTestua = jasoBilatzekoTestua.toLowerCase().trim();

				List<BezeroBean> filtrados = bezeroList.stream().filter(b -> (b.getIzena() != null
						&& b.getIzena().toLowerCase().contains(aurkituIdatzitakoTestua))
						|| (b.getEmaila() != null && b.getEmaila().toLowerCase().contains(aurkituIdatzitakoTestua))
						|| (b.getHelbidea() != null && b.getHelbidea().toLowerCase().contains(aurkituIdatzitakoTestua)))
						.collect(Collectors.toList());

				filtrazioList.setAll(filtrados);
			}

			pagination.setPageCount((int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE));
			pagination.setCurrentPageIndex(0);
			eguneratuOrriaDatuak(0);
		});
	}
}