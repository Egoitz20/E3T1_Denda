package saltzaileLeihoak.eguneraketakKontrola;

import java.util.ArrayList;

import datuBaseKonexioa.EguneraketakBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kontrolagailuGlobala.HandlerGlobala;

/**
 * Eguneraketen kontrola leihoaren kontrolagailua.
 * <p>
 * Klase honek datu-basean egindako eguneraketa guztien erregistroa erakusten du
 * zutabe bakar batean.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class EguneraketakKontrolaKontrolagailua extends HandlerGlobala {

    /** Eguneraketen taula */
    @FXML
    private TableView<EguneraketakBean> tableView;

    /** Kontrolaren zutabea */
    @FXML
    private TableColumn<EguneraketakBean, String> kontrolaColumn;
    
    /** Datuak gordetzeko zerrenda */
    private ObservableList<EguneraketakBean> eguneraketakList = FXCollections.observableArrayList(); 

    /**
     * Eraikitzaile lehenetsia.
     */
    public EguneraketakKontrolaKontrolagailua() {
    }

    /**
     * FXMLa kargatzean automatikoki exekutatzen den metodoa.
     * <p>
     * Zutabeak konfiguratzen ditu eta datuak kargatzen ditu.
     * </p>
     */
    @FXML
    public void initialize() {
        konfiguratuZutabeak();
        kargatuDatuak();
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
     * Taularen zutabea EguneraketakBean objektuaren atributuarekin lotzen du.
     */
    private void konfiguratuZutabeak() {
        kontrolaColumn.setCellValueFactory(new PropertyValueFactory<>("kontrola"));
    }
    
    /**
     * Datu-baseko eguneraketa erregistro guztiak kargatzen ditu.
     */
    private void kargatuDatuak() {
        ArrayList<EguneraketakBean> eguneraketak = jasoEguneraketak();
        eguneraketakList.setAll(eguneraketak);
        tableView.setItems(eguneraketakList);
    }
}