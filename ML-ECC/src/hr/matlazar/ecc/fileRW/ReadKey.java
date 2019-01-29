package hr.matlazar.ecc.fileRW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadKey {

	public static String read(File file) {
		String key = null;
		String line = null;
		 try {

	            FileReader fileReader = new FileReader(file);

	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            while((line = bufferedReader.readLine()) != null) {
	            	key = line;
	            }   

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
		
		return key;
	}
	
	
	public static String publicKeyDHReader(File file, String name) {
		
		String key = null;
		String line = null;
		 try {

	            FileReader fileReader = new FileReader(file);

	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            while((line = bufferedReader.readLine()) != null) {
	            	String pom = line.substring(0,line.indexOf(":"));
	            	if(name.toLowerCase().equals(pom.toLowerCase())) {
	            		key = line.substring(line.indexOf(":")+1);
	            		break;
	            	}
	            }   

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
		
		return key;
	}
}
