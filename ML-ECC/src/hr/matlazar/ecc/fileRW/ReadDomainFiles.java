package hr.matlazar.ecc.fileRW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Base64;

import hr.matlazar.ecc.domains.KeyDomain;

public class ReadDomainFiles {
	
	public static Object readKeyDoman(File file) {
		KeyDomain keyDomain = new KeyDomain();
		String line = null;
		String m = null;
		String ss = null;
		 try {

	            FileReader fileReader = new FileReader(file);

	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int readCunt = 2;
	            while((line = bufferedReader.readLine()) != null) {
	                switch (readCunt) {
					case 2:
						m = line;
						readCunt--;
						break;
					case 1:
						ss = line;
						readCunt--;
						break;
					default:
						break;
					}
	            }   
	            
	            keyDomain.setMessage(m);
	            keyDomain.setSharedSecret(new BigInteger(Base64.getDecoder().decode(ss.getBytes())));

	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                file + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + file + "'");                  
	        }
		
		
		return null;
	}
	
}
