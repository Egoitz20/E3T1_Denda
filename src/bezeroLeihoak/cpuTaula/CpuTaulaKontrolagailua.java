package bezeroLeihoak.cpuTaula;

import java.util.HashMap;
import java.util.Map;

import datuBaseKonexioa.ProduktuBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import kontrolagailuGlobala.HandlerGlobala;
import kontrolagailuGlobala.OtzaraGlobala;

/**
 * CPU produktuen taula leihoaren kontrolagailua.
 * <p>
 * Klase honek CPU kategorian dauden produktu guztiak erakusten ditu taula batean,
 * orrikatzearekin. Bezeroak produktu bakoitzaren kantitatea hauta dezake
 * (+/- botoiekin) eta "Gehitu Otzara" botoia sakatuz gero, hautatutako
 * produktuak otzaran gehitzen dira.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 */
public class CpuTaulaKontrolagailua extends HandlerGlobala {

    /** Produktuen taula */
    @FXML
    private TableView<ProduktuBean> tableView;

    /** Produktuaren izenaren zutabea */
    @FXML
    private TableColumn<ProduktuBean, String> izenaColumn;

    /** Produktuaren deskribapenaren zutabea */
    @FXML
    private TableColumn<ProduktuBean, String> deskribapenaColumn;

    /** Produktuaren salneurriaren zutabea */
    @FXML
    private TableColumn<ProduktuBean, Double> salneurriaColumn;

    /** Kantitatea zutabea (+/- botoiekin) */
    @FXML
    private TableColumn<ProduktuBean, Void> kantitateaColumn;

    /** Orrikatze kontrola */
    @FXML
    private Pagination pagination;

    /** CPU produktu guztiak gordetzeko zerrenda */
    private ObservableList<ProduktuBean> cpuList = FXCollections.observableArrayList();
    
    /** Bilaketaren emaitzak gordetzeko zerrenda */
    private ObservableList<ProduktuBean> filtrazioList = FXCollections.observableArrayList();

    /** Produktu bakoitzarentzako hautatutako kantitatea gordetzeko mapa */
    private Map<Integer, Integer> kantitateak = new HashMap<>();

    /**
     * Eraikitzaile lehenetsia.
     */
    public CpuTaulaKontrolagailua() {
    }
    
    /**
     * Itzuli botoiaren metodoa.
     * <p>
     * Leihoa ixten du eta bezero menu nagusira itzultzen da.
     * </p>
     */
    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
        irekiBezeroMenuPrintzipala();
    }
    
    /**
     * FXMLa kargatzean automatikoki exekutatzen den metodoa.
     * <p>
     * Zutabeak konfiguratzen ditu, CPU produktuak kargatzen ditu,
     * orrikatzea konfiguratzen du eta kantitatea zutabea sortzen du.
     * </p>
     */
    @FXML
    public void initialize() {
        konfiguratuKategoriaZutabeak(izenaColumn, deskribapenaColumn, salneurriaColumn);
        kargatuKategoriaDatuak(cpuList, filtrazioList, kantitateak, "CPU");
        konfiguratuBezeroKategoriaPaginazioa(pagination, filtrazioList, tableView);
        konfiguratuKantitateaZutabea();
    }

    /**
     * Gehitu Otzara botoiaren metodoa.
     * <p>
     * Hautatutako kantitatearekin produktuak otzaran gehitzen ditu eta
     * kantitateak berrezartzen ditu.
     * </p>
     */
    @FXML
    public void gehituOtzara() {
        OtzaraGlobala otzara = OtzaraGlobala.getInstantzia();
        
        for (ProduktuBean produktu : cpuList) {
            int kantitatea = kantitateak.getOrDefault(produktu.getId(), 0);
            if (kantitatea > 0) {
                otzara.gehituProduktua(produktu, kantitatea);
                kantitateak.put(produktu.getId(), 0);
            }
        }
        
        eguneratuBezeroKategoriaOrriaDatuak(pagination.getCurrentPageIndex(), cpuList, tableView);
        irekiAlerta("Arrakasta", "Produktuak otzaran gehitu dira", 
                    "Produktuak ondo gehitu dira zure otzaran.");
    }

    /**
     * Kantitatea zutabea konfiguratzen du, + eta - botoiekin.
     * <p>
     * Produktu bakoitzarentzat kantitatea handitzeko edo txikitzeko botoiak sortzen ditu.
     * </p>
     */
    private void konfiguratuKantitateaZutabea() {
        kantitateaColumn.setCellFactory(new Callback<TableColumn<ProduktuBean, Void>, TableCell<ProduktuBean, Void>>() {
            @Override
            public TableCell<ProduktuBean, Void> call(TableColumn<ProduktuBean, Void> param) {
                return new TableCell<ProduktuBean, Void>() {

                    private final Button btnKendu = new Button("-");
                    private final Label lblKopurua = new Label("0");
                    private final Button btnGehitu = new Button("+");
                    private final HBox container = new HBox(10, btnKendu, lblKopurua, btnGehitu);

                    {
                        btnKendu.setPrefWidth(40);
                        btnGehitu.setPrefWidth(40);
                        lblKopurua.setPrefWidth(40);
                        lblKopurua.setAlignment(Pos.CENTER);
                        lblKopurua.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
                        container.setAlignment(Pos.CENTER);

                        btnKendu.setOnAction(_ -> {
                            ProduktuBean produktu = getTableView().getItems().get(getIndex());
                            if (produktu != null) {
                                int id = produktu.getId();
                                int current = kantitateak.getOrDefault(id, 0);
                                if (current > 0) {
                                    kantitateak.put(id, current - 1);
                                    lblKopurua.setText(String.valueOf(current - 1));
                                }
                            }
                        });

                        btnGehitu.setOnAction(_ -> {
                            ProduktuBean produktu = getTableView().getItems().get(getIndex());
                            if (produktu != null) {
                                int id = produktu.getId();
                                int current = kantitateak.getOrDefault(id, 0);
                                kantitateak.put(id, current + 1);
                                lblKopurua.setText(String.valueOf(current + 1));
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            ProduktuBean produktu = getTableView().getItems().get(getIndex());
                            if (produktu != null) {
                                int kantitatea = kantitateak.getOrDefault(produktu.getId(), 0);
                                lblKopurua.setText(String.valueOf(kantitatea));
                            }
                            setGraphic(container);
                        }
                    }
                };
            }
        });
    }
}