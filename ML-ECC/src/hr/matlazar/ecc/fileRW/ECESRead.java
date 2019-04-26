package hr.matlazar.ecc.fileRW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

import hr.matlazar.ecc.arithmetic.PointEC;
import hr.matlazar.ecc.domains.ECECIESMessage;
import hr.matlazar.ecc.domains.KeyDomain;

public class ECESRead {
	
public static KeyDomain readECESMessage(File file) {
	
	KeyDomain keyDomain = new KeyDomain();
	
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
            
            keyDomain.setMessage(m);
            String[] coordinates = r.split("-");
            PointEC pointEC = new PointEC(new BigInteger(coordinates[0]), new BigInteger(coordinates[1]), true);
            keyDomain.setSharedSecret(pointEC);
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
