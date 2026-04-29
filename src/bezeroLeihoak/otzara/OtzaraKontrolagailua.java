package bezeroLeihoak.otzara;

import bezeroLeihoak.erosiMetodoa.ErosiMetodoa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import kontrolagailuGlobala.HandlerGlobala;
import kontrolagailuGlobala.OtzaraGlobala;
import kontrolagailuGlobala.OtzaraItem;

/**
 * Otzararen kontrolagailua.
 * <p>
 * Otzarako produktuen zerrenda kudeatzen du: produktuak gehitu, kendu,
 * kantitateak aldatu eta erosketa prozesua abiarazten du.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 * @see HandlerGlobala
 * @see OtzaraGlobala
 * @see OtzaraItem
 */
public class OtzaraKontrolagailua extends HandlerGlobala {

    /**
     * Produktuen edukiontzia.
     */
    @FXML
    private VBox produktuakContainer;

    /**
     * Totala erakusteko etiketa.
     */
    @FXML
    private Label totalaLabel;

    /**
     * IVA erakusteko etiketa.
     */
    @FXML
    private Label ivaLabel;

    /**
     * Guztira erakusteko etiketa.
     */
    @FXML
    private Label guztiraLabel;

    /**
     * Otzarako instantzia globala.
     */
    private OtzaraGlobala otzara;

    /**
     * Eraikitzaile hutsa.
     */
    public OtzaraKontrolagailua() {
    }

    /**
     * Menu nagusira itzultzen da.
     */
    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
        irekiBezeroMenuPrintzipala();
    }

    /**
     * Erosketa prozesua abiarazten du.
     * <p>
     * Otzara hutsik badago, abisua erakusten du.
     * </p>
     */
    @FXML
    public void erosi() {
        if (otzara.getProduktuak().isEmpty()) {
            irekiAlerta("Informazioa", "Otzara hutsik", "Ez duzu produkturik erosteko.");
            return;
        }

        try {
            ErosiMetodoa erosiMetodoa = new ErosiMetodoa();
            Stage newStage = new Stage();
            erosiMetodoa.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                       "Errorea erosia metodoa irekitzean: " + e.getMessage());
        }
    }

    /**
     * Hasieratzen du kontrolagailua.
     * <p>
     * Otzarako instantzia lortu eta produktuak kargatzen ditu.
     * </p>
     */
    @FXML
    public void initialize() {
        otzara = OtzaraGlobala.getInstantzia();
        kargatuOtzara();
    }

    /**
     * Otzarako produktuak kargatzen ditu.
     */
    private void kargatuOtzara() {
        produktuakContainer.getChildren().clear();

        for (OtzaraItem item : otzara.getProduktuak()) {
            HBox itemBox = createProductItem(item);
            produktuakContainer.getChildren().add(itemBox);
        }

        eguneratuPrezioak();
    }

    /**
     * Produktu bakoitzarentzako elementu grafikoa sortzen du.
     *
     * @param item Produktuaren informazioa
     * @return Produktua erakusteko HBox-a
     */
    private HBox createProductItem(OtzaraItem item) {
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5; -fx-padding: 10;");

        // ID
        Label idLabel = new Label(String.valueOf(item.getId()));
        idLabel.setPrefWidth(50);

        // Izena
        Label izenaLabel = new Label(item.getIzena());
        izenaLabel.setPrefWidth(200);

        // Salneurria
        Label salneurriaLabel = new Label(String.format("%.2f €", item.getSalneurria()));
        salneurriaLabel.setPrefWidth(80);

        // Boton -
        Button btnKendu = new Button("-");
        btnKendu.setPrefWidth(40);

        // Cantidad
        Label kantitateLabel = new Label(String.valueOf(item.getKantitatea()));
        kantitateLabel.setPrefWidth(40);
        kantitateLabel.setAlignment(Pos.CENTER);
        kantitateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Boton +
        Button btnGehitu = new Button("+");
        btnGehitu.setPrefWidth(40);

        // Total por producto
        Label totalLabel = new Label(String.format("%.2f €", item.getGuztira()));
        totalLabel.setPrefWidth(80);
        totalLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");

        // Boton eliminar
        Button btnEzabatu = new Button("🗑");
        btnEzabatu.setPrefWidth(40);

        btnKendu.setOnAction(_ -> {
            if (item.getKantitatea() > 0) {
                item.kenduKantitatea();
                if (item.getKantitatea() == 0) {
                    otzara.kenduProduktua(item);
                    produktuakContainer.getChildren().remove(hbox);
                } else {
                    kantitateLabel.setText(String.valueOf(item.getKantitatea()));
                    totalLabel.setText(String.format("%.2f €", item.getGuztira()));
                }
                eguneratuPrezioak();
            }
        });

        btnGehitu.setOnAction(_ -> {
            item.gehituKantitatea();
            kantitateLabel.setText(String.valueOf(item.getKantitatea()));
            totalLabel.setText(String.format("%.2f €", item.getGuztira()));
            eguneratuPrezioak();
        });

        btnEzabatu.setOnAction(_ -> {
            otzara.kenduProduktua(item);
            produktuakContainer.getChildren().remove(hbox);
            eguneratuPrezioak();
        });

        hbox.getChildren().addAll(idLabel, izenaLabel, salneurriaLabel,
                                  btnKendu, kantitateLabel, btnGehitu,
                                  totalLabel, btnEzabatu);

        return hbox;
    }

    /**
     * Prezioak eguneratzen ditu.
     * <p>
     * Totala, IVA eta guztira kalkulatu eta etiketetan erakusten ditu.
     * </p>
     */
    private void eguneratuPrezioak() {
        double totala = otzara.getTotala();
        double iva = otzara.getIva();
        double guztira = otzara.getGuztiraIva();

        totalaLabel.setText(String.format("%.2f €", totala));
        ivaLabel.setText(String.format("%.2f €", iva));
        guztiraLabel.setText(String.format("%.2f €", guztira));
    }
}