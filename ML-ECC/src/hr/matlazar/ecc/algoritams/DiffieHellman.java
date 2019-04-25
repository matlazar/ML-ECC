package hr.matlazar.ecc.algoritams;

import java.math.BigInteger;
import java.util.Base64;

import hr.matlazar.ecc.arithmetic.ECCArithmetic;
import hr.matlazar.ecc.arithmetic.PointEC;
import hr.matlazar.ecc.constants.DomainParameters;

public class DiffieHellman {

	
	public BigInteger g;
	public BigInteger p;
	public BigInteger a;
	public BigInteger b;
	
	
	
	public DiffieHellman(String option) {
		
		switch(option) {
			case "secp192k1":
				g = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
				p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp192k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp192k1_b.replaceAll(" ", ""), 16);
				break;
			case "secp192r1":
				g = new BigInteger(DomainParameters.secp192r1_G.replaceAll(" ", ""), 16);
				p = new BigInteger(DomainParameters.secp192r1_p.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp192r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp192r1_b.replaceAll(" ", ""), 16);
				break;
			case "secp224k1":
				g = new BigInteger(DomainParameters.secp224k1_G.replaceAll(" ", ""), 16);
				p = new BigInteger(DomainParameters.secp224k1_p.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp224k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp224k1_b.replaceAll(" ", ""), 16);
				break;
			case "secp224r1":
				g = new BigInteger(DomainParameters.secp224r1_G.replaceAll(" ", ""), 16);
				p = new BigInteger(DomainParameters.secp224r1_p.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp224r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp224r1_b.replaceAll(" ", ""), 16);
				break;
			case "secp256k1":
				g = new BigInteger(DomainParameters.secp256k1_G.replaceAll(" ", ""), 16);
				p = new BigInteger(DomainParameters.secp256k1_p.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp256k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp256k1_b.replaceAll(" ", ""), 16);
				break;
			case "secp256r1":
				g = new BigInteger(DomainParameters.secp256r1_G.replaceAll(" ", ""), 16);
				p = new BigInteger(DomainParameters.secp256r1_p.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp256r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp256r1_b.replaceAll(" ", ""), 16);
				break;
			case "secp384r1":
				g = new BigInteger(DomainParameters.secp384r1_G.replaceAll(" ", ""), 16);
				p = new BigInteger(DomainParameters.secp384r1_p.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp384r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp384r1_b.replaceAll(" ", ""), 16);
				break;
			case "secp521r1":
				g = new BigInteger(DomainParameters.secp521r1_G.replaceAll(" ", ""), 16);
				p = new BigInteger(DomainParameters.secp521r1_p.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp521r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp521r1_b.replaceAll(" ", ""), 16);
				break;
			default:
				g = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
				p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp192k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp192k1_b.replaceAll(" ", ""), 16);
		}
		
	}


	public String computeSharedSecret(String publicKey, String privateKey) {
		
		
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
	
	public PointEC sharedSecret(PointEC publicKey, String privateKey) {
		BigInteger privKey = new BigInteger(Base64.getDecoder().decode(privateKey));
		PointEC ss = ECCArithmetic.mul(privKey, publicKey, a, b, p);
		System.out.println(ss.getX() + " " + ss.getY());
		return ss;
	}
	
	public boolean verify(PointEC ss1, PointEC ss2) {
		
		if(ss1.getX().equals(ss2.getX()) && ss1.getY().equals(ss2.getY())) {
			return true;
		}else {
			return false;
		}
	}
}
