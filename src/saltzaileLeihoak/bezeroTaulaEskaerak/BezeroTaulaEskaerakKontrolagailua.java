package saltzaileLeihoak.bezeroTaulaEskaerak;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import datuBaseKonexioa.BezeroBean;
import javafx.beans.property.SimpleStringProperty;
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
import saltzaileLeihoak.eskaerakTaula.EskaerakTaula;

/**
 * Bezeroen eskaeren taula leihoaren kontrolagailua.
 * <p>
 * Klase honek bezero guztien zerrenda erakusten du taula batean,
 * bakoitzarekin "Ikusi eskaerak" botoi bat duena. Botoia sakatuz gero,
 * bezeroaren eskaera guztiak erakusten dituen taula irekitzen da.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class BezeroTaulaEskaerakKontrolagailua extends HandlerGlobala {

    // Taula
    @FXML
    private TableView<BezeroBean> tableView;

    // Zutabeak
    @FXML
    private TableColumn<BezeroBean, String> izenaAbizenaColumn;

    /** Botoiak izango dituen ekintza-zutabea */
    @FXML
    private TableColumn<BezeroBean, Void> actionColumn;

    // Bilaketa
    @FXML
    private TextField bilaketaField;

    // Paginazioa
    @FXML
    private Pagination pagination;

    /** Datu-baseko bezero guztiak gordetzeko zerrenda */
    private ObservableList<BezeroBean> bezeroList = FXCollections.observableArrayList();
    
    /** Bilaketaren emaitzak gordetzeko zerrenda */
    private ObservableList<BezeroBean> filtrazioList = FXCollections.observableArrayList();

    /** Orriko erregistro kopurua */
    private static final int ROWS_PER_PAGE = 10;

    /**
     * Eraikitzaile lehenetsia.
     */
    public BezeroTaulaEskaerakKontrolagailua() {
    }

    /**
     * Itzuli botoiaren metodoa.
     * <p>
     * Leihoa ixten du eta saltzaile menu nagusira itzultzen da.
     * </p>
     */
    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
        irekiSaltzaileMenuPrintzipala();
    }

    /**
     * FXMLa kargatzean automatikoki exekutatzen den metodoa.
     * <p>
     * Taula konfiguratzen du, datuak kargatzen ditu, orrikatzea eta
     * bilaketa konfiguratzen ditu, eta ekintza zutabea (botoia) gehitzen du.
     * </p>
     */
    @FXML
    public void initialize() {
        konfiguratuZutabeak();
        kargatuDatuak();
        konfiguratuBezeroPaginazioa(pagination, filtrazioList, tableView);
        konfiguratuBilaketa();
        konfiguratuEkintzaZutabea();
    }

    /**
     * Izenen zutabea konfiguratzen du, izena eta abizena konbinatuz.
     */
    private void konfiguratuZutabeak() {
        izenaAbizenaColumn.setCellValueFactory(cellData -> {
            BezeroBean bezero = cellData.getValue();
            String izenaOsoa = bezero.getIzena() + " " + bezero.getAbizena();
            return new SimpleStringProperty(izenaOsoa);
        });
    }

    /**
     * Ekintza zutabea konfiguratzen du, "Ikusi Eskaerak" botoia gehituz.
     */
    private void konfiguratuEkintzaZutabea() {
        actionColumn.setCellFactory(new Callback<TableColumn<BezeroBean, Void>, TableCell<BezeroBean, Void>>() {
            @Override
            public TableCell<BezeroBean, Void> call(TableColumn<BezeroBean, Void> param) {
                return new TableCell<BezeroBean, Void>() {
                    private final Button btnIkusiEskaerak = new Button("Ikusi Eskaerak");

                    {
                        btnIkusiEskaerak.setOnAction(_ -> {
                            BezeroBean bezero = getTableView().getItems().get(getIndex());
                            irekiEskaerakTaula(bezero);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnIkusiEskaerak);
                        }
                    }
                };
            }
        });
    }

    /**
     * Bezeroaren eskaeren taula irekitzen du hautatutako bezeroarekin.
     *
     * @param bezero eskaerak ikusi nahi zaizkion bezeroa
     */
    private void irekiEskaerakTaula(BezeroBean bezero) {
        try {
            EskaerakTaula eskaerak = new EskaerakTaula();
            Stage newStage = new Stage();
            eskaerak.setEskaeraBezeroData(bezero);
            eskaerak.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea bezeroaren eskaerak irekitzean: " + e.getMessage());
        }
    }

    /**
     * Datu-baseko bezero guztiak kargatzen ditu.
     */
    private void kargatuDatuak() {
        ArrayList<BezeroBean> bezeroak = jasoBezeroak();
        bezeroList.setAll(bezeroak);
        filtrazioList.setAll(bezeroList);
    }

    /**
     * Bilaketa funtzionalitatea konfiguratzen du.
     * <p>
     * Erabiltzaileak idazten duen testuaren arabera iragazten ditu datuak
     * izenaren edo emailaren arabera.
     * </p>
     */
    private void konfiguratuBilaketa() {
        bilaketaField.textProperty().addListener((observable, oldValue, jasoBilatzekoTestua) -> {
            if (jasoBilatzekoTestua == null || jasoBilatzekoTestua.trim().isEmpty()) {
                filtrazioList.setAll(bezeroList);
            } else {
                String aurkituIdatzitakoTestua = jasoBilatzekoTestua.toLowerCase().trim();

                List<BezeroBean> filtrados = bezeroList.stream()
                        .filter(b -> (b.getIzena() != null && 
                                     b.getIzena().toLowerCase().contains(aurkituIdatzitakoTestua))
                                || (b.getEmaila() != null && 
                                    b.getEmaila().toLowerCase().contains(aurkituIdatzitakoTestua)))
                        .collect(Collectors.toList());

                filtrazioList.setAll(filtrados);
            }

            pagination.setPageCount((int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE));
            pagination.setCurrentPageIndex(0);
            eguneratuBezeroOrriaDatuak(0, filtrazioList, tableView);
        });
    }
}