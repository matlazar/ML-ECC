package hr.matlazar.ecc.main;

import java.math.BigInteger;

import hr.matlazar.ecc.algoritams.ECES;
import hr.matlazar.ecc.algoritams.ElGamal;
import hr.matlazar.ecc.domains.ElGamalSend;
import hr.matlazar.ecc.domains.KeyDomain;
import hr.matlazar.ecc.keyGenerator.KeyGenerator;


public class EccMain {

	public static void main(String[] args) {
		
		KeyGenerator keyGenerator = new KeyGenerator();
		BigInteger privateKey = keyGenerator.generatePrivateKey();
		BigInteger publicKey = keyGenerator.generatePublicKey(privateKey);
		System.out.println("-----------------------------Kljucevi---------------------------------\n");
//		System.out.println("Private key: " + new String(privateKey.toByteArray()) + "\n");
//		System.out.println("Public key: " + new String(publicKey.toByteArray()) + "\n");
		System.out.println("----------------------------------------------------------------------\n");
		
		System.out.println("-----------------------------ECES---------------------------------\n");
		ECES eces = new ECES();
		KeyDomain keyDomain = eces.encrypt(publicKey, "ECES algoritam");
		System.out.println(keyDomain.getMessage().replaceAll(" ", "") + "\n*****************************************\n");
		System.out.println(eces.decrypt(keyDomain, privateKey));
		System.out.println("------------------------------------------------------------------\n");
		
		System.out.println("----------------------------ElGamal-------------------------------\n");
		
		ElGamal elGamal = new ElGamal();
		ElGamalSend egs = elGamal.encrtypt(publicKey, "El Gamal algoritam");
		
		System.out.println("Encrypt: " + new String(egs.getSharedSecret().toByteArray())  + "\n");
		String decrypt = elGamal.decrypt(egs, privateKey);
		System.out.println("Decrypt: " + decrypt + "\n");
		System.out.println("------------------------------------------------------------------\n");
		

	}

}
