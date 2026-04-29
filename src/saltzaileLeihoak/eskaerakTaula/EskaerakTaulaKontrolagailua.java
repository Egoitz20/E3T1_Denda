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

/**
 * Bezeroaren eskaeren taula leihoaren kontrolagailua.
 * <p>
 * Klase honek bezero jakin baten eskaera guztiak erakusten ditu taula batean,
 * bilaketa funtzionalitatea eta orrikatzea ditu.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class EskaerakTaulaKontrolagailua extends HandlerGlobala {

    // Taula
    @FXML private TableView<EskaerakBean> tableView;

    // Zutabeak
    @FXML private TableColumn<EskaerakBean, String> produktuIzenaColumn;
    @FXML private TableColumn<EskaerakBean, String> produktuKategoriaColumn;
    @FXML private TableColumn<EskaerakBean, Integer> produktuKopuruaColumn;
    @FXML private TableColumn<EskaerakBean, String> produktuEgoeraColumn;
    @FXML private TableColumn<EskaerakBean, Date> produktuEskaeraDataColumn;
    @FXML private TableColumn<EskaerakBean, String> saltzaileIzenaColumn;

    // Bilaketa
    @FXML private TextField bilaketaField;
    
    // Izenburua
    @FXML private Label bezeroIzenburua;

    // Paginazioa
    @FXML private Pagination pagination;

    /** Datu-baseko eskaera guztiak gordetzeko zerrenda */
    private ObservableList<EskaerakBean> eskaerakList = FXCollections.observableArrayList();
    
    /** Bilaketaren emaitzak gordetzeko zerrenda */
    private ObservableList<EskaerakBean> filtrazioList = FXCollections.observableArrayList();

    /** Orriko erregistro kopurua */
    private static final int ROWS_PER_PAGE = 10;
    
    /** Eskaerak erakutsi beharreko bezeroaren datuak */
    private BezeroBean bezeroData;

    /**
     * Eraikitzaile lehenetsia.
     */
    public EskaerakTaulaKontrolagailua() {
    }

    /**
     * Itzuli botoiaren metodoa.
     * <p>
     * Leihoa ixten du eta bezero eskaeren taula nagusira itzultzen da.
     * </p>
     */
    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
        try {
            BezeroTaulaEskaerak bezeroTaulaEskaerak = new BezeroTaulaEskaerak();
            Stage newStage = new Stage();
            bezeroTaulaEskaerak.start(newStage);
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea bezeroen eskaera taula irekitzean: " + e.getMessage());
        }
    }

    /**
     * FXMLa kargatzean automatikoki exekutatzen den metodoa.
     * <p>
     * Taula konfiguratzen du, orrikatzea eta bilaketa konfiguratzen ditu.
     * </p>
     */
    @FXML
    public void initialize() {
        konfiguratuZutabeak();
        konfiguratuEskaerakPaginazioa(pagination, filtrazioList, tableView);
        konfiguratuBilaketa();
    }

    /**
     * Eskaerak erakutsi beharreko bezeroaren datuak ezartzen ditu.
     *
     * @param data bezeroaren datuak
     */
    public void setEskaeraBezeroData(BezeroBean data) {
        this.bezeroData = data;
        
        if (bezeroData != null) {
            String izenaOsoa = bezeroData.getIzena() + " " + bezeroData.getAbizena();
            bezeroIzenburua.setText(izenaOsoa + " - Eskaerak");
            kargatuDatuak();
        }
    }

    /**
     * Taularen zutabeak EskaerakBean objektuaren atributuekin lotzen ditu.
     */
    private void konfiguratuZutabeak() {
        produktuIzenaColumn.setCellValueFactory(new PropertyValueFactory<>("produktu"));
        produktuKategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        produktuKopuruaColumn.setCellValueFactory(new PropertyValueFactory<>("kopurua"));
        produktuEgoeraColumn.setCellValueFactory(new PropertyValueFactory<>("deskribapena"));
        produktuEskaeraDataColumn.setCellValueFactory(new PropertyValueFactory<>("eskaera_data"));
        saltzaileIzenaColumn.setCellValueFactory(new PropertyValueFactory<>("saltzailea"));
    }

    /**
     * Bezeroaren eskaera guztiak kargatzen ditu.
     */
    private void kargatuDatuak() {
        if (bezeroData != null) {
            ArrayList<EskaerakBean> eskaerak = jasoBezeroenProduktuEskaerak(
                bezeroData.getIzena(), 
                bezeroData.getAbizena()
            );
            eskaerakList.setAll(eskaerak);
            filtrazioList.setAll(eskaerakList);
            konfiguratuEskaerakPaginazioa(pagination, filtrazioList, tableView);
        }
    }

    /**
     * Bilaketa funtzionalitatea konfiguratzen du.
     * <p>
     * Erabiltzaileak idazten duen testuaren arabera iragazten ditu datuak
     * produktu izena, kategoria edo saltzailearen arabera.
     * </p>
     */
    private void konfiguratuBilaketa() {
        bilaketaField.textProperty().addListener((observable, oldValue, jasoBilatzekoTestua) -> {
            if (jasoBilatzekoTestua == null || jasoBilatzekoTestua.trim().isEmpty()) {
                filtrazioList.setAll(eskaerakList);
            } else {
                String aurkituIdatzitakoTestua = jasoBilatzekoTestua.toLowerCase().trim();

                List<EskaerakBean> filtrados = eskaerakList.stream()
                        .filter(e -> (e.getProduktu() != null && 
                                     e.getProduktu().toLowerCase().contains(aurkituIdatzitakoTestua))
                                || (e.getKategoria() != null && 
                                    e.getKategoria().toLowerCase().contains(aurkituIdatzitakoTestua))
                                || (e.getSaltzailea() != null && 
                                    e.getSaltzailea().toLowerCase().contains(aurkituIdatzitakoTestua)))
                        .collect(Collectors.toList());

                filtrazioList.setAll(filtrados);
            }

            pagination.setPageCount((int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE));
            pagination.setCurrentPageIndex(0);
            eguneratuEskaerakOrriaDatuak(0, filtrazioList, tableView);
        });
    }
}