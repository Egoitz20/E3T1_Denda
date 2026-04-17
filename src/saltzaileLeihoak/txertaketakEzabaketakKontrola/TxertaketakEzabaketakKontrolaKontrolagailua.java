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

public class TxertaketakEzabaketakKontrolaKontrolagailua extends HandlerGlobala {

	public TxertaketakEzabaketakKontrolaKontrolagailua() {
	}

	@FXML
	private TableView<TxertaketakEzabaketakKontrolaBean> tableView;

	@FXML
	private TableColumn<TxertaketakEzabaketakKontrolaBean, String> txertaketakColumn;

	@FXML
	private TableColumn<TxertaketakEzabaketakKontrolaBean, String> ezabaketakColumn;

	private ObservableList<TxertaketakEzabaketakKontrolaBean> kontrolaList = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		konfiguratuZutabeak();
		kargatuDatuak();
	}

	@FXML
	public void itzuli() {
		itxiOraingoLeihoa();
		irekiSaltzaileMenuPrintzipala();
	}

	private void konfiguratuZutabeak() {
		txertaketakColumn.setCellValueFactory(new PropertyValueFactory<>("txertaketak"));
		ezabaketakColumn.setCellValueFactory(new PropertyValueFactory<>("ezabaketak"));
	}

	private void kargatuDatuak() {
		ArrayList<TxertaketakEzabaketakKontrolaBean> eguneraketak = jasoTxertaketakEzabaketak();
		kontrolaList.setAll(eguneraketak);
	}

}
