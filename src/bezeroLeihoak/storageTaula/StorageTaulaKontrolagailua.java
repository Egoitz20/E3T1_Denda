package bezeroLeihoak.storageTaula;

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

public class StorageTaulaKontrolagailua extends HandlerGlobala {

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
	private ObservableList<ProduktuBean> storageList = FXCollections.observableArrayList();
	private ObservableList<ProduktuBean> filtrazioList = FXCollections.observableArrayList();

	// Mapa para almacenar las cantidades seleccionadas
	private Map<Integer, Integer> kantitateak = new HashMap<>();

	public StorageTaulaKontrolagailua() {
	}

	@FXML
	public void itzuli() {
		itxiOraingoLeihoa();
		irekiBezeroMenuPrintzipala();
	}

	@FXML
	public void initialize() {
		konfiguratuKategoriaZutabeak(izenaColumn, deskribapenaColumn, salneurriaColumn);
		kargatuKategoriaDatuak(storageList, filtrazioList, kantitateak, "Storage");
		konfiguratuBezeroKategoriaPaginazioa(pagination, filtrazioList, tableView);
		konfiguratuKantitateaZutabea();
	}

	@FXML
	public void gehituOtzara() {
		// Recoger todos los productos con cantidad > 0
		Map<ProduktuBean, Integer> otzarakoProduktuak = new HashMap<>();

		for (ProduktuBean produktu : storageList) {
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
