package hr.matlazar.ecc.fileRW;

import java.io.File;

public class ReadFile {
	
	public static Object readFile(File file, String option) {
		
		switch (option) {
		case "ECDSASignature":
			return ReadSignature.read(file);
		default:
			
			return null;
		}
	}
	
	public static String getPublicKey(File file, String name) {
		return null;
	}
	
}
