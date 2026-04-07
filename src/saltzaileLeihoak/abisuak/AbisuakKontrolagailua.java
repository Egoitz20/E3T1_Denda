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

public class AbisuakKontrolagailua extends HandlerGlobala {

    @FXML
    private TableView<AbisuakBean> tableView;

    @FXML
    private TableColumn<AbisuakBean, String> bezeroIzenaColumn;

    @FXML
    private TableColumn<AbisuakBean, String> kontzeptuaColumn;

    @FXML
    private TableColumn<AbisuakBean, String> deskribapenaColumn;

    @FXML
    private TableColumn<AbisuakBean, String> dataColumn;

    @FXML
    private Pagination pagination;

    private ObservableList<AbisuakBean> abisuakList = FXCollections.observableArrayList();
    private ObservableList<AbisuakBean> filtrazioList = FXCollections.observableArrayList();

    public AbisuakKontrolagailua() {
    }

    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
        irekiSaltzaileMenuPrintzipala();
    }

    @FXML
    public void initialize() {
        konfiguratuZutabeak();
        kargatuDatuak();
        konfiguratuAbisuakPaginazioa(pagination, abisuakList, tableView);
        
        // Gehitu click bikoitzeko entzulea taulan
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                AbisuakBean selectedAbisua = tableView.getSelectionModel().getSelectedItem();
                if (selectedAbisua != null) {
                    irekiArazoDeskribapena(selectedAbisua);
                }
            }
        });
    }

    private void konfiguratuZutabeak() {
        bezeroIzenaColumn.setCellValueFactory(new PropertyValueFactory<>("bezeroIzena"));
        kontzeptuaColumn.setCellValueFactory(new PropertyValueFactory<>("kontzeptua"));
        deskribapenaColumn.setCellValueFactory(new PropertyValueFactory<>("deskribapena"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
    }

    private void kargatuDatuak() {
        abisuakList.setAll(AbisuakGlobala.getInstantzia().getAbisuak());
        filtrazioList.setAll(abisuakList);
    }

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