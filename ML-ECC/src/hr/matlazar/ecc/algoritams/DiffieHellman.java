package hr.matlazar.ecc.algoritams;

import java.math.BigInteger;
import java.util.Base64;

import hr.matlazar.ecc.constants.DomainParameters;

public class DiffieHellman {

	
	public BigInteger g = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	
	public static String computeSharedSecret(String publicKey, String privateKey) {
		
		BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
		BigInteger a = new BigInteger(Base64.getDecoder().decode(privateKey));
		BigInteger b = new BigInteger(Base64.getDecoder().decode(publicKey));
		BigInteger s = b.modPow(a, p);
		String sharedSecred = Base64.getEncoder().encodeToString(s.toByteArray());
		return sharedSecred;
	}
	
	
	public static boolean verifySharedSecert (String sharedSecredA, String sharedSecredB) {
		
		BigInteger sSA = new BigInteger(Base64.getDecoder().decode(sharedSecredA));
		BigInteger sSB = new BigInteger(Base64.getDecoder().decode(sharedSecredB));
		
		if(sSA.equals(sSB)) {
			return true;
		}else {
			return false;
		}
	}
}
