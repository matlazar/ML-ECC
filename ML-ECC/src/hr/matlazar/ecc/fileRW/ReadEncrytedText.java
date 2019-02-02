package hr.matlazar.ecc.fileRW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadEncrytedText {
	
	public static String readEcnryptionTxt(File file, int option) {
		
		String encrypt = "";
		
		if(option == 0) {

			String line = null;
			 try {
		            FileReader fileReader = new FileReader(file);
					String ss = null;
					String r = null;
		            BufferedReader bufferedReader = new BufferedReader(fileReader);
		            int readCunt = 2;
		            while((line = bufferedReader.readLine()) != null) {
		                switch (readCunt) {
						case 2:
							ss = line;
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
		            
		            
		            encrypt = ss;
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
			 
		}else {
			
			String line = null;
			 try {

		            FileReader fileReader = new FileReader(file);
		            String m = null;
					String r = null;
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
		            
		            encrypt = m;

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
		}
		
		return encrypt;
		
	}
	
}
