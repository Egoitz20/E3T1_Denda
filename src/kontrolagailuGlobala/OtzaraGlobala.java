package kontrolagailuGlobala;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import datuBaseKonexioa.ProduktuBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OtzaraGlobala {

	private static OtzaraGlobala instantzia;
	private final ObservableList<OtzaraItem> produktuak = FXCollections.observableArrayList();

	private OtzaraGlobala() {
	}

	public static OtzaraGlobala getInstantzia() {
		if (instantzia == null) {
			instantzia = new OtzaraGlobala();
		}
		return instantzia;
	}

	public ObservableList<OtzaraItem> getProduktuak() {
		return produktuak;
	}

	public void gehituProduktua(ProduktuBean produktua, int kantitatea) {
		// Bilatu produktua dagoen aldez
		for (OtzaraItem item : produktuak) {
			if (item.getId() == produktua.getId()) {
				item.gehituKantitatea();
				return;
			}
		}
		// Produktua ez badago, sortu berria
		produktuak.add(new OtzaraItem(produktua, kantitatea));
	}

	public void kenduProduktua(OtzaraItem item) {
		produktuak.remove(item);
	}

	public double getTotala() {
		return produktuak.stream().mapToDouble(OtzaraItem::getGuztira).sum();
	}

	public double getIva() {
		return getTotala() * 0.21;
	}

	public double getGuztiraIva() {
		return getTotala() + getIva();
	}

	public void garbitu() {
		produktuak.clear();
	}
}