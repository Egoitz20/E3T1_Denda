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

/**
 * Bezeroen taula leihoaren kontrolagailua.
 * <p>
 * Klase honek bezero guztien zerrenda erakusten du taula batean,
 * bilaketa funtzionalitatea eta orrikatzea ditu. Bezero bat
 * hautatzean, editatzeko leihoa irekitzen du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class BezeroTaulaKontrolagailua extends HandlerGlobala {

    // Taula
    @FXML private TableView<BezeroBean> tableView;

    // Zutabeak
    @FXML private TableColumn<BezeroBean, String> izenaColumn;
    @FXML private TableColumn<BezeroBean, String> emailaColumn;
    @FXML private TableColumn<BezeroBean, String> helbideaColumn;

    // Bilaketa
    @FXML private TextField bilaketaField;

    // Paginazioa
    @FXML private Pagination pagination;

    /** Datu-baseko bezero guztiak gordetzeko zerrenda */
    private ObservableList<BezeroBean> bezeroList = FXCollections.observableArrayList();
    
    /** Bilaketaren emaitzak gordetzeko zerrenda */
    private ObservableList<BezeroBean> filtrazioList = FXCollections.observableArrayList();

    /** Orriko erregistro kopurua */
    private static final int ROWS_PER_PAGE = 10;

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
     * bilaketa konfiguratzen ditu, eta taulan klik egiteko gertaera gehitzen du.
     * </p>
     */
    @FXML
    public void initialize() {
        konfiguratuZutabeak();
        kargatuDatuak();
        konfiguratuBezeroPaginazioa(pagination, filtrazioList, tableView);
        konfiguratuBilaketa();
        
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                BezeroBean selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    irekiBezeroKudeaketa(selectedItem);
                }
            }
        });
    }
    
    /**
     * Bezeroaren kudeaketa leihoa irekitzen du hautatutako bezeroarekin.
     *
     * @param bezeroa editatzeko hautatutako bezeroa
     */
    private void irekiBezeroKudeaketa(BezeroBean bezeroa) {
        try {
            BezeroKudeaketa kudeaketa = new BezeroKudeaketa();
            Stage newStage = new Stage();
            kudeaketa.setBezeroData(bezeroa);
            kudeaketa.start(newStage);
            itxiOraingoLeihoa();

            newStage.setOnHidden(_ -> {
                irekiSaltzaileMenuPrintzipala();
            });
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea bezeroaren kudeaketa irekitzean: " + e.getMessage());
        }
    }

    /**
     * Taularen zutabeak BezeroBean objektuaren atributuekin lotzen ditu.
     */
    private void konfiguratuZutabeak() {
        izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));
        emailaColumn.setCellValueFactory(new PropertyValueFactory<>("emaila"));
        helbideaColumn.setCellValueFactory(new PropertyValueFactory<>("helbidea"));
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
     * izenaren, emailaren edo helbidearen arabera.
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
                                    b.getEmaila().toLowerCase().contains(aurkituIdatzitakoTestua))
                                || (b.getHelbidea() != null && 
                                    b.getHelbidea().toLowerCase().contains(aurkituIdatzitakoTestua)))
                        .collect(Collectors.toList());

                filtrazioList.setAll(filtrados);
            }

            pagination.setPageCount((int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE));
            pagination.setCurrentPageIndex(0);
            eguneratuBezeroOrriaDatuak(0, filtrazioList, tableView);
        });
    }
}