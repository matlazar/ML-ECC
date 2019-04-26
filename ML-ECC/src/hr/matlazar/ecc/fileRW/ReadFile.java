package hr.matlazar.ecc.fileRW;

import java.io.File;

public class ReadFile {
	
	public static Object readFile(File file, String option) {
		
		switch (option) {
		case "ECDSASignature":
			return ReadSignature.read(file);
		case "ECES":
			return ReadDomainFiles.readKeyDoman(file);
		case "ECIES":
			return ReadECIESMessage.readECIESMessage(file);
		case "ElGamal":
			return ReadElGamalSend.readElGamalSend(file);
		case "ReadKeyFile":
			return ReadKey.read(file);
		case "ReadEcECIESFile":
			return ReadEcECIESFile.readECIESMessage(file);
		default:
			return null;
		}
	}
	
	public static String getPublicKey(File file, String name) {
		return ReadKey.publicKeyDHReader(file, name);
	}
	
}
