package bezeroLeihoak.cpuTaula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datuBaseKonexioa.ProduktuBean;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import kontrolagailuGlobala.HandlerGlobala;

public class CpuTaulaKontrolagailua extends HandlerGlobala {

	// Taula
	@FXML
	private TableView<ProduktuBean> tableView;

	// Zutabeak
	@FXML
	private TableColumn<ProduktuBean, String> izenaColumn;

	@FXML
	private TableColumn<ProduktuBean, String> deskribapenaColumn;

	@FXML
	private TableColumn<ProduktuBean, Double> salneurriaColumn;

	@FXML
	private TableColumn<ProduktuBean, Void> kantitateaColumn;

	// Paginazioa
	@FXML
	private Pagination pagination;

	// Datu listak
	private ObservableList<ProduktuBean> ramList = FXCollections.observableArrayList();
	private ObservableList<ProduktuBean> filtrazioList = FXCollections.observableArrayList();

	// Mapa para almacenar las cantidades seleccionadas
	private Map<Integer, Integer> kantitateak = new HashMap<>();

	private static final int ROWS_PER_PAGE = 10;

	public CpuTaulaKontrolagailua() {
	}
	
	@FXML
	public void itzuli() {
		itxiOraingoLeihoa();
		irekiBezeroMenuPrintzipala();
	}
	
	@FXML
	public void initialize() {
		konfiguratuZutabeak();
		kargatuDatuak();
		konfiguratuPaginazioa();
		konfiguratuKantitateaZutabea();
	}
	
	private void kargatuDatuak() {
		// Cargar productos de la categoría CPU
		ArrayList<ProduktuBean> cpuProduktuak = jasoProduktuak("CPU");
		ramList.setAll(cpuProduktuak);
		filtrazioList.setAll(ramList);

		// Inicializar el mapa de cantidades
		for (ProduktuBean produktu : ramList) {
			kantitateak.putIfAbsent(produktu.getId(), 0);
		}
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
			List<ProduktuBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
			tableView.setItems(FXCollections.observableArrayList(pageData));
		} else {
			tableView.setItems(FXCollections.observableArrayList());
		}
	}

	@FXML
	public void gehituOtzara() {
		// Recoger todos los productos con cantidad > 0
		Map<ProduktuBean, Integer> otzarakoProduktuak = new HashMap<>();

		for (ProduktuBean produktu : ramList) {
			int kantitatea = kantitateak.getOrDefault(produktu.getId(), 0);
			if (kantitatea > 0) {
				otzarakoProduktuak.put(produktu, kantitatea);
			}
		}

		if (otzarakoProduktuak.isEmpty()) {
			irekiAlerta("Informazioa", "Otzara hutsik", "Ez duzu produkturik aukeratu.");
			return;
		}

		// TODO: Aquí llamarías al método para añadir los productos a la cesta
		// Por ahora mostramos un mensaje
		StringBuilder mezua = new StringBuilder("Otzaran gehitu diren produktuak:\n");
		for (Map.Entry<ProduktuBean, Integer> entry : otzarakoProduktuak.entrySet()) {
			mezua.append("- ").append(entry.getKey().getIzena()).append(": ").append(entry.getValue())
					.append(" unitate\n");
		}

		irekiAlerta("Arrakasta", "Produktuak otzaran gehitu dira", mezua.toString());
	}


	private void konfiguratuZutabeak() {
		izenaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIzena()));

		deskribapenaColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDeskribapena()));

		salneurriaColumn.setCellValueFactory(
				cellData -> new SimpleDoubleProperty(cellData.getValue().getSalneurria()).asObject());

		// Formatear el precio con 2 decimales
		salneurriaColumn.setCellFactory(column -> new TableCell<ProduktuBean, Double>() {
			@Override
			protected void updateItem(Double item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(String.format("%.2f €", item));
				}
			}
		});
	}

	private void konfiguratuKantitateaZutabea() {
		kantitateaColumn.setCellFactory(new Callback<TableColumn<ProduktuBean, Void>, TableCell<ProduktuBean, Void>>() {
			@Override
			public TableCell<ProduktuBean, Void> call(TableColumn<ProduktuBean, Void> param) {
				return new TableCell<ProduktuBean, Void>() {

					private final Button btnKendu = new Button("-");
					private final Label lblKopurua = new Label("0");
					private final Button btnGehitu = new Button("+");
					private final HBox container = new HBox(10, btnKendu, lblKopurua, btnGehitu);

					{
						// Estilos
						btnKendu.setPrefWidth(40);
						btnGehitu.setPrefWidth(40);
						lblKopurua.setPrefWidth(40);
						lblKopurua.setAlignment(Pos.CENTER);
						lblKopurua.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
						container.setAlignment(Pos.CENTER);

						btnKendu.setOnAction(event -> {
							ProduktuBean produktu = getTableView().getItems().get(getIndex());
							if (produktu != null) {
								int id = produktu.getId();
								int current = kantitateak.getOrDefault(id, 0);
								if (current > 0) {
									kantitateak.put(id, current - 1);
									lblKopurua.setText(String.valueOf(current - 1));
								}
							}
						});

						btnGehitu.setOnAction(event -> {
							ProduktuBean produktu = getTableView().getItems().get(getIndex());
							if (produktu != null) {
								int id = produktu.getId();
								int current = kantitateak.getOrDefault(id, 0);
								kantitateak.put(id, current + 1);
								lblKopurua.setText(String.valueOf(current + 1));
							}
						});
					}

					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							// Recuperar la cantidad guardada para este producto
							ProduktuBean produktu = getTableView().getItems().get(getIndex());
							if (produktu != null) {
								int kantitatea = kantitateak.getOrDefault(produktu.getId(), 0);
								lblKopurua.setText(String.valueOf(kantitatea));
							}
							setGraphic(container);
						}
					}
				};
			}
		});
	}

}
