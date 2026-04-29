package saltzaileLeihoak.saltzaileTaula;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import datuBaseKonexioa.LangileSaltzaileBean;
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
import saltzaileLeihoak.saltzaileKudeaketa.SaltzaileKudeaketa;

/**
 * Saltzaileen taula leihoaren kontrolagailua.
 * <p>
 * Klase honek saltzaile guztien zerrenda erakusten du taula batean,
 * bilaketa funtzionalitatea eta orrikatzea ditu. Saltzaile bat
 * hautatzean, editatzeko leihoa irekitzen du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class SaltzaileTaulaKontrolagailua extends HandlerGlobala {

    // Taula
    @FXML
    private TableView<LangileSaltzaileBean> tableView;

    // Zutabeak
    @FXML
    private TableColumn<LangileSaltzaileBean, Integer> idColumn;
    @FXML
    private TableColumn<LangileSaltzaileBean, String> izenaAbizenakColumn;
    @FXML
    private TableColumn<LangileSaltzaileBean, String> erabiltzaileaColumn;
    @FXML
    private TableColumn<LangileSaltzaileBean, String> pasahitzaColumn;
    @FXML
    private TableColumn<LangileSaltzaileBean, String> emailaColumn;
    @FXML
    private TableColumn<LangileSaltzaileBean, String> telefonoaColumn;

    /** Bilaketa testu eremua */
    @FXML
    private TextField bilaketaField;

    /** Orrikatze kontrola */
    @FXML
    private Pagination pagination;

    /** Datu-baseko saltzaile guztiak gordetzeko zerrenda */
    private ObservableList<LangileSaltzaileBean> saltzaileList = FXCollections.observableArrayList();

    /** Bilaketaren emaitzak gordetzeko zerrenda */
    private ObservableList<LangileSaltzaileBean> filtrazioList = FXCollections.observableArrayList();

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
        konfiguratuSaltzailePaginazioa(pagination, filtrazioList, tableView);
        konfiguratuBilaketa();

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                LangileSaltzaileBean selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    irekiSaltzaileKudeaketa(selectedItem);
                }
            }
        });
    }

    /**
     * Saltzailearen kudeaketa leihoa irekitzen du hautatutako saltzailearekin.
     *
     * @param saltzailea editatzeko hautatutako saltzailea
     */
    private void irekiSaltzaileKudeaketa(LangileSaltzaileBean saltzailea) {
        try {
            SaltzaileKudeaketa kudeaketa = new SaltzaileKudeaketa();
            Stage newStage = new Stage();
            kudeaketa.setSaltzaileData(saltzailea);
            kudeaketa.start(newStage);
            itxiOraingoLeihoa();

            newStage.setOnHidden(_ -> {
                irekiSaltzaileMenuPrintzipala();
            });
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea saltzailearen kudeaketa irekitzean: " + e.getMessage());
        }
    }

    /**
     * Taularen zutabeak LangileSaltzaileBean objektuaren atributuekin lotzen ditu.
     */
    private void konfiguratuZutabeak() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        izenaAbizenakColumn.setCellValueFactory(new PropertyValueFactory<>("langileIzenaAbizena"));
        erabiltzaileaColumn.setCellValueFactory(new PropertyValueFactory<>("erabiltzailea"));
        pasahitzaColumn.setCellValueFactory(new PropertyValueFactory<>("pasahitza"));
        emailaColumn.setCellValueFactory(new PropertyValueFactory<>("emaila"));
        telefonoaColumn.setCellValueFactory(new PropertyValueFactory<>("telefonoa"));
    }

    /**
     * Datu-baseko saltzaile guztiak kargatzen ditu.
     */
    private void kargatuDatuak() {
        ArrayList<LangileSaltzaileBean> saltzaileak = jasoLangileSaltzaileak();
        saltzaileList.setAll(saltzaileak);
        filtrazioList.setAll(saltzaileList);
    }

    /**
     * Bilaketa funtzionalitatea konfiguratzen du.
     * <p>
     * Erabiltzaileak idazten duen testuaren arabera iragazten ditu datuak
     * izena, erabiltzailea edo IDaren arabera.
     * </p>
     */
    private void konfiguratuBilaketa() {
        bilaketaField.textProperty().addListener((observable, oldValue, jasoBilatzekoTestua) -> {
            if (jasoBilatzekoTestua == null || jasoBilatzekoTestua.trim().isEmpty()) {
                filtrazioList.setAll(saltzaileList);
            } else {
                String aurkituIdatzitakoTestua = jasoBilatzekoTestua.toLowerCase().trim();

                List<LangileSaltzaileBean> filtrados = saltzaileList.stream()
                        .filter(s -> (s.getLangileIzenaAbizena() != null && 
                                     s.getLangileIzenaAbizena().toLowerCase().contains(aurkituIdatzitakoTestua))
                                || (s.getErabiltzailea() != null && 
                                    s.getErabiltzailea().toLowerCase().contains(aurkituIdatzitakoTestua))
                                || String.valueOf(s.getId()).contains(aurkituIdatzitakoTestua))
                        .collect(Collectors.toList());
                filtrazioList.setAll(filtrados);
            }

            pagination.setPageCount((int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE));
            pagination.setCurrentPageIndex(0);
            eguneratuSaltzaileOrriaDatuak(0, filtrazioList, tableView);
        });
    }
}