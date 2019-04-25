package hr.matlazar.swing.comboBox;

import java.util.ArrayList;
import java.util.List;

public class DHType {
	
	private List<String> dhType = new ArrayList<>();
	
	public DHType() {
		dhType.add("Compressed-Diffie-Helman");
		dhType.add("Decompressed-Diffie-Hellman");
	}
	
	public List<String> getDhType() {
		return dhType;
	}

}
