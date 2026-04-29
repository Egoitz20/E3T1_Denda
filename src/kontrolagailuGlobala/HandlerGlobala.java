package kontrolagailuGlobala;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bezeroLeihoak.bezeroMenuPrintzipala.BezeroMenuPrintzipala;
import datuBaseKonexioa.AbisuakBean;
import datuBaseKonexioa.BezeroBean;
import datuBaseKonexioa.EguneraketakBean;
import datuBaseKonexioa.EskaerakBean;
import datuBaseKonexioa.Konexioa;
import datuBaseKonexioa.LangileSaltzaileBean;
import datuBaseKonexioa.ProduktuBean;
import datuBaseKonexioa.TxertaketakEzabaketakKontrolaBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import orokorLeihoak.login.Login;
import saltzaileLeihoak.saltzaileMenuPrintzipala.SaltzaileMenuPrintzipala;

/**
 * Klase orokorren kudeatzaile globala.
 * <p>
 * Klase honek aplikazio osoan erabiltzen diren funtzionalitate komunak biltzen ditu:
 * leihoen kudeaketa, datu-baseko kontsultak, txertaketak, eguneraketak, ezabaketak,
 * eta paginazioa. Klase honetatik heredatzen dute kontrolagailu guztiek.
 * </p>
 * 
 * @author Zure Izena
 * @version 1.0
 */
public class HandlerGlobala {

    /** Uneko leihoaren stage-a */
    private Stage stage;
    
    /** Saioa hasi duen saltzailearen datuak (estatikoak) */
    private static LangileSaltzaileBean saltzaileLogeatuta;
    
    /** Saioa hasi duen bezeroaren datuak (estatikoak) */
    private static BezeroBean bezeroLogeatuta;

    /** Orriko erakutsiko diren erregistro kopurua */
    private static final int ROWS_PER_PAGE = 10;

    // ============================================================
    // LEIHOEN KUDEAKETA
    // ============================================================

    /**
     * Stage-a ezartzen du.
     *
     * @param stage uneko leihoaren stage-a
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Saioa hasi duen saltzailea lortzen du.
     *
     * @return saioa hasi duen saltzailearen datuak
     */
    public static LangileSaltzaileBean getSaltzaileLogeatuta() {
        return saltzaileLogeatuta;
    }

    /**
     * Saioa hasi duen saltzailea ezartzen du.
     *
     * @param saltzaile saioa hasi duen saltzailearen datuak
     */
    public static void setSaltzaileLogeatuta(LangileSaltzaileBean saltzaile) {
        saltzaileLogeatuta = saltzaile;
    }

    /**
     * Saioa hasi duen bezeroa lortzen du.
     *
     * @return saioa hasi duen bezeroaren datuak
     */
    public static BezeroBean getBezeroLogeatuta() {
        return bezeroLogeatuta;
    }
    
    /**
     * Saioa hasi duen bezeroa ezartzen du.
     *
     * @param bezero saioa hasi duen bezeroaren datuak
     */
    public static void setBezeroLogeatuta(BezeroBean bezero) {
        bezeroLogeatuta = bezero;
    }

