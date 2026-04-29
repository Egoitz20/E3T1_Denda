package bezeroLeihoak.erosiMetodoa;

import java.awt.Desktop;
import java.net.URI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import kontrolagailuGlobala.HandlerGlobala;
import kontrolagailuGlobala.OtzaraGlobala;

/**
 * Ordainketa metodoen kontrolagailua.
 * <p>
 * Ordainketa metodo desberdinak kudeatzen ditu: PayPal, VISA, BIZUM,
 * Apple Pay, Google Pay eta transferentzia.
 * </p>
 * 
 * @author AIA
 * @version 1.0
 * @see HandlerGlobala
 * @see OtzaraGlobala
 */
public class ErosiMetodoaKontrolagailua extends HandlerGlobala {

    /**
     * Bezeroaren izena erakusteko etiketa.
     */
    @FXML
    private Label userLabel;

    /**
     * Azpitotala erakusteko etiketa.
     */
    @FXML
    private Label subtotalLabel;

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
     * Eraikitzaile hutsa.
     */
    public ErosiMetodoaKontrolagailua() {
    }

    /**
     * Hasieratzen du kontrolagailua.
     * <p>
     * Erosketaren totalak kalkulatu eta erakusten ditu.
     * </p>
     */
    @FXML
    public void initialize() {
        // Mostrar los totales de la compra
        OtzaraGlobala otzara = OtzaraGlobala.getInstantzia();
        subtotalLabel.setText(String.format("%.2f €", otzara.getTotala()));
        ivaLabel.setText(String.format("%.2f €", otzara.getIva()));
        guztiraLabel.setText(String.format("%.2f €", otzara.getGuztiraIva()));
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
     * PayPal ordainketa metodoa hautatzen du.
     */
    @FXML
    public void paypal() {
        String url = "https://www.paypal.com/es/home";
        irekiWebOrria(url);
        erosketaBurutu("PayPal");
    }

    /**
     * VISA ordainketa metodoa hautatzen du.
     */
    @FXML
    public void visa() {
        String url = "https://www.visa.es/es_ES";
        irekiWebOrria(url);
        erosketaBurutu("VISA");
    }

    /**
     * BIZUM ordainketa metodoa hautatzen du.
     */
    @FXML
    public void bizum() {
        String url = "https://bizum.es";
        irekiWebOrria(url);
        erosketaBurutu("BIZUM");
    }

    /**
     * Apple Pay ordainketa metodoa hautatzen du.
     */
    @FXML
    public void applePay() {
        String url = "https://www.apple.com/es/apple-pay/";
        irekiWebOrria(url);
        erosketaBurutu("Apple Pay");
    }

    /**
     * Google Pay ordainketa metodoa hautatzen du.
     */
    @FXML
    public void googlePay() {
        String url = "https://pay.google.com/about/";
        irekiWebOrria(url);
        erosketaBurutu("Google Pay");
    }

    /**
     * Transferentzia ordainketa metodoa hautatzen du.
     */
    @FXML
    public void transferencia() {
        String url = "https://www.bankinter.com/banca-online/transferencias";
        irekiWebOrria(url);
        erosketaBurutu("Transferencia Bancaria");
    }

    /**
     * Web orria irekitzen du nabigatzailean.
     *
     * @param url Ireki beharreko web helbidea
     */
    private void irekiWebOrria(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin da nabigatzailea ireki",
                       "Errorea web orria irekitzean: " + e.getMessage());
        }
    }

    /**
     * Erosketa burutzen du.
     * <p>
     * Konfirmazio mezua erakusten du, otzara garbitzen du eta menu nagusira itzultzen da.
     * </p>
     *
     * @param metodoa Hautatutako ordainketa metodoa
     */
    private void erosketaBurutu(String metodoa) {
        // Mostrar mensaje de confirmación
        OtzaraGlobala otzara = OtzaraGlobala.getInstantzia();
        double guztira = otzara.getGuztiraIva();

        irekiAlerta("Erosketa Burututa",
                   "Eskerrik asko zure erosketa!",
                   "Ordainketa metodoa: " + metodoa + "\n" +
                   "Ordaindutako guztira: " + String.format("%.2f €", guztira) + "\n" +
                   "Laster jasoko duzu konfirmazio emaila.");

        // Vaciar el carrito después de la compra
        otzara.garbitu();

        // Volver al menú principal
        itxiOraingoLeihoa();
        irekiBezeroMenuPrintzipala();
    }
}