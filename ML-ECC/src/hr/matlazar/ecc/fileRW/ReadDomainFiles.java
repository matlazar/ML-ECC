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
		String ss1 = null;
		 try {

	            FileReader fileReader = new FileReader(file);

	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int readCunt = 3;
	            while((line = bufferedReader.readLine()) != null) {
	                switch (readCunt) {
					case 3:
						m = line;
						readCunt--;
						break;
					case 2:
						ss = line;
						readCunt--;
						break;
					case 1:
						ss1 = line;
						readCunt--;
						break;
					default:
						break;
					}
	            }   
	            
	            keyDomain.setMessage(m);
	            keyDomain.getSharedSecret().setX(new BigInteger(ss));
	            keyDomain.getSharedSecret().setY(new BigInteger(ss1));
	            

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
		
		
		return keyDomain;
	}
	
}
