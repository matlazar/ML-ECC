package hr.matlazar.ecc.algoritams;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.domains.ECDSASignature;

public class ECDSA {
	
	Random rnd = new Random(); 
	BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
	BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	BigInteger G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	
	public ECDSASignature signMessage(String message, String privateKey) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
			BigInteger hashNumber = new BigInteger(Base64.getDecoder().decode(hash));
			BigInteger k = new BigInteger(n.bitLength(), rnd);
			BigInteger P = k.multiply(G);
			BigInteger r = P.mod(n);
			BigInteger s = k.modInverse(n).multiply(hashNumber);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

		return null;
	}
	
//	MessageDigest digest = MessageDigest.getInstance("SHA-256");
//	byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
}
