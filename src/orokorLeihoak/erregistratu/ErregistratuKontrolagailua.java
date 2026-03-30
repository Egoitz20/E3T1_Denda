package orokorLeihoak.erregistratu;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import partekatutakoMetodoak.ErrepikatutakoMetodoak;

public class ErregistratuKontrolagailua extends ErrepikatutakoMetodoak{


	private TextField erabiltzaileBerria;
	private PasswordField pasahitzaBerria;
	private PasswordField errepikatuPasahitzaBerria;
	
	public ErregistratuKontrolagailua() {
	}
	
	public void itzuli() {
		
	}
	
	public void sortuErabiltzailea() {
		String jasotakoErabiltzaileBerria = erabiltzaileBerria.getText();
		String jasotakoPasahitzaBerria = pasahitzaBerria.getText();
		String jasotakoErrepikatuPasahitzaBerria = errepikatuPasahitzaBerria.getText();
		
		if (jasotakoPasahitzaBerria.equals(jasotakoErrepikatuPasahitzaBerria)) {
	
		}
		
	} 
		

	

}
