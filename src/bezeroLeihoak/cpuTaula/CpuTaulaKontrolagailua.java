package bezeroLeihoak.cpuTaula;

import java.util.HashMap;
import java.util.Map;

import datuBaseKonexioa.ProduktuBean;
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
import kontrolagailuGlobala.OtzaraGlobala;

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
	private ObservableList<ProduktuBean> cpuList = FXCollections.observableArrayList();
	private ObservableList<ProduktuBean> filtrazioList = FXCollections.observableArrayList();

	// Mapa para almacenar las cantidades seleccionadas
	private Map<Integer, Integer> kantitateak = new HashMap<>();

	public CpuTaulaKontrolagailua() {
	}
	
	@FXML
	public void itzuli() {
		itxiOraingoLeihoa();
		irekiBezeroMenuPrintzipala();
	}
	
	@FXML
	public void initialize() {
		konfiguratuKategoriaZutabeak(izenaColumn, deskribapenaColumn, salneurriaColumn);
		kargatuKategoriaDatuak(cpuList, filtrazioList, kantitateak, "CPU");
		konfiguratuBezeroKategoriaPaginazioa(pagination, filtrazioList, tableView);
		konfiguratuKantitateaZutabea();
	}

	@FXML
	public void gehituOtzara() {
OtzaraGlobala otzara = OtzaraGlobala.getInstantzia();
	    
	    for (ProduktuBean produktu : cpuList) {
	        int kantitatea = kantitateak.getOrDefault(produktu.getId(), 0);
	        if (kantitatea > 0) {
	            otzara.gehituProduktua(produktu, kantitatea);
	            // Resetear cantidad después de añadir
	            kantitateak.put(produktu.getId(), 0);
	        }
	    }
	    
	    // Refrescar la tabla para que los contadores vuelvan a 0
	    eguneratuBezeroKteagoriaOrriaDatuak(pagination.getCurrentPageIndex(), cpuList, tableView);
	    
	    irekiAlerta("Arrakasta", "Produktuak otzaran gehitu dira", 
	                "Produktuak ondo gehitu dira zure otzaran.");
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

						btnKendu.setOnAction(_ -> {
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

						btnGehitu.setOnAction(_ -> {
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
