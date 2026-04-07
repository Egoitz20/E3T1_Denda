package kontrolagailuGlobala;

import datuBaseKonexioa.AbisuakBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AbisuakGlobala {

    private static AbisuakGlobala instantzia;
    private final ObservableList<AbisuakBean> abisuak = FXCollections.observableArrayList();

    private AbisuakGlobala() {
    }

    public static AbisuakGlobala getInstantzia() {
        if (instantzia == null) {
            instantzia = new AbisuakGlobala();
        }
        return instantzia;
    }

    public ObservableList<AbisuakBean> getAbisuak() {
        return abisuak;
    }

    public void gehituAbisua(String bezeroIzena, String kontzeptua, String deskribapena) {
        AbisuakBean abisua = new AbisuakBean(bezeroIzena, kontzeptua, deskribapena);
        abisuak.add(0, abisua); // Añadir al principio (el más reciente arriba)
    }

    public void garbitu() {
        abisuak.clear();
    }
}