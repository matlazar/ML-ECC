package hr.matlazar.ecc.fileRW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import hr.matlazar.ecc.domains.ECDSASignature;

public class ReadSignature {

	public static ECDSASignature read(File file) {
		
		ECDSASignature ecdsaSignature = new ECDSASignature();
		
		String line = null;
		String m = null;
		String r = null;
		String s = null;
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
						r = line;
						readCunt--;
						break;
					case 1:
						s = line;
						readCunt--;
						break;
					default:
						break;
					}
	            }   
	            
	            ecdsaSignature.setMessage(m);
	            ecdsaSignature.setR(r);
	            ecdsaSignature.setS(s);
	            // Always close files.
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
		
		
		return ecdsaSignature;
	}
	
}
