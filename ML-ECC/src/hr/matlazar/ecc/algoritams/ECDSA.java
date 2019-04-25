package hr.matlazar.ecc.algoritams;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import hr.matlazar.ecc.arithmetic.ECCArithmetic;
import hr.matlazar.ecc.arithmetic.PointEC;
import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.domains.ECDSASignature;
import hr.matlazar.ecc.fileRW.WriteFile;

public class ECDSA {
	
	Random rnd = new Random(); 
	File file =  new File("files/ECDSA.txt");
	BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
	BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	BigInteger G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	private static BigInteger a = new BigInteger(DomainParameters.secp192k1_a.replaceAll(" ", ""), 16);
	private static BigInteger b = new BigInteger(DomainParameters.secp192k1_b.replaceAll(" ", ""), 16);
	static String uG = DomainParameters.secp192k1_uG.replaceAll(" ", "");
	private static int bits = 0;
	
	
	
	public ECDSA(String option) {
		
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

	public ECDSASignature signMessage(String message, String privateKey) {
		MessageDigest digest;
		String R = null;
		String S = null;

		ECDSASignature ecdsaSignature = new ECDSASignature();
		
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(Base64.getEncoder().encode(message.getBytes()));
			BigInteger hashNumber = new BigInteger(hash);
			BigInteger k = new BigInteger(n.bitLength(), rnd);
			BigInteger qA = new BigInteger (Base64.getDecoder().decode(privateKey.getBytes()));
			BigInteger P = k.multiply(G);
			BigInteger r = P.mod(n);
			BigInteger s = k.modInverse(n).multiply(hashNumber.add(qA.multiply(r)));
			s = s.mod(n);
			R = Base64.getEncoder().encodeToString(r.toByteArray());
			S = Base64.getEncoder().encodeToString(s.toByteArray());
			//String z = Base64.getEncoder().encodeToString(hashNumber.toByteArray());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		
	

		ecdsaSignature.setMessage(message);
		ecdsaSignature.setR(R);
		ecdsaSignature.setS(S);
		
		WriteFile.write(file, ecdsaSignature.getMessage(), ecdsaSignature.getR(), ecdsaSignature.getS());
		return ecdsaSignature;
	}
	
	public boolean dehashString(ECDSASignature ecdsaSignature,String publicKey) {
		MessageDigest digest;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(Base64.getEncoder().encode(ecdsaSignature.getMessage().getBytes()));
			BigInteger z = new BigInteger(hash);
			BigInteger r = new BigInteger(Base64.getDecoder().decode(ecdsaSignature.getR().getBytes()));
			BigInteger s = new BigInteger(Base64.getDecoder().decode(ecdsaSignature.getS().getBytes()));
			BigInteger hA = new BigInteger(Base64.getDecoder().decode(publicKey.getBytes()));
			
			BigInteger w = s.modInverse(n);
			
			BigInteger u1 = w.multiply(z).mod(n);
			BigInteger u2 = w.multiply(r).mod(n);
			BigInteger xP = u1.multiply(G).add(u2.multiply(hA));
			
			if(r.equals(xP.mod(n))) {
				return true;
			} else {
				return false;
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public ECDSASignature ecECDSASignMessage(String message, String privateKey) {
		MessageDigest digest;
		String R = null;
		String S = null;
		PointEC pointG = pointG(bits, p);

		ECDSASignature ecdsaSignature = new ECDSASignature();

		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(Base64.getEncoder().encode(message.getBytes()));
			BigInteger hashNumber = new BigInteger(hash);
			BigInteger k = new BigInteger(n.bitLength(), rnd);
			BigInteger qA = new BigInteger (Base64.getDecoder().decode(privateKey.getBytes()));
			PointEC P = ECCArithmetic.mul(k, pointG, a, b, p);//k.multiply(G);
			BigInteger r = P.getX().mod(n);
			BigInteger s = k.modInverse(n).multiply(hashNumber.add(qA.multiply(r)));
			s = s.mod(n);
			R = Base64.getEncoder().encodeToString(r.toByteArray());
			S = Base64.getEncoder().encodeToString(s.toByteArray());
			//String z = Base64.getEncoder().encodeToString(hashNumber.toByteArray());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 



		ecdsaSignature.setMessage(message);
		ecdsaSignature.setR(R);
		ecdsaSignature.setS(S);

		WriteFile.write(file, ecdsaSignature.getMessage(), ecdsaSignature.getR(), ecdsaSignature.getS());
		return ecdsaSignature;


	}

	public boolean ecDehashString(ECDSASignature ecdsaSignature, PointEC publicKey) {
		MessageDigest digest;
		PointEC pointG = pointG(bits, p);
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(Base64.getEncoder().encode(ecdsaSignature.getMessage().getBytes()));
			BigInteger z = new BigInteger(hash);
			BigInteger r = new BigInteger(Base64.getDecoder().decode(ecdsaSignature.getR().getBytes()));
			BigInteger s = new BigInteger(Base64.getDecoder().decode(ecdsaSignature.getS().getBytes()));

			BigInteger w = s.modInverse(n);

			BigInteger u1 = w.multiply(z).mod(n);
			BigInteger u2 = w.multiply(r).mod(n);
			PointEC first = ECCArithmetic.mul(u1, pointG, a, b, p);
			PointEC second = ECCArithmetic.mul(u2, publicKey, a, b, p);
			PointEC sumPoint = ECCArithmetic.add(first, second, a, b, p);

			if(sumPoint.getX().equals(new BigInteger("0")) &&  sumPoint.getY().equals(new BigInteger("0"))) {
				return false;
			} else {
				BigInteger v =  sumPoint.getX().mod(p);
				if(r.equals(v)) {
					return true;
				}else {
					return false;
				}
			}

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static PointEC pointG(int bits, BigInteger p) {
		String substringUG = uG.substring(2);
		BigInteger x = new BigInteger(substringUG.substring(0, Math.min(substringUG.length(), bits*8)), 16);
		BigInteger y = new BigInteger(substringUG.substring(bits * 8), 16);
		PointEC pointEC = new PointEC(x, y, a, b, p);
		return pointEC;
	}
}
