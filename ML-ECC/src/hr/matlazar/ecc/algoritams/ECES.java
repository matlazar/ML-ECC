package hr.matlazar.ecc.algoritams;

import java.io.File;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Random;

import hr.matlazar.ecc.arithmetic.ECCArithmetic;
import hr.matlazar.ecc.arithmetic.PointEC;
import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.domains.KeyDomain;
import hr.matlazar.ecc.fileRW.WriteFile;

public class ECES {
	
	File file = new File("files/eces.txt");
	
	BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
	BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	BigInteger G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	private static BigInteger a = new BigInteger(DomainParameters.secp192k1_a.replaceAll(" ", ""), 16);
	private static BigInteger b = new BigInteger(DomainParameters.secp192k1_b.replaceAll(" ", ""), 16);
	static String uG = DomainParameters.secp192k1_uG.replaceAll(" ", "");
	private static int bits = 0;
	
	

	public ECES(String option) {
		
		switch(option) {
			case "secp192k1":
				p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp192k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp192k1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp192k1_uG.replaceAll(" ", "");
				bits = 6;
				break;
			case "secp192r1":
				p = new BigInteger(DomainParameters.secp192r1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp192r1_G.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp192r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp192r1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp192r1_uG.replaceAll(" ", "");
				bits = 6;
				break;
			case "secp224k1":
				p = new BigInteger(DomainParameters.secp224k1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp224k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp224k1_G.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp224k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp224k1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp224k1_uG.replaceAll(" ", "");
				bits = 7;
				break;
			case "secp224r1":
				p = new BigInteger(DomainParameters.secp224r1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp224r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp224r1_G.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp224r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp224r1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp224r1_uG.replaceAll(" ", "");
				bits = 7;
				break;
			case "secp256k1":
				p = new BigInteger(DomainParameters.secp256k1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp256k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp256k1_G.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp256k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp256k1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp256k1_uG.replaceAll(" ", "");
				bits = 8;
				break;
			case "secp256r1":
				p = new BigInteger(DomainParameters.secp256r1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp256r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp256r1_G.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp256r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp256r1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp256r1_uG.replaceAll(" ", "");
				bits = 8;
				break;
			case "secp384r1":
				p = new BigInteger(DomainParameters.secp384r1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp384r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp384r1_G.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp384r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp384r1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp384r1_uG.replaceAll(" ", "");
				bits = 12;
				break;
			case "secp521r1":
				p = new BigInteger(DomainParameters.secp521r1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp521r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp521r1_G.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp521r1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp521r1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp521r1_uG.replaceAll(" ", "");
				bits = 16;
				break;
			default:
				p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
				n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
				a = new BigInteger(DomainParameters.secp192k1_a.replaceAll(" ", ""), 16);
				b = new BigInteger(DomainParameters.secp192k1_b.replaceAll(" ", ""), 16);
				uG = DomainParameters.secp192k1_uG.replaceAll(" ", "");
				bits = 6;
		}
		
	}

//	public KeyDomain encrypt(String d, String message) {
//		
//		BigInteger publicKey = new BigInteger(Base64.getDecoder().decode(d));
//		byte [] toByte = message.getBytes();
//		BigInteger M = new BigInteger(toByte);
//		BigInteger r;
//        while(true)   
//        {       
//        	Random rnd = new Random();
//            r = new BigInteger(n.bitLength(), rnd);   
//            if(p.subtract(BigInteger.ONE).gcd(r).equals(BigInteger.ONE)&&r.compareTo(p) == -1)   
//            { 
//            break;   
//            }   
//        }  
//		KeyDomain keyDomain = new KeyDomain();
//		BigInteger R = r.multiply(G);
//		BigInteger sharedSecret = r.multiply(publicKey);
//		BigInteger c = M.multiply(sharedSecret).mod(p);
//		keyDomain.setMessage(Base64.getEncoder().encodeToString(c.toByteArray()));
//		keyDomain.setSharedSecret(Base64.getEncoder().encodeToString(R.toByteArray()));	
//		
//		return keyDomain;
//	}
//	
//	public String decrypt(KeyDomain keyDomain, String q) {
//		
//		BigInteger privateKey = new BigInteger(Base64.getDecoder().decode(q));
//		
//		BigInteger ss = new BigInteger(Base64.getDecoder().decode(keyDomain.getSharedSecret()));
//		
//		BigInteger x = privateKey.multiply(ss);
//
//		BigInteger c = new BigInteger(Base64.getDecoder().decode(keyDomain.getMessage()));
//		
//		BigInteger message = x.modInverse(p).multiply(c).mod(p);
//		
//		String decrypt = new String(message.toByteArray());
//		
//		return decrypt;
//	}
	
	public KeyDomain encrypt(PointEC publicKey, String message) {
		PointEC pointGenerator = pointG(bits, p);
		byte [] toByte = message.getBytes();
		BigInteger M = new BigInteger(toByte);
		BigInteger k;
		do {
			Random rnd = new Random();
			k = new BigInteger(n.bitLength(), rnd);		
		} while (k.compareTo(new BigInteger("2")) == -1);
		
		PointEC r = ECCArithmetic.mul(k, pointGenerator, a, b, p);
		PointEC t = ECCArithmetic.mul(k, publicKey, a, b, p);
		
		BigInteger c = M.multiply(t.getX()).mod(p);
		KeyDomain keyDomain = new KeyDomain();
		keyDomain.setMessage(Base64.getEncoder().encodeToString(c.toByteArray()));
		keyDomain.setSharedSecret(r);
		WriteFile.write(file, keyDomain.getMessage(), keyDomain.getSharedSecret().getX() + "-" + keyDomain.getSharedSecret().getY());
		return keyDomain;
	}
	
	public String decrypt(KeyDomain keyDomain, String q) {
		BigInteger privateKey = new BigInteger(Base64.getDecoder().decode(q));
		BigInteger c = new BigInteger(Base64.getDecoder().decode(keyDomain.getMessage()));
		PointEC s = ECCArithmetic.mul(privateKey, keyDomain.getSharedSecret(), a, b, p);
		BigInteger message = s.getX().modInverse(p).multiply(c).mod(p);
		String decrypt = new String(message.toByteArray());
		return decrypt;
	}
	
	public static PointEC pointG(int bits, BigInteger p) {
		String substringUG = uG.substring(2);
		BigInteger x = new BigInteger(substringUG.substring(0, Math.min(substringUG.length(), bits*8)), 16);
		BigInteger y = new BigInteger(substringUG.substring(bits * 8), 16);
		PointEC pointEC = new PointEC(x, y, a, b, p);
		return pointEC;
	}
	
}
 
