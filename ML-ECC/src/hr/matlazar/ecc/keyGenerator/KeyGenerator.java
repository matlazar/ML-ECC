package hr.matlazar.ecc.keyGenerator;

import java.math.BigInteger;
import java.util.Random;

import hr.matlazar.ecc.constants.DomainParameters;

public class KeyGenerator {
	
	Random rnd = new Random(); 
	 
	public BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
	public BigInteger g = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	public BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	
	public BigInteger generatePrivateKey() {
		BigInteger privateKey = new BigInteger(n.bitLength(), rnd);
		return privateKey;
	}
	
	public BigInteger generatePublicKey(BigInteger privateKey) {
		BigInteger publicKey = privateKey.multiply(g);
		return publicKey;
	}
	
}
