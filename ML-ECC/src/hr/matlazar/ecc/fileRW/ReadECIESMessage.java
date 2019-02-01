package hr.matlazar.ecc.fileRW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import hr.matlazar.ecc.domains.ECIESMessage;

public class ReadECIESMessage {
	
	public static ECIESMessage readECIESMessage(File file) {
		
		ECIESMessage eciesMessage = new ECIESMessage();
		
		String line = null;
		String m = null;
		String r = null;
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
						r = line;
						readCunt--;
						break;
					default:
						break;
					}
	            }   
	            
	            eciesMessage.setMessage(m);
	            eciesMessage.setR(r);


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
		
		
		return eciesMessage;
	}
}
