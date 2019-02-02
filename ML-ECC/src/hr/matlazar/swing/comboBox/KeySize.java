package hr.matlazar.swing.comboBox;

import java.util.ArrayList;
import java.util.List;

public class KeySize {
	
	private List<String> keySize = new ArrayList<>();
	
	public KeySize() {
		keySize.add("secp192k1");
		keySize.add("secp192r1");
		keySize.add("secp224k1");
		keySize.add("secp224r1");
		keySize.add("secp256k1");
		keySize.add("secp256r1");
		keySize.add("secp384r1");
		keySize.add("secp521r1");
	}
	
	public List<String> getKeySize() {
		return keySize;
	}
	
}
