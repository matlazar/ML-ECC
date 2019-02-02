package hr.matlazar.ecc.algoritams;

import java.io.File;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Random;

import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.domains.ElGamalSend;
import hr.matlazar.ecc.fileRW.WriteFile;

public class ElGamal {

	File file = new File("files/elgamal.txt");
	
	BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
	BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	BigInteger G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	
	
	
	public ElGamal(String option) {
		
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

	public ElGamalSend encrypt(String d, String message) {
		
		BigInteger publicKey = new BigInteger(Base64.getDecoder().decode(d));
		
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
        
        elGamalSend.setR(Base64.getEncoder().encodeToString(r.multiply(G).toByteArray()));
        elGamalSend.setSharedSecret(Base64.getEncoder().encodeToString(M.add(r.multiply(publicKey)).toByteArray()));
        
        WriteFile.write(file, elGamalSend.getSharedSecret(), elGamalSend.getR());
       
		return elGamalSend;
	}
	
	public String decrypt(ElGamalSend elGamalSend, String q) {
		
		BigInteger r = new BigInteger(Base64.getDecoder().decode(elGamalSend.getR()));
		
		BigInteger privateKey = new BigInteger(Base64.getDecoder().decode(q));
		
		BigInteger sharedSecred = new BigInteger(Base64.getDecoder().decode(elGamalSend.getSharedSecret()));
		BigInteger M = sharedSecred.subtract(privateKey.multiply(r));
		String decrypt = new String(M.toByteArray());
		return decrypt;
	}
	
}
