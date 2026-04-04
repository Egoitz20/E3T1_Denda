package saltzaileLeihoak.eskaerakTaula;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import datuBaseKonexioa.BezeroBean;
import datuBaseKonexioa.EskaerakBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;
import saltzaileLeihoak.bezeroTaulaEskaerak.BezeroTaulaEskaerak;
import saltzaileLeihoak.saltzaileMenuPrintzipala.SaltzaileMenuPrintzipala;

public class EskaerakTaulaKontrolagailua extends HandlerGlobala {

    // Taula
    @FXML
    private TableView<EskaerakBean> tableView;

    // Zutabeak
    @FXML
    private TableColumn<EskaerakBean, String> produktuIzenaColumn;
    
    @FXML
    private TableColumn<EskaerakBean, String> produktuKategoriaColumn;
    
    @FXML
    private TableColumn<EskaerakBean, Integer> produktuKopuruaColumn;
    
    @FXML
    private TableColumn<EskaerakBean, String> produktuEgoeraColumn;
    
    @FXML
    private TableColumn<EskaerakBean, Date> produktuEskaeraDataColumn;
    
    @FXML
    private TableColumn<EskaerakBean, String> saltzaileIzenaColumn;

    // Bilaketa
    @FXML
    private TextField bilaketaField;
    
    // Izenburua
    @FXML
    private Label bezeroIzenburua;

    // Paginazioa
    @FXML
    private Pagination pagination;

    // Datu listak
    private ObservableList<EskaerakBean> eskaerakList = FXCollections.observableArrayList();
    private ObservableList<EskaerakBean> filtrazioList = FXCollections.observableArrayList();

    private static final int ROWS_PER_PAGE = 10;
    private BezeroBean bezeroData;

    public EskaerakTaulaKontrolagailua() {

    }

    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
    		try {
    			BezeroTaulaEskaerak bezeroTaulaEskaerak = new BezeroTaulaEskaerak();
    			Stage newStage = new Stage();
    			bezeroTaulaEskaerak.start(newStage);

    			itxiOraingoLeihoa();

    		} catch (Exception e) {
    			e.printStackTrace();
    			irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
    					"Errorea saltzailearen menua irekitzean: " + e.getMessage());
    		}
    }

    @FXML
    public void initialize() {
        konfiguratuZutabeak();
        konfiguratuEskaerakPaginazioa(pagination, filtrazioList, tableView);
        konfiguratuBilaketa();
    }

    public void setEskaeraBezeroData(BezeroBean data) {
        this.bezeroData = data;
        
        // Actualizar el título con el nombre del cliente
        if (bezeroData != null) {
            String izenaOsoa = bezeroData.getIzena() + " " + bezeroData.getAbizena();
            bezeroIzenburua.setText(izenaOsoa + " - Eskaerak");
            kargatuDatuak();
        }
    }

    private void konfiguratuZutabeak() {
        produktuIzenaColumn.setCellValueFactory(new PropertyValueFactory<>("produktu"));
        produktuKategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        produktuKopuruaColumn.setCellValueFactory(new PropertyValueFactory<>("kopurua"));
        produktuEgoeraColumn.setCellValueFactory(new PropertyValueFactory<>("deskribapena"));
        produktuEskaeraDataColumn.setCellValueFactory(new PropertyValueFactory<>("eskaera_data"));
        saltzaileIzenaColumn.setCellValueFactory(new PropertyValueFactory<>("saltzailea"));
    }

    private void kargatuDatuak() {
        if (bezeroData != null) {
            ArrayList<EskaerakBean> eskaerak = jasoBezeroenProduktuEskaerak(
                bezeroData.getIzena(), 
                bezeroData.getAbizena()
            );
            eskaerakList.setAll(eskaerak);
            filtrazioList.setAll(eskaerakList);
            
            // Actualizar paginación después de cargar datos
            konfiguratuEskaerakPaginazioa(pagination, filtrazioList, tableView);
        }
    }

    private void konfiguratuBilaketa() {
        bilaketaField.textProperty().addListener((observable, oldValue, jasoBilatzekoTestua) -> {
            if (jasoBilatzekoTestua == null || jasoBilatzekoTestua.trim().isEmpty()) {
                filtrazioList.setAll(eskaerakList);
            } else {
                String aurkituIdatzitakoTestua = jasoBilatzekoTestua.toLowerCase().trim();

                List<EskaerakBean> filtrados = eskaerakList.stream()
                        .filter(e -> (e.getProduktu() != null && e.getProduktu().toLowerCase().contains(aurkituIdatzitakoTestua))
                                || (e.getKategoria() != null && e.getKategoria().toLowerCase().contains(aurkituIdatzitakoTestua))
                                || (e.getSaltzailea() != null && e.getSaltzailea().toLowerCase().contains(aurkituIdatzitakoTestua)))
                        .collect(Collectors.toList());

                filtrazioList.setAll(filtrados);
            }

            pagination.setPageCount((int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE));
            pagination.setCurrentPageIndex(0);
            eguneratuEskaerakOrriaDatuak(0, filtrazioList, tableView);
        });
    }
}