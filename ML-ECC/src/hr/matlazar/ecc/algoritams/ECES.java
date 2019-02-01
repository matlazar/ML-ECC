package hr.matlazar.ecc.algoritams;

import java.math.BigInteger;
import java.util.Base64;
import java.util.Random;

import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.domains.KeyDomain;

public class ECES {
	
	
	
	BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
	BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	BigInteger G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	
	

	public ECES(String option) {
		
		switch(option) {
			case "secp192k1":
				p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
				break;
			case "secp192r1":
				p = new BigInteger(DomainParameters.secp192r1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp192r1_G.replaceAll(" ", ""), 16);
				break;
			case "secp224k1":
				p = new BigInteger(DomainParameters.secp224k1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp224k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp224k1_G.replaceAll(" ", ""), 16);
				break;
			case "secp224r1":
				p = new BigInteger(DomainParameters.secp224r1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp224r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp224r1_G.replaceAll(" ", ""), 16);
				break;
			case "secp256k1":
				p = new BigInteger(DomainParameters.secp256k1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp256k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp256k1_G.replaceAll(" ", ""), 16);
				break;
			case "secp256r1":
				p = new BigInteger(DomainParameters.secp256r1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp256r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp256r1_G.replaceAll(" ", ""), 16);
				break;
			case "secp384r1":
				p = new BigInteger(DomainParameters.secp384r1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp384r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp384r1_G.replaceAll(" ", ""), 16);
				break;
			case "secp521r1":
				p = new BigInteger(DomainParameters.secp521r1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp521r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp521r1_G.replaceAll(" ", ""), 16);
				break;
			default:
				p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
		}
		
	}

	public KeyDomain encrypt(String d, String message) {
		
		BigInteger publicKey = new BigInteger(Base64.getDecoder().decode(d));
		byte [] toByte = message.getBytes();
		BigInteger M = new BigInteger(toByte);
		BigInteger r;
        while(true)   
        {       
        	Random rnd = new Random();
            r = new BigInteger(n.bitLength(), rnd);   
            if(p.subtract(BigInteger.ONE).gcd(r).equals(BigInteger.ONE)&&r.compareTo(p) == -1)   
            { 
            break;   
            }   
        }  
		KeyDomain keyDomain = new KeyDomain();
		BigInteger R = r.multiply(G);
		BigInteger sharedSecret = r.multiply(publicKey);
		BigInteger c = M.multiply(sharedSecret).mod(p);
		keyDomain.setMessage(Base64.getEncoder().encodeToString(c.toByteArray()));
		keyDomain.setSharedSecret(Base64.getEncoder().encodeToString(R.toByteArray()));	
		
		return keyDomain;
	}
	
	public String decrypt(KeyDomain keyDomain, String q) {
		
		BigInteger privateKey = new BigInteger(Base64.getDecoder().decode(q));
		
		BigInteger ss = new BigInteger(Base64.getDecoder().decode(keyDomain.getSharedSecret()));
		
		BigInteger x = privateKey.multiply(ss);

		BigInteger c = new BigInteger(Base64.getDecoder().decode(keyDomain.getMessage()));
		
		BigInteger message = x.modInverse(p).multiply(c).mod(p);
		
		String decrypt = new String(message.toByteArray());
		
		return decrypt;
	}
	
}
 
