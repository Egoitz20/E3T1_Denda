package saltzaileLeihoak.abisuak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kontrolagailuGlobala.AbisuakGlobala;
import kontrolagailuGlobala.HandlerGlobala;
import datuBaseKonexioa.AbisuakBean;
import saltzaileLeihoak.arazoDeskribapena.ArazoDeskribapena;

/**
 * Abisuen leihoaren kontrolagailua.
 * <p>
 * Klase honek bezeroen abisu guztiak erakusten ditu taula batean,
 * orrikatzearekin. Abisu baten gainean klik bikoitza eginez gero,
 * arazoaren deskribapena ikusteko leihoa irekitzen da.
 * </p>
 * 
 * @author Zure Izena
 * @version 1.0
 */
public class AbisuakKontrolagailua extends HandlerGlobala {

    /** Abisuen taula */
    @FXML
    private TableView<AbisuakBean> tableView;

    /** Bezeroaren izenaren zutabea */
    @FXML
    private TableColumn<AbisuakBean, String> bezeroIzenaColumn;

    /** Kontzeptuaren zutabea */
    @FXML
    private TableColumn<AbisuakBean, String> kontzeptuaColumn;

    /** Deskribapenaren zutabea */
    @FXML
    private TableColumn<AbisuakBean, String> deskribapenaColumn;

    /** Dataren zutabea */
    @FXML
    private TableColumn<AbisuakBean, String> dataColumn;

    /** Orrikatze kontrola */
    @FXML
    private Pagination pagination;

    /** Abisu guztiak gordetzeko zerrenda */
    private ObservableList<AbisuakBean> abisuakList = FXCollections.observableArrayList();
    
    /** Bilaketaren emaitzak gordetzeko zerrenda (bilaketarik gabe, abisu guztiak) */
    private ObservableList<AbisuakBean> filtrazioList = FXCollections.observableArrayList();

    /**
     * Eraikitzaile lehenetsia.
     */
    public AbisuakKontrolagailua() {
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
     * Zutabeak konfiguratzen ditu, datuak kargatzen ditu, orrikatzea konfiguratzen du
     * eta taulan klik bikoitzeko gertaera gehitzen du arazoaren deskribapena ikusteko.
     * </p>
     */
    @FXML
    public void initialize() {
        konfiguratuZutabeak();
        kargatuDatuak();
        konfiguratuAbisuakPaginazioa(pagination, abisuakList, tableView);
        
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                AbisuakBean selectedAbisua = tableView.getSelectionModel().getSelectedItem();
                if (selectedAbisua != null) {
                    irekiArazoDeskribapena(selectedAbisua);
                }
            }
        });
    }

    /**
     * Taularen zutabeak AbisuakBean objektuaren atributuekin lotzen ditu.
     */
    private void konfiguratuZutabeak() {
        bezeroIzenaColumn.setCellValueFactory(new PropertyValueFactory<>("bezeroIzena"));
        kontzeptuaColumn.setCellValueFactory(new PropertyValueFactory<>("kontzeptua"));
        deskribapenaColumn.setCellValueFactory(new PropertyValueFactory<>("deskribapena"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
    }

    /**
     * Datu-baseko abisu guztiak kargatzen ditu (AbisuakGlobala singletonetik).
     */
    private void kargatuDatuak() {
        abisuakList.setAll(AbisuakGlobala.getInstantzia().getAbisuak());
        filtrazioList.setAll(abisuakList);
    }

    /**
     * Arazoaren deskribapena leihoa irekitzen du hautatutako abisuarekin.
     *
     * @param abisua ikusi nahi den abisua
     */
    private void irekiArazoDeskribapena(AbisuakBean abisua) {
        try {
            ArazoDeskribapena arazoDeskribapena = new ArazoDeskribapena();
            Stage newStage = new Stage();
            arazoDeskribapena.setAbisua(abisua);
            arazoDeskribapena.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki", 
                       "Errorea arazo deskribapena irekitzean: " + e.getMessage());
        }
    }
}