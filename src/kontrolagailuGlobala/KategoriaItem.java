package kontrolagailuGlobala;

import datuBaseKonexioa.ProduktuBean;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class KategoriaItem extends ProduktuBean {

	private final IntegerProperty kantitatea = new SimpleIntegerProperty(0);

	public int getKantitatea() {
		return kantitatea.get();
	}

	public void setKantitatea(int value) {
		kantitatea.set(value);
	}

	public IntegerProperty kantitateaProperty() {
		return kantitatea;
	}

	public void gehituKantitatea() {
		setKantitatea(getKantitatea() + 1);
	}

	public void kenduKantitatea() {
		if (getKantitatea() > 0) {
			setKantitatea(getKantitatea() - 1);
		}
	}
}