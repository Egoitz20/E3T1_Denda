package saltzaileLeihoak.bezeroTaulaEskaerak;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import datuBaseKonexioa.BezeroBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import kontrolagailuGlobala.HandlerGlobala;
import saltzaileLeihoak.bezeroKudeaketa.BezeroKudeaketa;
import saltzaileLeihoak.eskaerakTaula.EskaerakTaula;
import javafx.beans.property.SimpleStringProperty;

public class BezeroTaulaEskaerakKontrolagailua extends HandlerGlobala {

	// Taula
	@FXML
	private TableView<BezeroBean> tableView;

	// Zutabeak
	@FXML
	private TableColumn<BezeroBean, String> izenaAbizenaColumn;

	// Botoiak izango dituen ekintza-zutabea. Void esan nahi du ez dituela bean
	// datuak erakusten, osagai grafikoak bakarrik.
	@FXML
	private TableColumn<BezeroBean, Void> actionColumn;

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

	public BezeroTaulaEskaerakKontrolagailua() {
	}

	@FXML
	public void itzuli() {
		itxiOraingoLeihoa();
		irekiSaltzaileMenuPrintzipala();
	}

	@FXML
	public void initialize() {
		konfiguratuZutabeak(); // Nola bisratzen dira zutabeak konfiguratzen du
		kargatuDatuak(); // Bezeroak datu basetik kargatzen du
		konfiguratuPaginazioa(); // Pagination botoiak konfiguratzen du
		konfiguratuBilaketa(); // Bilaketa konfiguratzen du
		konfiguratuEkintzaZutabea(); // Errenkadari botoiak gehitzen du
	}

	// Izenen zutabea, elkarrekin izena + abizena erakuts konfiguratzen du
	private void konfiguratuZutabeak() {

		izenaAbizenaColumn.setCellValueFactory(cellData -> {
			BezeroBean bezero = cellData.getValue(); // Oriango errenkadaren bezeroa jasotzen du
			String izenaOsoa = bezero.getIzena() + " " + bezero.getAbizena(); // Izena + Abizena konkatenatzen du
			return new SimpleStringProperty(izenaOsoa); // Testua "behagarria" izan bildutzen du

		});
	}

	private void konfiguratuEkintzaZutabea() {
		// Gelaxka pertsonalizatuen fabrika bat ekintza-zutaberako sortzen du.

		actionColumn.setCellFactory(new Callback<TableColumn<BezeroBean, Void>, TableCell<BezeroBean, Void>>() {
			@Override
			public TableCell<BezeroBean, Void> call(TableColumn<BezeroBean, Void> param) {
				return new TableCell<BezeroBean, Void>() {

					// Botoi bat "Ikusi eskaerak" testuarekin sortzen du. "final" baita ez du
					// aldatuko.

					private final Button btnIkusiEskaerak = new Button("Ikusi Eskaerak");

					{
						btnIkusiEskaerak.setOnAction(event -> { // Klick emanez gero, zer gertatuko den definitzen du.
							BezeroBean bezero = getTableView().getItems().get(getIndex()); // Non dagoen botoia,
																							// errenkadaren bezeroa
																							// jasotzen du
							irekiEskaerakTaula(bezero); // Eskaera leihoa irekitzen du bezero horrekin
						});
					}

					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty); // Gelaxka eguneratu behar den bakoitzean erabiltzen den metodoa.
						if (empty) {
							setGraphic(null); // Gelaxka hutsik badago, ez du ezer erakusten
						} else {
							setGraphic(btnIkusiEskaerak); // Bistaratzen du botoia datuak egoten badira
						}
					}
				};
			}
		});
	}

	private void irekiEskaerakTaula(BezeroBean bezero) {
		try {
			EskaerakTaula eskaerak = new EskaerakTaula();
			Stage newStage = new Stage();

			// Pasar los datos a la nueva ventana
			eskaerak.setEskaeraBezeroData(bezero);
			eskaerak.start(newStage);

			itxiOraingoLeihoa();

		} catch (Exception e) {
			e.printStackTrace();
			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
					"Errorea bezeroaren eskaerak irekitzean: " + e.getMessage());
		}

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
						|| (b.getEmaila() != null && b.getEmaila().toLowerCase().contains(aurkituIdatzitakoTestua)))
						.collect(Collectors.toList());

				filtrazioList.setAll(filtrados);
			}

			pagination.setPageCount((int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE));
			pagination.setCurrentPageIndex(0);
			eguneratuOrriaDatuak(0);
		});
	}

}
