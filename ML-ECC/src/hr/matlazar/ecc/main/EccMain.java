package hr.matlazar.ecc.main;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


import hr.matlazar.ecc.algoritams.DiffieHellman;
import hr.matlazar.ecc.algoritams.ECDSA;
import hr.matlazar.ecc.algoritams.ECES;
import hr.matlazar.ecc.algoritams.ECIES;
import hr.matlazar.ecc.algoritams.ElGamal;
import hr.matlazar.ecc.arithmetic.PointEC;
import hr.matlazar.ecc.constants.DomainParameters;
import hr.matlazar.ecc.constants.FileName;
import hr.matlazar.ecc.domains.ECCDHKeyPair;
import hr.matlazar.ecc.domains.ECDSASignature;
import hr.matlazar.ecc.domains.ECECIESMessage;
import hr.matlazar.ecc.domains.ECIESMessage;
import hr.matlazar.ecc.domains.ElGamalSend;
import hr.matlazar.ecc.domains.KeyDomain;
import hr.matlazar.ecc.domains.KeyPair;
import hr.matlazar.ecc.fileRW.WriteFile;
import hr.matlazar.ecc.keyGenerator.KeyGenerator;


public class EccMain {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
//		Scanner sc = new Scanner(System.in);
//		
//		KeyGenerator keyGenerator = new KeyGenerator(DomainParameters.secp192k1);
//		KeyPair keyPair = new KeyPair();
//		keyPair = keyGenerator.generateKeys();
//		
//		System.out.println("-----------------------------Kljucevi---------------------------------\n");
//		
//		System.out.println("Private key: " + keyPair.getPrivateKey() + "\n");
//		System.out.println("Public key: " + keyPair.getPublicKey() + "\n");
//		WriteFile.write(FileName.PRIVATE_KEY, keyPair.getPrivateKey());
//		
//		System.out.println("----------------------------------------------------------------------\n");
//		
//		System.out.println("-----------------------------ECES---------------------------------\n");
//		ECES eces = new ECES("secp192k1");
//		System.out.println(" Unesi tekst() ECES: ");
//		KeyDomain keyDomain = eces.encrypt(keyPair.getPublicKey(), sc.nextLine());
//		System.out.println("Encryption: " + keyDomain.getMessage() + "\n");
//		System.out.println("Decryption: " + eces.decrypt(keyDomain, keyPair.getPrivateKey()));
//		System.out.println("------------------------------------------------------------------\n");
//		
//		System.out.println("----------------------------ElGamal-------------------------------\n");
//		
//		ElGamal elGamal = new ElGamal(DomainParameters.secp192k1);
//		System.out.println(" Unesi tekst() ElGamal: ");
//		ElGamalSend egs = elGamal.encrypt(keyPair.getPublicKey(), sc.nextLine());
//
//		System.out.println("Encrypt: " + egs.getSharedSecret()  + "\n");
//		String decrypt = elGamal.decrypt(egs, keyPair.getPrivateKey());
//		System.out.println("Decrypt: " + decrypt + "\n");
//		System.out.println("------------------------------------------------------------------\n");
//		
//		
//		System.out.println("--------------------------Diffie-Helman---------------------------\n");
//		
//		System.out.println("****************************DH-Keys*******************************\n");
//		
//		KeyPair bKeyPair =new KeyPair();
//		keyPair = keyGenerator.DHgenerateKeys();
//		bKeyPair = keyGenerator.DHgenerateKeys();
//		System.out.println("Alice's keys");
//		System.out.println("Private key: " + keyPair.getPrivateKey() + "\n");
//		System.out.println("Public key: " + keyPair.getPublicKey() + "\n");
//		System.out.println("******************************************************************\n");
//		System.out.println("Bob's keys");
//		System.out.println("Private key: " + bKeyPair.getPrivateKey() + "\n");
//		System.out.println("Public key: " + bKeyPair.getPublicKey() + "\n");
//		System.out.println("******************************************************************\n");
//		WriteFile.write(FileName.PUBLIC_KEYS, "Darko:" + bKeyPair.getPublicKey(), "Ana:" + keyPair.getPublicKey());
//		
//		DiffieHellman diffieHellman = new DiffieHellman("secp192k1");
//		String aliceSharedSecret = diffieHellman.computeSharedSecret(bKeyPair.getPublicKey(), keyPair.getPrivateKey());
//		String bobSharedSecret = diffieHellman.computeSharedSecret(keyPair.getPublicKey(), bKeyPair.getPrivateKey());
//		
//		System.out.println("Bob šalje Alice svoj javni kljuè: " + bKeyPair.getPublicKey() + "\n");
//		System.out.println("Alice generira zajednièku tajnu: " + aliceSharedSecret + "\n");
//		
//		System.out.println("Alice šalje Bobu svoj javni kljuè: " + keyPair.getPublicKey() + "\n");
//		System.out.println("Bob generira zajednièku tajnu: " + bobSharedSecret + "\n");
//		
//		boolean verification = DiffieHellman.verifySharedSecert(aliceSharedSecret, bobSharedSecret);
//
//		
//		System.out.println("++++++++++++++++++++++++Verifikacija++++++++++++++++++++++++++++++\n");
//		if(verification) {
//			System.out.println("Verifikacija uspješna\n");
//		}else {
//			System.out.println("Verifikacija nije uspješna\n");
//		}
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
//
//		System.out.println("--------------------------ECIES-----------------------------------\n");
//		
//		ECIES ecies = new ECIES(DomainParameters.secp192k1);
//		keyPair = keyGenerator.generateKeys();
//		System.out.println("Unesi tekst za dekripciju: ");
//		ECIESMessage ecm = ecies.encryptECIES(sc.nextLine(), keyPair.getPublicKey());
//		System.out.println("Enkripcija " + ecm.getMessage() + "\n");
//		String p = ecies.decrypt(ecm, keyPair.getPrivateKey());
//		
//		System.out.println(p);
//		System.out.println("------------------------------------------------------------------\n");
//		
//		ECDSASignature es = new ECDSASignature();
//		ECDSA ecdsa = new ECDSA(DomainParameters.secp192k1);
//		es = ecdsa.signMessage(ecm.getMessage(), keyPair.getPrivateKey());
//		System.out.println("-----------------------------------------------\n");
//		boolean verify = ecdsa.dehashString(es, keyPair.getPublicKey());
//		
//		if(verify) {
//			System.out.println("Potpis je validan");
//		} else {
//			System.out.println("Potpis nije validan");
//		}
//		
//		sc.close();
//		DiffieHellman diffieHellman = new DiffieHellman("secp256k1");
//		KeyGenerator keyGenerator = new KeyGenerator("secp256k1");
//		ECCDHKeyPair Alice = keyGenerator.ECDHGenerateKeys();
//		ECCDHKeyPair Bob = keyGenerator.ECDHGenerateKeys();
//		
//		
//		PointEC ss1 = diffieHellman.sharedSecret(Bob.getPublicKeyPoint(), Alice.getPrivateKey());
//		PointEC ss2 = diffieHellman.sharedSecret(Alice.getPublicKeyPoint(), Bob.getPrivateKey());
//		
//		System.out.println(diffieHellman.verify(ss1, ss2));
//		
//		KeyGenerator keyGenerator = new KeyGenerator("secp192k1");
//		ECCDHKeyPair Alice = keyGenerator.ECDHGenerateKeys();
//		
//		ECDSA ecdsa = new ECDSA("secp192k1");
//		
//		ECDSASignature ecdsaSignature =  ecdsa.ecECDSASignMessage("Marko", Alice.getPrivateKey());
//		System.out.println(ecdsaSignature.getMessage() + " " + ecdsaSignature.getR() + " " + ecdsaSignature.getS());
//		System.out.println("-------------------------------------------------------------------------------------");
//		System.out.println(ecdsa.ecDehashString(ecdsaSignature, Alice.getPublicKeyPoint()));
//		
//		KeyGenerator keyGenerator = new KeyGenerator("secp256k1");
//		ECCDHKeyPair Alice = keyGenerator.ECDHGenerateKeys();
//		
//		ECES eces = new ECES("secp256k1");
//		
//		KeyDomain keyDomain = eces.encrypt(Alice.getPublicKeyPoint(), "Nataša");
//		
//		System.out.println(keyDomain.getMessage());
//		
//		System.out.println("-------------------------");
//		
//		System.out.println(eces.decrypt(keyDomain, Alice.getPrivateKey()));
		
//		ECIES ecies = new ECIES("secp256k1");
//		ECECIESMessage ececiesMessage = ecies.ecEncryptECIES("Matija je prezakon jer navija za Man Utd", Alice.getPublicKeyPoint());
//		System.out.println(ececiesMessage.getMessage());
//		System.out.println("---------------------------------------------------------");
//		System.out.println(ecies.ecDecrypt(ececiesMessage, Alice.getPrivateKey()));
		
	}

}
