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
		String R = null;
		String S = null;
		String z = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(Base64.getEncoder().encode(message.getBytes()));
			BigInteger hashNumber = new BigInteger(hash);
			System.out.println(hashNumber);
			BigInteger k = new BigInteger(n.bitLength(), rnd);
			BigInteger qA = new BigInteger (Base64.getDecoder().decode(privateKey.getBytes()));
			BigInteger P = k.multiply(G);
			BigInteger r = P.mod(n);
			BigInteger s = k.modInverse(n).multiply(hashNumber.add(qA));
			s = s.mod(n);
			System.out.println(r);
			System.out.println(s);
			R = Base64.getEncoder().encodeToString(r.toByteArray());
			S = Base64.getEncoder().encodeToString(s.toByteArray());
			z = Base64.getEncoder().encodeToString(hashNumber.toByteArray());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		ECDSASignature ecdsaSignature = new ECDSASignature();
		ecdsaSignature.setMessage(message);
		ecdsaSignature.setR(R);
		ecdsaSignature.setS(S);
		return ecdsaSignature;
	}
	
	public String dehashString(ECDSASignature ecdsaSignature, String publicKey) throws NoSuchAlgorithmException {
		MessageDigest digest;
		digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(Base64.getEncoder().encode(ecdsaSignature.getMessage().getBytes()));
		BigInteger z = new BigInteger(hash);
		System.out.println(z);
		BigInteger r = new BigInteger(Base64.getDecoder().decode(ecdsaSignature.getR().getBytes()));
		BigInteger s = new BigInteger(Base64.getDecoder().decode(ecdsaSignature.getS().getBytes()));
		BigInteger hA = new BigInteger(Base64.getDecoder().decode(publicKey.getBytes()));
		
		BigInteger w = s.modInverse(n);
		
		BigInteger u1 = w.multiply(z).mod(n);
		BigInteger u2 = w.multiply(r).mod(n);
		BigInteger xP = u1.multiply(G).add(u2.multiply(hA));
		System.out.println("-----");
		System.out.println(r);
		System.out.println(s);
		System.out.println("-----");
		System.out.println(r);
		System.out.println(xP.mod(n));
		
		
		if(r.equals(xP.mod(n))) {
			System.out.println("pase");
		}else {
			System.out.println("Ne pase");
		}
		return null;
		
	}
//	MessageDigest digest = MessageDigest.getInstance("SHA-256");
//	byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
}
