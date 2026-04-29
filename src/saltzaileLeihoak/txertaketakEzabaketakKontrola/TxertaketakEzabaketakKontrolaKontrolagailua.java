package saltzaileLeihoak.txertaketakEzabaketakKontrola;

import java.util.ArrayList;

import datuBaseKonexioa.TxertaketakEzabaketakKontrolaBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kontrolagailuGlobala.HandlerGlobala;

/**
 * Txertaketen eta ezabaketen kontrola leihoaren kontrolagailua.
 * <p>
 * Klase honek datu-basean egindako txertaketa eta ezabaketa guztien
 * erregistroa erakusten du bi zutabetan: txertaketak eta ezabaketak.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class TxertaketakEzabaketakKontrolaKontrolagailua extends HandlerGlobala {

    /** Txertaketen eta ezabaketen taula */
    @FXML
    private TableView<TxertaketakEzabaketakKontrolaBean> tableView;

    /** Txertaketen zutabea */
    @FXML
    private TableColumn<TxertaketakEzabaketakKontrolaBean, String> txertaketakColumn;

    /** Ezabaketen zutabea */
    @FXML
    private TableColumn<TxertaketakEzabaketakKontrolaBean, String> ezabaketakColumn;

    /** Datuak gordetzeko zerrenda */
    private ObservableList<TxertaketakEzabaketakKontrolaBean> kontrolaList = FXCollections.observableArrayList();

    /**
     * Eraikitzaile lehenetsia.
     */
    public TxertaketakEzabaketakKontrolaKontrolagailua() {
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
     * Taularen zutabeak TxertaketakEzabaketakKontrolaBean objektuaren
     * atributuekin lotzen ditu.
     */
    private void konfiguratuZutabeak() {
        txertaketakColumn.setCellValueFactory(new PropertyValueFactory<>("txertaketak"));
        ezabaketakColumn.setCellValueFactory(new PropertyValueFactory<>("ezabaketak"));
    }

    /**
     * Datu-baseko txertaketa eta ezabaketa erregistro guztiak kargatzen ditu.
     */
    private void kargatuDatuak() {
        ArrayList<TxertaketakEzabaketakKontrolaBean> eguneraketak = jasoTxertaketakEzabaketak();
        kontrolaList.setAll(eguneraketak);
        tableView.setItems(kontrolaList);
    }
}