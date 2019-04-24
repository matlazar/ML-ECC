package hr.matlazar.ecc.keyGenerator;

import java.io.File;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Random;

import hr.matlazar.ecc.arithmetic.ECCArithmetic;
import hr.matlazar.ecc.arithmetic.PointEC;
import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.domains.ECCDHKeyPair;
import hr.matlazar.ecc.domains.KeyPair;
import hr.matlazar.ecc.fileRW.WriteFile;

public class KeyGenerator {
	
	Random rnd = new Random();  
	
	File filePublic = new File("files/publicKey.txt");
	File filePrivate = new File("files/privateKey.txt");
	
	BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
	BigInteger g = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	private static BigInteger a = new BigInteger(DomainParameters.secp192k1_a.replaceAll(" ", ""), 16);
	private static BigInteger b = new BigInteger(DomainParameters.secp192k1_b.replaceAll(" ", ""), 16);
	static String uG = DomainParameters.secp192k1_uG.replaceAll(" ", "");
	private static int bits = 0;
	
	
	
	public KeyGenerator(String option) {
		
		switch(option) {
			case "secp192k1":
				p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp192k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp192k1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp192k1_uG.replaceAll(" ", "");
				bits = 6;
				break;
			case "secp192r1":
				p = new BigInteger(DomainParameters.secp192r1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp192r1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192r1_n.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp192r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp192r1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp192r1_uG.replaceAll(" ", "");
				bits = 6;
				break;
			case "secp224k1":
				p = new BigInteger(DomainParameters.secp224k1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp224k1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp224k1_n.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp224k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp224k1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp224k1_uG.replaceAll(" ", "");
				bits = 7;
				break;
			case "secp224r1":
				p = new BigInteger(DomainParameters.secp224r1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp224r1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp224r1_n.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp224r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp224r1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp224r1_uG.replaceAll(" ", "");
				bits = 7;
				break;
			case "secp256k1":
				p = new BigInteger(DomainParameters.secp256k1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp256k1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp256k1_n.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp256k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp256k1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp256k1_uG.replaceAll(" ", "");
				bits = 8;
				break;
			case "secp256r1":
				p = new BigInteger(DomainParameters.secp256r1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp256r1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp256r1_n.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp256r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp256r1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp256r1_uG.replaceAll(" ", "");
				bits = 8;
				break;
			case "secp384r1":
				p = new BigInteger(DomainParameters.secp384r1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp384r1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp384r1_n.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp384r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp384r1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp384r1_uG.replaceAll(" ", "");
				bits = 12;
				break;
			case "secp521r1":
				p = new BigInteger(DomainParameters.secp521r1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp521r1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp521r1_n.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp521r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp521r1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp521r1_uG.replaceAll(" ", "");
				bits = 16;
				break;
			default:	
				p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
				g = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp192k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp192k1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp192k1_uG.replaceAll(" ", "");
				bits = 6;
		}
		
	}

	public KeyPair generateKeys() {

		KeyPair keyPair = new KeyPair();
			
		BigInteger d = new BigInteger(n.bitLength(), rnd);
		keyPair.setPrivateKey(Base64.getEncoder().encodeToString(d.toByteArray()));
		WriteFile.write(filePrivate, Base64.getEncoder().encodeToString(d.toByteArray()));
		
		BigInteger Q = d.multiply(g).mod(p);
		keyPair.setPublicKey(Base64.getEncoder().encodeToString(Q.toByteArray()));
		WriteFile.write(filePublic, Base64.getEncoder().encodeToString(Q.toByteArray()));	
		
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
	
	public ECCDHKeyPair ECDHGenerateKeys() {
		
		ECCDHKeyPair eccdhKeyPair = new ECCDHKeyPair();
		PointEC pointG = pointG(bits, p);
		do {
			BigInteger d = new BigInteger(n.bitLength(), rnd);
			eccdhKeyPair.setPrivateKey(Base64.getEncoder().encodeToString(d.toByteArray()));
			
			PointEC javniKljuc = ECCArithmetic.mul(d, pointG, a, b, p);
			eccdhKeyPair.setPublicKeyPoint(javniKljuc);
		}while(!eccdhKeyPair.getPublicKeyPoint().isOnCurve());
		
		return eccdhKeyPair;
	}
	
	public static PointEC pointG(int bits, BigInteger p) {
		String substringUG = uG.substring(2);
		BigInteger x = new BigInteger(substringUG.substring(0, Math.min(substringUG.length(), bits*8)), 16);
		BigInteger y = new BigInteger(substringUG.substring(bits * 8), 16);
		PointEC pointEC = new PointEC(x, y, a, b, p);
		return pointEC;
	}
}
