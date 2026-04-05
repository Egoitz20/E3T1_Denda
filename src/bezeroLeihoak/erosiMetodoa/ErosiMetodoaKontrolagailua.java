package bezeroLeihoak.erosiMetodoa;

import java.awt.Desktop;
import java.net.URI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import kontrolagailuGlobala.HandlerGlobala;
import kontrolagailuGlobala.OtzaraGlobala;

public class ErosiMetodoaKontrolagailua extends HandlerGlobala {

    @FXML
    private Label userLabel;
    
    @FXML
    private Label subtotalLabel;
    
    @FXML
    private Label ivaLabel;
    
    @FXML
    private Label guztiraLabel;

    public ErosiMetodoaKontrolagailua() {
    }
    
    @FXML
    public void initialize() {
        // Mostrar los totales de la compra
        OtzaraGlobala otzara = OtzaraGlobala.getInstantzia();
        subtotalLabel.setText(String.format("%.2f €", otzara.getTotala()));
        ivaLabel.setText(String.format("%.2f €", otzara.getIva()));
        guztiraLabel.setText(String.format("%.2f €", otzara.getGuztiraIva()));
    }

    @FXML
    public void itzuli() {
        itxiOraingoLeihoa();
        irekiBezeroMenuPrintzipala();
    }

    @FXML
    public void paypal() {
        String url = "https://www.paypal.com/es/home";
        irekiWebOrria(url);
        erosketaBurutu("PayPal");
    }

    @FXML
    public void visa() {
        String url = "https://www.visa.es/es_ES";
        irekiWebOrria(url);
        erosketaBurutu("VISA");
    }

    @FXML
    public void bizum() {
        String url = "https://bizum.es";
        irekiWebOrria(url);
        erosketaBurutu("BIZUM");
    }

    @FXML
    public void applePay() {
        String url = "https://www.apple.com/es/apple-pay/";
        irekiWebOrria(url);
        erosketaBurutu("Apple Pay");
    }

    @FXML
    public void googlePay() {
        String url = "https://pay.google.com/about/";
        irekiWebOrria(url);
        erosketaBurutu("Google Pay");
    }
    
    @FXML
    public void transferencia() {
        String url = "https://www.bankinter.com/banca-online/transferencias";
        irekiWebOrria(url);
        erosketaBurutu("Transferencia Bancaria");
    }
    
    private void irekiWebOrria(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin da nabigatzailea ireki", 
                       "Errorea web orria irekitzean: " + e.getMessage());
        }
    }
    
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