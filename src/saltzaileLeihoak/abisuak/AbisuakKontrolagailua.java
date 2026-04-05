package saltzaileLeihoak.abisuak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kontrolagailuGlobala.AbisuakGlobala;
import kontrolagailuGlobala.HandlerGlobala;
import datuBaseKonexioa.AbisuakBean;

public class AbisuakKontrolagailua extends HandlerGlobala {

    @FXML
    private TableView<AbisuakBean> tableView;

    @FXML
    private TableColumn<AbisuakBean, String> bezeroIzenaColumn;

    @FXML
    private TableColumn<AbisuakBean, String> kontzeptuaColumn;

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
    }

    private void konfiguratuZutabeak() {
        bezeroIzenaColumn.setCellValueFactory(new PropertyValueFactory<>("bezeroIzena"));
        kontzeptuaColumn.setCellValueFactory(new PropertyValueFactory<>("kontzeptua"));
    }

    private void kargatuDatuak() {
        abisuakList.setAll(AbisuakGlobala.getInstantzia().getAbisuak());
        filtrazioList.setAll(abisuakList);
    }
}