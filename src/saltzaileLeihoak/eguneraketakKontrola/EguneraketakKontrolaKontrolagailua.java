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

public class EguneraketakKontrolaKontrolagailua extends HandlerGlobala {

	@FXML
	private TableView<EguneraketakBean> tableView;

	@FXML
	private TableColumn<EguneraketakBean, String> kontrolaColumn;
	
	private ObservableList<EguneraketakBean> eguneraketakList = FXCollections.observableArrayList(); 

	public EguneraketakKontrolaKontrolagailua() {
	}

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
		kontrolaColumn.setCellValueFactory(new PropertyValueFactory<>("kontrola"));
	}
	
	private void kargatuDatuak() {
		ArrayList<EguneraketakBean> eguneraketak = jasoEguneraketak();
		eguneraketakList.setAll(eguneraketak);
	}
	
	
}
