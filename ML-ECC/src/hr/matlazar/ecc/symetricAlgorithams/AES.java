package hr.matlazar.ecc.symetricAlgorithams;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	private static final String initVector = "encryptionIntVec";
	
	public static String encrypt(String value, String key) {
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] key2 = Base64.getDecoder().decode(key);
			key2 = sha.digest(key2);
			key2 = Arrays.copyOf(key2, 16);
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key2, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64.getEncoder().encodeToString((encrypted));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String decrypt(String encrypted, String key) {
		try {
			
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] key2 = Base64.getDecoder().decode(key);
			key2 = sha.digest(key2);
			key2 = Arrays.copyOf(key2, 16);
			
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key2, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
}
