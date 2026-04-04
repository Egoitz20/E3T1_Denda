package bezeroLeihoak.ramTaula;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import kontrolagailuGlobala.HandlerGlobala;
import kontrolagailuGlobala.OtzaraGlobala;

public class RamTaulaKontrolagailua extends HandlerGlobala {

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

	// Hiztegi bat, produktu bakoitzerako (IDaren arabera) erabiltzaileak zenbat
	// unitate hautatu dituen jasotzen duena.
	// Adib: {1=3, 2=0, 3=5} → ID 1 produktua 3 unitate eramaten ditu, ID 2 eramaten
	// 0, ID 3 eramaten 5.
	private Map<Integer, Integer> kantitateak = new HashMap<>();

	public RamTaulaKontrolagailua() {
	}

	@FXML
	public void itzuli() {
		itxiOraingoLeihoa();
		irekiBezeroMenuPrintzipala();
	}

	@FXML
	public void initialize() {
		konfiguratuKategoriaZutabeak(izenaColumn, deskribapenaColumn, salneurriaColumn);
		kargatuKategoriaDatuak(ramList, filtrazioList, kantitateak, "RAM");
		konfiguratuBezeroKategoriaPaginazioa(pagination, filtrazioList, tableView);
		konfiguratuKantitateaZutabea();
	}

	@FXML
	public void gehituOtzara() {
		OtzaraGlobala otzara = OtzaraGlobala.getInstantzia();
	    
	    for (ProduktuBean produktu : ramList) {
	        int kantitatea = kantitateak.getOrDefault(produktu.getId(), 0);
	        if (kantitatea > 0) {
	            otzara.gehituProduktua(produktu, kantitatea);
	            // Resetear cantidad después de añadir
	            kantitateak.put(produktu.getId(), 0);
	        }
	    }
	    
	    // Refrescar la tabla para que los contadores vuelvan a 0
	    eguneratuBezeroKteagoriaOrriaDatuak(pagination.getCurrentPageIndex(), ramList, tableView);
	    
	    irekiAlerta("Arrakasta", "Produktuak otzaran gehitu dira", 
	                "Produktuak ondo gehitu dira zure otzaran.");
	}

	private void konfiguratuKantitateaZutabea() {
		// setCellFactory: zutabe honen gelaxkak sortzen dituen "fabrika" bat sortzen du
		kantitateaColumn.setCellFactory(new Callback<TableColumn<ProduktuBean, Void>, TableCell<ProduktuBean, Void>>() {
			@Override
			public TableCell<ProduktuBean, Void> call(TableColumn<ProduktuBean, Void> param) {
				return new TableCell<ProduktuBean, Void>() {

					// Gelaxka bakoitzaren barruan joango diren osagaiak sortzen dira
					private final Button btnKendu = new Button("-");
					private final Label lblKopurua = new Label("0");
					private final Button btnGehitu = new Button("+");
					private final HBox container = new HBox(10, btnKendu, lblKopurua, btnGehitu);

					{
						// Kontenedorea konfiguratzen da (HBOX)
						btnKendu.setPrefWidth(40); // Botoi - 40 pixelekoa 
						btnGehitu.setPrefWidth(40); // Botoi + 40 pixelekoa  
						lblKopurua.setPrefWidth(40); // Label 40 pixelekoa
						lblKopurua.setAlignment(Pos.CENTER); // Textu erdian
						lblKopurua.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
						container.setAlignment(Pos.CENTER); // Dena erdian

						// Botoi - sakatzen denean
						btnKendu.setOnAction(event -> {
							ProduktuBean produktu = getTableView().getItems().get(getIndex());
							if (produktu != null) {
								int id = produktu.getId();
								int current = kantitateak.getOrDefault(id, 0); // Oraingo kantitatea
								if (current > 0) {
									kantitateak.put(id, current - 1); // Kendu 1
									lblKopurua.setText(String.valueOf(current - 1)); // Textua refreskatu
								}
							}
						});

						// Botoi - sakatzen denean
						btnGehitu.setOnAction(event -> {
							ProduktuBean produktu = getTableView().getItems().get(getIndex());
							if (produktu != null) {
								int id = produktu.getId();
								int current = kantitateak.getOrDefault(id, 0); // Oraingo kantitatea
								kantitateak.put(id, current + 1); // Geitu 1
								lblKopurua.setText(String.valueOf(current + 1)); // Textua refreskatu
							}
						});
					}

					// Metodo hau gelaxka eguneratu behar den bakoitzean deitzen zaio
					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null); // Gelaxka hutsik -> Ez da ezer bistaratzen
						} else {
							// Gelxka datuekin -> Gordetutako kantitatea hartzen da eta bistaratzen dira botoiak
							ProduktuBean produktu = getTableView().getItems().get(getIndex());
							if (produktu != null) {
								int kantitatea = kantitateak.getOrDefault(produktu.getId(), 0);
								lblKopurua.setText(String.valueOf(kantitatea));
							}
							setGraphic(container); // Botoiak bistaratu
						}
					}
				};
			}
		});
	}

}