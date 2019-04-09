package hr.matlazar.ecc.algoritams;

import java.io.File;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Random;

import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.domains.ECIESMessage;
import hr.matlazar.ecc.fileRW.WriteFile;
import hr.matlazar.ecc.symetricAlgorithams.AES;

public class ECIES {
	
	File file = new File("files/ecies.txt");
	
	BigInteger n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
	BigInteger G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
	
	Random rnd = new Random();
	
	
	
	public ECIES(String option) {
		
		switch(option) {
			case "secp192k1":
				n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
				break;
			case "secp192r1":
				n = new BigInteger(DomainParameters.secp192r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp192r1_G.replaceAll(" ", ""), 16);
				break;
			case "secp224k1":
				n = new BigInteger(DomainParameters.secp224k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp224k1_G.replaceAll(" ", ""), 16);
				break;
			case "secp224r1":
				n = new BigInteger(DomainParameters.secp224r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp224r1_G.replaceAll(" ", ""), 16);
				break;
			case "secp256k1":
				n = new BigInteger(DomainParameters.secp256k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp256k1_G.replaceAll(" ", ""), 16);
				break;
			case "secp256r1":
				n = new BigInteger(DomainParameters.secp256r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp256r1_G.replaceAll(" ", ""), 16);
				break;
			case "secp384r1":
				n = new BigInteger(DomainParameters.secp384r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp384r1_G.replaceAll(" ", ""), 16);
				break;
			case "secp521r1":
				n = new BigInteger(DomainParameters.secp521r1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp521r1_G.replaceAll(" ", ""), 16);
				break;
			default:
				n = new BigInteger(DomainParameters.secp192k1_n.replaceAll(" ", ""), 16);
				G = new BigInteger(DomainParameters.secp192k1_G.replaceAll(" ", ""), 16);
			
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
	
	
}
