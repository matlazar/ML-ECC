package hr.matlazar.ecc.algoritams;

import java.math.BigInteger;
import java.util.Random;

import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.domains.ElGamalSend;

public class ElGamal {

	BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
	BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	BigInteger G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	
	public ElGamalSend encrtypt(BigInteger publicKey, String message) {
		
		ElGamalSend elGamalSend = new ElGamalSend();
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
        
        elGamalSend.setR(r.multiply(G));
        elGamalSend.setSharedSecret(M.add(r.multiply(publicKey)));
        
        
		return elGamalSend;
	}
	
	public String decrypt(ElGamalSend elGamalSend, BigInteger privateKey) {
		
		BigInteger M = elGamalSend.getSharedSecret().subtract(privateKey.multiply(elGamalSend.getR()));
		String decrypt = new String(M.toByteArray());
		return decrypt;
	}
	
}
