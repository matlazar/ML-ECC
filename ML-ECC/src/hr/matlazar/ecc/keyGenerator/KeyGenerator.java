package hr.matlazar.ecc.keyGenerator;

import java.math.BigInteger;
import java.util.Base64;
import java.util.Random;

import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.domains.KeyPair;

public class KeyGenerator {
	
	Random rnd = new Random(); 
	 
	public BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
	public BigInteger g = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	public BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	
	public KeyPair generateKeys() {
		
		KeyPair keyPair = new KeyPair();
			
		BigInteger d = new BigInteger(n.bitLength(), rnd);
		keyPair.setPrivateKey(Base64.getEncoder().encodeToString(d.toByteArray()));
			
		BigInteger Q = d.multiply(g);
		keyPair.setPublicKey(Base64.getEncoder().encodeToString(Q.toByteArray()));

		return keyPair;
	}
	
}
