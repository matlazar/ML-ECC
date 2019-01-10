package hr.matlazar.ecc.main;

import java.math.BigInteger;
import java.util.Base64;

import hr.matlazar.ecc.algoritams.ECES;
import hr.matlazar.ecc.algoritams.ElGamal;
import hr.matlazar.ecc.domains.ElGamalSend;
import hr.matlazar.ecc.domains.KeyDomain;
import hr.matlazar.ecc.domains.KeyPair;
import hr.matlazar.ecc.keyGenerator.KeyGenerator;


public class EccMain {

	public static void main(String[] args) {
		
		KeyGenerator keyGenerator = new KeyGenerator();
		BigInteger privateKey = keyGenerator.generatePrivateKey();
		BigInteger publicKey = keyGenerator.generatePublicKey(privateKey);
		KeyPair keyPair = new KeyPair();
		keyPair = keyGenerator.generateKeys();
		System.out.println("-----------------------------Kljucevi---------------------------------\n");
//		System.out.println(Base64.getEncoder().encodeToString(privateKey.toByteArray()));
		System.out.println("Private key: " + keyPair.getPrivateKey() + "\n");
		System.out.println("Public key: " + keyPair.getPublicKey() + "\n");
		System.out.println("----------------------------------------------------------------------\n");
		
		System.out.println("-----------------------------ECES---------------------------------\n");
		ECES eces = new ECES();
		KeyDomain keyDomain = eces.encrypt(keyPair.getPublicKey(), "ECES algoritam");
		System.out.println("Encryption: " + keyDomain.getMessage() + "\n");
		System.out.println("Decryption: " + eces.decrypt(keyDomain, keyPair.getPrivateKey()));
		System.out.println("------------------------------------------------------------------\n");
		
		System.out.println("----------------------------ElGamal-------------------------------\n");
		
		ElGamal elGamal = new ElGamal();
		ElGamalSend egs = elGamal.encrtypt(keyPair.getPublicKey(), "El Gamal algoritam");
		
		System.out.println("Encrypt: " + egs.getSharedSecret()  + "\n");
		String decrypt = elGamal.decrypt(egs, keyPair.getPrivateKey());
		System.out.println("Decrypt: " + decrypt + "\n");
		System.out.println("------------------------------------------------------------------\n");
		

	}

}
