package hr.matlazar.ecc.algoritams;

import java.io.File;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Random;

import hr.matlazar.ecc.arithmetic.ECCArithmetic;
import hr.matlazar.ecc.arithmetic.PointEC;
import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.domains.ECECIESMessage;
import hr.matlazar.ecc.domains.ECIESMessage;
import hr.matlazar.ecc.fileRW.WriteFile;
import hr.matlazar.ecc.symetricAlgorithams.AES;

public class ECIES {
	
	File file = new File("files/ecies.txt");
	
	BigInteger p = new BigInteger(DomainParameters.secp192k1_p.replaceAll(" ", ""), 16);
	BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	BigInteger G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	private static BigInteger a = new BigInteger(DomainParameters.secp192k1_a.replaceAll(" ", ""), 16);
	private static BigInteger b = new BigInteger(DomainParameters.secp192k1_b.replaceAll(" ", ""), 16);
	static String uG = DomainParameters.secp192k1_uG.replaceAll(" ", "");
	private static int bits = 0;
	Random rnd = new Random();
	
	
	
	public ECIES(String option) {
		
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

	public ECIESMessage encryptECIES(String plaintext, String publicKey) {
		
		ECIESMessage eciesMessage = new ECIESMessage();
		
		BigInteger qa = new BigInteger(Base64.getDecoder().decode(publicKey));
		
		BigInteger r = new BigInteger(n.bitLength(), rnd);
		
		BigInteger R = r.multiply(G);
		
		BigInteger S = r.multiply(qa);
		
		String encrypted = AES.encrypt(plaintext, Base64.getEncoder().encodeToString(S.toByteArray()));
		
		eciesMessage.setMessage(encrypted);
		
		eciesMessage.setR(Base64.getEncoder().encodeToString(R.toByteArray()));
		
		WriteFile.write(file, eciesMessage.getMessage(), eciesMessage.getR());
		
		return eciesMessage;
	}
	
	public String decrypt(ECIESMessage eciesMessage, String privateKey) {
		
		BigInteger dA = new BigInteger(Base64.getDecoder().decode(privateKey));
		
		BigInteger R = new BigInteger(Base64.getDecoder().decode(eciesMessage.getR()));
		
		BigInteger S = dA.multiply(R);
		
		String decrypt = AES.decrypt(eciesMessage.getMessage(), Base64.getEncoder().encodeToString(S.toByteArray()));
		
		return decrypt;
	}
	
	public ECECIESMessage ecEncryptECIES(String plaintext, PointEC publicKey) {
		
		ECECIESMessage ececiesMessage = new ECECIESMessage();
		
		BigInteger r = new BigInteger(n.bitLength(), rnd);
		
		PointEC R = ECCArithmetic.mul(r, pointG(bits, p), a, b, p);
		
		PointEC S = ECCArithmetic.mul(r, publicKey, a, b, p);
		
		String encrypted = AES.encrypt(plaintext, Base64.getEncoder().encodeToString(S.getX().toByteArray()));
		
		ececiesMessage.setMessage(encrypted);
		
		ececiesMessage.setR(R);
		
		//WriteFile.write(file, ececiesMessage.getMessage(), ececiesMessage.getR());
		
		return ececiesMessage;
	}
	
	public String ecDecrypt(ECECIESMessage ececiesMessage, String privateKey) {
		
		BigInteger dA = new BigInteger(Base64.getDecoder().decode(privateKey));
		
		//BigInteger R = new BigInteger(Base64.getDecoder().decode(eciesMessage.getR()));
		
		PointEC S = ECCArithmetic.mul(dA, ececiesMessage.getR(), a, b, p);
		
		String decrypt = AES.decrypt(ececiesMessage.getMessage(), Base64.getEncoder().encodeToString(S.getX().toByteArray()));
		
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