    /**
     * Uneko leihoa ixten du.
     */
    protected void itxiOraingoLeihoa() {
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * Uneko leihoa erakusten du (okultatu ondoren berriz ikusteko).
     */
    protected void ikusiLeihoa() {
        if (stage != null) {
            stage.show();
        }
    }

    /**
     * Uneko leihoa ezkutatzen du.
     */
    protected void okultatuLeihoa() {
        if (stage != null) {
            stage.hide();
        }
    }

    /**
     * Saltzaile menu nagusia irekitzen du.
     */
    protected void irekiSaltzaileMenuPrintzipala() {
        try {
            SaltzaileMenuPrintzipala saltzaileMenu = new SaltzaileMenuPrintzipala();
            Stage newStage = new Stage();

            if (saltzaileLogeatuta != null) {
                saltzaileMenu.setSaltzaileData(saltzaileLogeatuta);
            }

            saltzaileMenu.start(newStage);
            itxiOraingoLeihoa();

        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea saltzailearen menua irekitzean: " + e.getMessage());
        }
    }

    /**
     * Bezero menu nagusia irekitzen du.
     */
    protected void irekiBezeroMenuPrintzipala() {
        try {
            BezeroMenuPrintzipala bezeroMenu = new BezeroMenuPrintzipala();
            Stage newStage = new Stage();
            bezeroMenu.start(newStage);
            itxiOraingoLeihoa();
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea bezeroaren menua irekitzean: " + e.getMessage());
        }
    }

    /**
     * Login leihoa irekitzen du.
     */
    protected void irekiLogina() {
        try {
            Login logina = new Login();
            Stage newStage = new Stage();
            logina.start(newStage);
        } catch (Exception e) {
            e.printStackTrace();
            irekiAlerta("Errorea", "Ezin izan da leihoa ireki",
                    "Errorea loginaren leihoa irekitzean: " + e.getMessage());
        }
    }

    /**
     * Errore alerta irekitzen du.
     *
     * @param titulua alertaren titulua
     * @param goiburua alertaren goiburua
     * @param mezua alertaren mezua
     */
    protected void irekiAlerta(String titulua, String goiburua, String mezua) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulua);
        alert.setHeaderText(goiburua);
        alert.setContentText(mezua);
        alert.showAndWait();
    }

    // ============================================================
    // DATU-BASEKO KONTSULTAK
    // ============================================================

    /** Bezeroen bista */
    private static final String BEZERO_TAULA = "SELECT * FROM BISTA_BEZERO_BEZEROTELEFONO";

    /**
     * Bezero guztiak datu-basean bilatzen ditu.
     *
     * @return Bezeroen zerrenda
     */
    protected ArrayList<BezeroBean> jasoBezeroak() {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        Statement stmt = null;
        ResultSet rs = null;
        BezeroBean erregistro;
        ArrayList<BezeroBean> bezeroTaula = new ArrayList<BezeroBean>();

        try {
            konexioa = db.konektorea();
            stmt = konexioa.createStatement();
            rs = stmt.executeQuery(BEZERO_TAULA);

            while (rs.next()) {
                erregistro = new BezeroBean();
                erregistro.setId(rs.getInt("ID"));
                erregistro.setIzena(rs.getString("IZENA"));
                erregistro.setAbizena(rs.getString("ABIZENA"));
                erregistro.setNan(rs.getString("NAN"));
                erregistro.setEmaila(rs.getString("EMAILA"));
                erregistro.setHelbidea(rs.getString("HELBIDEA"));
                erregistro.setZenbakia(rs.getString("ZENBAKIA"));
                erregistro.setErabiltzaile(rs.getString("ERABILTZAILEA"));
                erregistro.setPasahitza(rs.getString("PASAHITZA"));
                bezeroTaula.add(erregistro);
            }
        } catch (SQLException e) {
            System.out.println("Errorea: " + e);
        } finally {
            itxiRekurtsuak(rs, stmt, konexioa);
        }
        return bezeroTaula;
    }

    /** Saltzaileen bista */
    private static final String LANGILESALTZAILE_TAULA = "SELECT * FROM BISTA_LANGILESALTZAILE";

    /**
     * Saltzaile guztiak datu-basean bilatzen ditu (Langile taularekin INNER JOIN).
     *
     * @return Saltzaileen zerrenda informazio osagarriarekin
     */
    protected ArrayList<LangileSaltzaileBean> jasoLangileSaltzaileak() {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        Statement stmt = null;
        ResultSet rs = null;
        LangileSaltzaileBean erregistro;
        ArrayList<LangileSaltzaileBean> saltzaileTaula = new ArrayList<LangileSaltzaileBean>();

        try {
            konexioa = db.konektorea();
            stmt = konexioa.createStatement();
            rs = stmt.executeQuery(LANGILESALTZAILE_TAULA);
            while (rs.next()) {
                erregistro = new LangileSaltzaileBean();
                erregistro.setId(rs.getInt("ID"));
                erregistro.setLangileIzenaAbizena(rs.getString("LANGILE_IZENA_ABIZENAK"));
                erregistro.setEmaila(rs.getString("EMAILA"));
                erregistro.setTelefonoa(rs.getString("TELEFONOA"));
                erregistro.setKontratazio_data(rs.getDate("KONTRATAZIO_DATA"));
                erregistro.setId_nagusi(rs.getInt("ID_NAGUSI"));
                erregistro.setNagusiIzenaAbizena(rs.getString("NAGUSI_IZENA_ABIZENAK"));
                erregistro.setSoldata(rs.getInt("SOLDATA"));
                erregistro.setErabiltzailea(rs.getString("ERABILTZAILEA"));
                erregistro.setPasahitza(rs.getString("PASAHITZA"));
                saltzaileTaula.add(erregistro);
            }
        } catch (SQLException e) {
            System.out.println("Errorea: " + e);
        } finally {
            itxiRekurtsuak(rs, stmt, konexioa);
        }
        return saltzaileTaula;
    }

    /** Eguneraketen bista */
    private static final String EGUNERAKETAK_TAULA = "SELECT * FROM BISTA_EGUNERAKETAK";

    /**
     * Eguneraketa guztiak datu-basean bilatzen ditu.
     *
     * @return Eguneraketen zerrenda
     */
    protected ArrayList<EguneraketakBean> jasoEguneraketak() {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        Statement stmt = null;
        ResultSet rs = null;
        EguneraketakBean erregistro;
        ArrayList<EguneraketakBean> eguneraketakTaula = new ArrayList<EguneraketakBean>();

        try {
            konexioa = db.konektorea();
            stmt = konexioa.createStatement();
            rs = stmt.executeQuery(EGUNERAKETAK_TAULA);

            while (rs.next()) {
                erregistro = new EguneraketakBean();
                erregistro.setKontrola(rs.getString("KONTROLA"));
                eguneraketakTaula.add(erregistro);
            }
        } catch (SQLException e) {
            System.out.println("Errorea: " + e);
        } finally {
            itxiRekurtsuak(rs, stmt, konexioa);
        }
        return eguneraketakTaula;
    }

    /** Txertaketen eta ezabaketen bista */
    private static final String TXERTAKETAK_EZABAKETAK_TAULA = "SELECT * FROM BISTA_TXERTAKETAK_EZABAKETAK";

    /**
     * Txertaketa eta ezabaketa guztiak datu-basean bilatzen ditu.
     *
     * @return Txertaketen eta ezabaketen zerrenda
     */
    protected ArrayList<TxertaketakEzabaketakKontrolaBean> jasoTxertaketakEzabaketak() {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        Statement stmt = null;
        ResultSet rs = null;
        TxertaketakEzabaketakKontrolaBean erregistro;
        ArrayList<TxertaketakEzabaketakKontrolaBean> txertaketakEzabaketakTaula = new ArrayList<TxertaketakEzabaketakKontrolaBean>();

        try {
            konexioa = db.konektorea();
            stmt = konexioa.createStatement();
            rs = stmt.executeQuery(TXERTAKETAK_EZABAKETAK_TAULA);

            while (rs.next()) {
                erregistro = new TxertaketakEzabaketakKontrolaBean();
                erregistro.setTxertaketak(rs.getString("TXERTAKETAK"));
                erregistro.setEzabaketak(rs.getString("EZABAKETAK"));
                txertaketakEzabaketakTaula.add(erregistro);
            }
        } catch (SQLException e) {
            System.out.println("Errorea: " + e);
        } finally {
            itxiRekurtsuak(rs, stmt, konexioa);
        }
        return txertaketakEzabaketakTaula;
    }

    /**
     * Bezero jakin baten produktu eskaerak bilatzen ditu.
     *
     * @param izena bezeroaren izena
     * @param abizena bezeroaren abizena
     * @return Eskaeren zerrenda
     */
    protected ArrayList<EskaerakBean> jasoBezeroenProduktuEskaerak(String izena, String abizena) {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        EskaerakBean erregistro;
        ArrayList<EskaerakBean> produktuEskaeraTaula = new ArrayList<EskaerakBean>();

        String sql = "{CALL BEZERO_ESKAERAK_INFORMAZIOA(?, ?)}";

        try {
            konexioa = db.konektorea();
            cs = konexioa.prepareCall(sql);
            cs.setString(1, izena);
            cs.setString(2, abizena);
            rs = cs.executeQuery();

            while (rs.next()) {
                erregistro = new EskaerakBean();
                erregistro.setProduktu(rs.getString("PRODUKTU"));
                erregistro.setKategoria(rs.getString("KATEGORIA"));
                erregistro.setKopurua(rs.getInt("KOPURUA"));
                erregistro.setEskaera_data(rs.getDate("DATA"));
                erregistro.setDeskribapena(rs.getString("DESKRIBAPEN"));
                erregistro.setSaltzailea(rs.getString("SALTZAILEA"));
                produktuEskaeraTaula.add(erregistro);
            }
        } catch (SQLException e) {
            System.out.println("Errorea: " + e);
        } finally {
            itxiRekurtsuak(rs, cs, konexioa);
        }
        return produktuEskaeraTaula;
    }

    /**
     * Kategoria jakin bateko produktuak bilatzen ditu.
     *
     * @param kategoria produktuen kategoria
     * @return Produktuen zerrenda
     */
    protected ArrayList<ProduktuBean> jasoProduktuak(String kategoria) {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ProduktuBean erregistro;
        ArrayList<ProduktuBean> produktuTaula = new ArrayList<ProduktuBean>();

        String sql = "{CALL KATEGORIA_PRODUKTU_INFO (?)}";

        try {
            konexioa = db.konektorea();
            cs = konexioa.prepareCall(sql);
            cs.setString(1, kategoria);
            rs = cs.executeQuery();

            while (rs.next()) {
                erregistro = new ProduktuBean();
                erregistro.setId(rs.getInt("ID"));
                erregistro.setIzena(rs.getString("PRODUKTU_IZENA"));
                erregistro.setDeskribapena(rs.getString("DESKRIBAPENA"));
                erregistro.setSalneurria(rs.getDouble("SALNEURRIA"));
                produktuTaula.add(erregistro);
            }
        } catch (SQLException e) {
            System.out.println("Errorea: " + e);
        } finally {
            itxiRekurtsuak(rs, cs, konexioa);
        }
        return produktuTaula;
    }

    // ============================================================
    // TXERTAKETAK
    // ============================================================

    /**
     * Bezero berria erregistratzen du (erabiltzaile eta pasahitzarekin soilik).
     *
     * @param erabiltzailea bezero berriaren erabiltzailea
     * @param pasahitza bezero berriaren pasahitza
     */
    protected void erregistroBezeroBerriaSortu(String erabiltzailea, String pasahitza) {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        CallableStatement cs = null;

        String sql = "{CALL ERREGISTROA(?, ?)}";

        try {
            konexioa = db.konektorea();
            cs = konexioa.prepareCall(sql);
            cs.setString(1, erabiltzailea);
            cs.setString(2, pasahitza);
            cs.execute();
        } catch (SQLException e) {
            System.out.println("Errorea: " + e);
        } finally {
            itxiRekurtsuak(null, cs, konexioa);
        }
    }

    /**
     * Bezero berria txertatzen du datu-basean.
     *
     * @param izena bezeroaren izena
     * @param abizena bezeroaren abizena
     * @param nan bezeroaren NANa
     * @param emaila bezeroaren emaila
     * @param helbidea bezeroaren helbidea
     * @param erabiltzailea bezeroaren erabiltzailea
     * @param pasahitza bezeroaren pasahitza
     * @param zenbakia bezeroaren telefono zenbakia
     */
    protected void txertatuBezeroa(String izena, String abizena, String nan, String emaila, String helbidea,
            String erabiltzailea, String pasahitza, String zenbakia) {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        CallableStatement cs = null;

        String sql = "{CALL BEZERO_GEHIKETA(?, ?, ?, ?, ?, ?, ?, ?)}";

        try {
            konexioa = db.konektorea();
            cs = konexioa.prepareCall(sql);
            cs.setString(1, izena);
            cs.setString(2, abizena);
            cs.setString(3, nan);
            cs.setString(4, emaila);
            cs.setString(5, helbidea);
            cs.setString(6, erabiltzailea);
            cs.setString(7, pasahitza);
            cs.setString(8, zenbakia);
            cs.execute();
        } catch (SQLException e) {
            System.out.println("Errorea: " + e);
        } finally {
            itxiRekurtsuak(null, cs, konexioa);
        }
    }

    /**
     * Saltzaile berria txertatzen du datu-basean.
     *
     * @param izena saltzailearen izena
     * @param abizena saltzailearen abizena
     * @param erabiltzailea saltzailearen erabiltzailea
     * @param pasahitza saltzailearen pasahitza
     * @param emaila saltzailearen emaila
     * @param telefonoa saltzailearen telefonoa
     * @param soldata saltzailearen soldata
     */
    protected void txertatuSaltzailea(String izena, String abizena, String erabiltzailea, String pasahitza,
            String emaila, String telefonoa, int soldata) {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        CallableStatement cs = null;

        String sql = "{CALL SALTZAILE_GEHIKETA(?, ?, ?, ?, ?, ?, ?)}";

        try {
            konexioa = db.konektorea();
            cs = konexioa.prepareCall(sql);
            cs.setString(1, izena);
            cs.setString(2, abizena);
            cs.setString(3, erabiltzailea);
            cs.setString(4, pasahitza);
            cs.setString(5, emaila);
            cs.setString(6, telefonoa);
            cs.setInt(7, soldata);
            cs.execute();
        } catch (SQLException e) {
            System.out.println("Errorea: " + e);
        } finally {
            itxiRekurtsuak(null, cs, konexioa);
        }
    }

    // ============================================================
    // EGUNERAKETAK
    // ============================================================

    /**
     * Saltzaile baten datuak eguneratzen ditu.
     *
     * @param id saltzailearen IDa
     * @param izena saltzailearen izena
     * @param abizena saltzailearen abizena
     * @param erabiltzailea saltzailearen erabiltzailea
     * @param pasahitza saltzailearen pasahitza
     * @param emaila saltzailearen emaila
     * @param telefonoa saltzailearen telefonoa
     * @param soldata saltzailearen soldata
     */
    protected void eguneratuSaltzailea(int id, String izena, String abizena, String erabiltzailea, String pasahitza,
            String emaila, String telefonoa, int soldata) {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        CallableStatement cs = null;

        String sql = "{CALL SALTZAILE_ALDAKETA(?, ?, ?, ?, ?, ?, ?, ?)}";

        try {
            konexioa = db.konektorea();
            cs = konexioa.prepareCall(sql);
            cs.setInt(1, id);
            cs.setString(2, izena);
            cs.setString(3, abizena);
            cs.setString(4, erabiltzailea);
            cs.setString(5, pasahitza);
            cs.setString(6, emaila);
            cs.setString(7, telefonoa);
            cs.setInt(8, soldata);
            cs.execute();
        } catch (SQLException e) {
            System.out.println("Errorea saltzailea eguneratzean: " + e);
            irekiAlerta("Errorea", "Ezin izan da saltzailea eguneratu", "Datu-baseko errorea: " + e.getMessage());
        } finally {
            itxiRekurtsuak(null, cs, konexioa);
        }
    }

    /**
     * Bezero baten datuak eguneratzen ditu.
     *
     * @param id bezeroaren IDa
     * @param izena bezeroaren izena
     * @param abizena bezeroaren abizena
     * @param nan bezeroaren NANa
     * @param emaila bezeroaren emaila
     * @param helbidea bezeroaren helbidea
     * @param erabiltzailea bezeroaren erabiltzailea
     * @param pasahitza bezeroaren pasahitza
     * @param zenbakia bezeroaren telefonoa
     */
    protected void eguneratuBezeroa(int id, String izena, String abizena, String nan, String emaila, String helbidea,
            String erabiltzailea, String pasahitza, String zenbakia) {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        CallableStatement cs = null;

        String sql = "{CALL BEZERO_ALDAKETA(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try {
            konexioa = db.konektorea();
            cs = konexioa.prepareCall(sql);
            cs.setInt(1, id);
            cs.setString(2, izena);
            cs.setString(3, abizena);
            cs.setString(4, nan);
            cs.setString(5, emaila);
            cs.setString(6, helbidea);
            cs.setString(7, erabiltzailea);
            cs.setString(8, pasahitza);
            cs.setString(9, zenbakia);
            cs.execute();
        } catch (SQLException e) {
            System.out.println("Errorea bezeroa eguneratzean: " + e);
            irekiAlerta("Errorea", "Ezin izan da bezeroa eguneratu", "Datu-baseko errorea: " + e.getMessage());
        } finally {
            itxiRekurtsuak(null, cs, konexioa);
        }
    }

    // ============================================================
    // EZABAKETAK
    // ============================================================

    /**
     * Saltzaile bat ezabatzen du datu-basean.
     *
     * @param id ezabatu nahi den saltzailearen IDa
     */
    protected void ezabatuSaltzailea(int id) {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        CallableStatement cs = null;

        String sql = "{CALL SALTZAILE_EZABATU(?)}";

        try {
            konexioa = db.konektorea();
            cs = konexioa.prepareCall(sql);
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) {
            System.out.println("Errorea saltzailea ezabatzean: " + e);
            irekiAlerta("Errorea", "Ezin izan da saltzailea ezabatu", "Datu-baseko errorea: " + e.getMessage());
        } finally {
            itxiRekurtsuak(null, cs, konexioa);
        }
    }

    /**
     * Bezero bat ezabatzen du datu-basean.
     *
     * @param id ezabatu nahi den bezeroaren IDa
     */
    protected void ezabatuBezeroa(int id) {
        Konexioa db = new Konexioa();
        Connection konexioa = null;
        CallableStatement cs = null;

        String sql = "{CALL BEZERO_EZABATU(?)}";

        try {
            konexioa = db.konektorea();
            cs = konexioa.prepareCall(sql);
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) {
            System.out.println("Errorea bezeroa ezabatzean: " + e);
            irekiAlerta("Errorea", "Ezin izan da bezeroa ezabatu", "Datu-baseko errorea: " + e.getMessage());
        } finally {
            itxiRekurtsuak(null, cs, konexioa);
        }
    }

    // ============================================================
    // BEZERO LEIHOETAKO METODOAK
    // ============================================================

    /**
     * Kategoriako produktuen taularen zutabeak konfiguratzen ditu.
     *
     * @param izenaColumn produktuaren izenaren zutabea
     * @param deskribapenaColumn deskribapenaren zutabea
     * @param salneurriaColumn salneurriaren zutabea
     */
    protected void konfiguratuKategoriaZutabeak(TableColumn<ProduktuBean, String> izenaColumn,
            TableColumn<ProduktuBean, String> deskribapenaColumn, TableColumn<ProduktuBean, Double> salneurriaColumn) {
        izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));
        deskribapenaColumn.setCellValueFactory(new PropertyValueFactory<>("deskribapena"));
        salneurriaColumn.setCellValueFactory(new PropertyValueFactory<>("salneurria"));
    }

    /**
     * Kategoriako produktuak kargatzen ditu eta kantitate mapa hasieratzen du.
     *
     * @param kategoriaList produktuen zerrenda
     * @param filtrazioList iragazitako produktuen zerrenda
     * @param kantitateak produktuen kantitateen mapa
     * @param kategoria kategoria
     */
    protected void kargatuKategoriaDatuak(ObservableList<ProduktuBean> kategoriaList,
            ObservableList<ProduktuBean> filtrazioList, Map<Integer, Integer> kantitateak, String kategoria) {
        ArrayList<ProduktuBean> kategoriaProduktuak = jasoProduktuak(kategoria);
        kategoriaList.setAll(kategoriaProduktuak);
        filtrazioList.setAll(kategoriaList);

        for (ProduktuBean produktu : kategoriaList) {
            kantitateak.putIfAbsent(produktu.getId(), 0);
        }
    }

    // ============================================================
    // PAGINAZIO METODOAK (BEZERO KATEGORIA)
    // ============================================================

    /**
     * Bezero kategoriako produktuen paginazioa konfiguratzen du.
     *
     * @param pagination paginazio kontrola
     * @param filtrazioList iragazitako produktuen zerrenda
     * @param tableView taula
     */
    protected void konfiguratuBezeroKategoriaPaginazioa(Pagination pagination,
            ObservableList<ProduktuBean> filtrazioList, TableView<ProduktuBean> tableView) {
        int orriGeiketa = (int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE);
        pagination.setPageCount(orriGeiketa == 0 ? 1 : orriGeiketa);
        pagination.setCurrentPageIndex(0);

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, indexBerria) -> {
            eguneratuBezeroKategoriaOrriaDatuak(indexBerria.intValue(), filtrazioList, tableView);
        });

        eguneratuBezeroKategoriaOrriaDatuak(0, filtrazioList, tableView);
    }

    /**
     * Bezero kategoriako orriko datuak eguneratzen ditu.
     *
     * @param indexOrria orriaren indizea
     * @param filtrazioList iragazitako produktuen zerrenda
     * @param tableView taula
     */
    protected void eguneratuBezeroKategoriaOrriaDatuak(int indexOrria, ObservableList<ProduktuBean> filtrazioList,
            TableView<ProduktuBean> tableView) {
        int lehengoErregistroa = indexOrria * ROWS_PER_PAGE;
        int azkenErregistroa = Math.min(lehengoErregistroa + ROWS_PER_PAGE, filtrazioList.size());

        if (lehengoErregistroa < filtrazioList.size()) {
            List<ProduktuBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
            tableView.setItems(FXCollections.observableArrayList(pageData));
        } else {
            tableView.setItems(FXCollections.observableArrayList());
        }
    }

    // ============================================================
    // PAGINAZIO METODOAK (SALTZAILEA)
    // ============================================================

    /**
     * Saltzaileen paginazioa konfiguratzen du.
     *
     * @param pagination paginazio kontrola
     * @param filtrazioList iragazitako saltzaileen zerrenda
     * @param tableView taula
     */
    protected void konfiguratuSaltzailePaginazioa(Pagination pagination,
            ObservableList<LangileSaltzaileBean> filtrazioList, TableView<LangileSaltzaileBean> tableView) {
        int orriGeiketa = (int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE);
        pagination.setPageCount(orriGeiketa == 0 ? 1 : orriGeiketa);
        pagination.setCurrentPageIndex(0);

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, indexBerria) -> {
            eguneratuSaltzaileOrriaDatuak(indexBerria.intValue(), filtrazioList, tableView);
        });

        eguneratuSaltzaileOrriaDatuak(0, filtrazioList, tableView);
    }

    /**
     * Saltzaileen orriko datuak eguneratzen ditu.
     *
     * @param indexOrria orriaren indizea
     * @param filtrazioList iragazitako saltzaileen zerrenda
     * @param tableView taula
     */
    protected void eguneratuSaltzaileOrriaDatuak(int indexOrria, ObservableList<LangileSaltzaileBean> filtrazioList,
            TableView<LangileSaltzaileBean> tableView) {
        int lehengoErregistroa = indexOrria * ROWS_PER_PAGE;
        int azkenErregistroa = Math.min(lehengoErregistroa + ROWS_PER_PAGE, filtrazioList.size());

        if (lehengoErregistroa < filtrazioList.size()) {
            List<LangileSaltzaileBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
            tableView.setItems(FXCollections.observableArrayList(pageData));
        } else {
            tableView.setItems(FXCollections.observableArrayList());
        }
    }

    // ============================================================
    // PAGINAZIO METODOAK (BEZEROA)
    // ============================================================

    /**
     * Bezeroen paginazioa konfiguratzen du.
     *
     * @param pagination paginazio kontrola
     * @param filtrazioList iragazitako bezeroen zerrenda
     * @param tableView taula
     */
    protected void konfiguratuBezeroPaginazioa(Pagination pagination, ObservableList<BezeroBean> filtrazioList,
            TableView<BezeroBean> tableView) {
        int orriGeiketa = (int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE);
        pagination.setPageCount(orriGeiketa == 0 ? 1 : orriGeiketa);
        pagination.setCurrentPageIndex(0);

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, indexBerria) -> {
            eguneratuBezeroOrriaDatuak(indexBerria.intValue(), filtrazioList, tableView);
        });

        eguneratuBezeroOrriaDatuak(0, filtrazioList, tableView);
    }

    /**
     * Bezeroen orriko datuak eguneratzen ditu.
     *
     * @param indexOrria orriaren indizea
     * @param filtrazioList iragazitako bezeroen zerrenda
     * @param tableView taula
     */
    protected void eguneratuBezeroOrriaDatuak(int indexOrria, ObservableList<BezeroBean> filtrazioList,
            TableView<BezeroBean> tableView) {
        int lehengoErregistroa = indexOrria * ROWS_PER_PAGE;
        int azkenErregistroa = Math.min(lehengoErregistroa + ROWS_PER_PAGE, filtrazioList.size());

        if (lehengoErregistroa < filtrazioList.size()) {
            List<BezeroBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
            tableView.setItems(FXCollections.observableArrayList(pageData));
        } else {
            tableView.setItems(FXCollections.observableArrayList());
        }
    }

    // ============================================================
    // PAGINAZIO METODOAK (ESKAERAK)
    // ============================================================

    /**
     * Eskaeren paginazioa konfiguratzen du.
     *
     * @param pagination paginazio kontrola
     * @param filtrazioList iragazitako eskaeren zerrenda
     * @param tableView taula
     */
    protected void konfiguratuEskaerakPaginazioa(Pagination pagination, ObservableList<EskaerakBean> filtrazioList,
            TableView<EskaerakBean> tableView) {
        int orriGeiketa = (int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE);
        pagination.setPageCount(orriGeiketa == 0 ? 1 : orriGeiketa);
        pagination.setCurrentPageIndex(0);

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, indexBerria) -> {
            eguneratuEskaerakOrriaDatuak(indexBerria.intValue(), filtrazioList, tableView);
        });

        eguneratuEskaerakOrriaDatuak(0, filtrazioList, tableView);
    }

    /**
     * Eskaeren orriko datuak eguneratzen ditu.
     *
     * @param indexOrria orriaren indizea
     * @param filtrazioList iragazitako eskaeren zerrenda
     * @param tableView taula
     */
    protected void eguneratuEskaerakOrriaDatuak(int indexOrria, ObservableList<EskaerakBean> filtrazioList,
            TableView<EskaerakBean> tableView) {
        int lehengoErregistroa = indexOrria * ROWS_PER_PAGE;
        int azkenErregistroa = Math.min(lehengoErregistroa + ROWS_PER_PAGE, filtrazioList.size());

        if (lehengoErregistroa < filtrazioList.size()) {
            List<EskaerakBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
            tableView.setItems(FXCollections.observableArrayList(pageData));
        } else {
            tableView.setItems(FXCollections.observableArrayList());
        }
    }

    // ============================================================
    // PAGINAZIO METODOAK (ABISUAK)
    // ============================================================

    /**
     * Abisuen paginazioa konfiguratzen du.
     *
     * @param pagination paginazio kontrola
     * @param filtrazioList iragazitako abisuen zerrenda
     * @param tableView taula
     */
    protected void konfiguratuAbisuakPaginazioa(Pagination pagination, ObservableList<AbisuakBean> filtrazioList,
            TableView<AbisuakBean> tableView) {
        int orriGeiketa = (int) Math.ceil((double) filtrazioList.size() / ROWS_PER_PAGE);
        pagination.setPageCount(orriGeiketa == 0 ? 1 : orriGeiketa);
        pagination.setCurrentPageIndex(0);

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, indexBerria) -> {
            eguneratuAbisuakOrriaDatuak(indexBerria.intValue(), filtrazioList, tableView);
        });

        eguneratuAbisuakOrriaDatuak(0, filtrazioList, tableView);
    }

    /**
     * Abisuen orriko datuak eguneratzen ditu.
     *
     * @param indexOrria orriaren indizea
     * @param filtrazioList iragazitako abisuen zerrenda
     * @param tableView taula
     */
    protected void eguneratuAbisuakOrriaDatuak(int indexOrria, ObservableList<AbisuakBean> filtrazioList,
            TableView<AbisuakBean> tableView) {
        int lehengoErregistroa = indexOrria * ROWS_PER_PAGE;
        int azkenErregistroa = Math.min(lehengoErregistroa + ROWS_PER_PAGE, filtrazioList.size());

        if (lehengoErregistroa < filtrazioList.size()) {
            List<AbisuakBean> pageData = filtrazioList.subList(lehengoErregistroa, azkenErregistroa);
            tableView.setItems(FXCollections.observableArrayList(pageData));
        } else {
            tableView.setItems(FXCollections.observableArrayList());
        }
    }

    // ============================================================
    // ERREKURTSOAK IXTEKO METODO PRIBATUAK
    // ============================================================

    /**
     * ResultSet, Statement eta Connection itxi egiten ditu.
     *
     * @param rs itxi nahi den ResultSet
     * @param stmt itxi nahi den Statement
     * @param konexioa itxi nahi den Connection
     */
    private void itxiRekurtsuak(ResultSet rs, Statement stmt, Connection konexioa) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (konexioa != null) konexioa.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ResultSet, CallableStatement eta Connection itxi egiten ditu.
     *
     * @param rs itxi nahi den ResultSet
     * @param cs itxi nahi den CallableStatement
     * @param konexioa itxi nahi den Connection
     */
    private void itxiRekurtsuak(ResultSet rs, CallableStatement cs, Connection konexioa) {
        try {
            if (rs != null) rs.close();
            if (cs != null) cs.close();
            if (konexioa != null) konexioa.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}