package hr.matlazar.swing.comboBox;

import java.util.ArrayList;
import java.util.List;

public class Algoritham {
	
	private List<String> algoritham = new ArrayList<>();
	
	public Algoritham() {
		algoritham.add("El-Gamal");
		algoritham.add("ECIES");
	}
	
	public List<String> getAlgoritham() {
		return algoritham;
	}

}
