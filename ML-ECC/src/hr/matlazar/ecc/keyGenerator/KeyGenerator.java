package hr.matlazar.ecc.keyGenerator;

import java.math.BigInteger;
import java.util.Base64;
import java.util.Random;

import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.domains.KeyPair;

public class KeyGenerator {
	
	Random rnd = new Random();  
	
	BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
	BigInteger g = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	
	
	public KeyGenerator(String option) {
		
		switch(option) {
			case "secp192k1":
				p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
				break;
			case "secp192r1":
				p = new BigInteger(DomainParameters.secp192r1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp192r1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192r1_n.replaceAll(" ", ""), 16);
				break;
			case "secp224k1":
				p = new BigInteger(DomainParameters.secp224k1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp224k1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp224k1_n.replaceAll(" ", ""), 16);
				break;
			case "secp224r1":
				p = new BigInteger(DomainParameters.secp224r1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp224r1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp224r1_n.replaceAll(" ", ""), 16);
				break;
			case "secp256k1":
				p = new BigInteger(DomainParameters.secp256k1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp256k1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp256k1_n.replaceAll(" ", ""), 16);
				break;
			case "secp256r1":
				p = new BigInteger(DomainParameters.secp256r1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp256r1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp256r1_n.replaceAll(" ", ""), 16);
				break;
			case "secp384r1":
				p = new BigInteger(DomainParameters.secp384r1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp384r1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp384r1_n.replaceAll(" ", ""), 16);
				break;
			case "secp521r1":
				p = new BigInteger(DomainParameters.secp521r1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp521r1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp521r1_n.replaceAll(" ", ""), 16);
				break;
			default:	
				p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
		}
		
	}

	public KeyPair generateKeys() {

		KeyPair keyPair = new KeyPair();
			
		BigInteger d = new BigInteger(n.bitLength(), rnd);
		keyPair.setPrivateKey(Base64.getEncoder().encodeToString(d.toByteArray()));
		
		BigInteger Q = d.multiply(g);
		keyPair.setPublicKey(Base64.getEncoder().encodeToString(Q.toByteArray()));
		
		return keyPair;
	}
	
	public KeyPair DHgenerateKeys() {
		
		KeyPair keyPair = new KeyPair();
			
		BigInteger d = new BigInteger(n.bitLength(), rnd);
		keyPair.setPrivateKey(Base64.getEncoder().encodeToString(d.toByteArray()));
			
		BigInteger Q = g.modPow(d, p);
		keyPair.setPublicKey(Base64.getEncoder().encodeToString(Q.toByteArray()));

		return keyPair;
	}
	
}
