package hr.matlazar.swing.buttonAction;

import hr.matlazar.ecc.domains.KeyPair;
import hr.matlazar.ecc.keyGenerator.KeyGenerator;

public class GenerateKeys {
	
	public static KeyPair getKeys(String option) {
		KeyGenerator keyGenerator = new KeyGenerator(option);
		return keyGenerator.generateKeys();
	}
	
}
