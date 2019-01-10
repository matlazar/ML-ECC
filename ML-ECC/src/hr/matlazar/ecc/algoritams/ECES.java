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
		BigInteger c = M.multiply(sharedSecret);
		c = c.mod(p);
		keyDomain.setMessage(Base64.getEncoder().encodeToString(c.toByteArray()));
		keyDomain.setSharedSecret(R);	
		
		return keyDomain;
	}
	
	public String decrypt(KeyDomain keyDomain, String q) {
		
		BigInteger privateKey = new BigInteger(Base64.getDecoder().decode(q));
		
		BigInteger x = privateKey.multiply(keyDomain.getSharedSecret());

		BigInteger c = new BigInteger(Base64.getDecoder().decode(keyDomain.getMessage()));
		
		BigInteger message = c.multiply(x.modInverse(p)).mod(p);
		
		String decrypt = new String(message.toByteArray());
		
		return decrypt;
	}
	
}
 
