package hr.matlazar.ecc.fileRW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {
	
	
	public static void write(File filePath, String ... args) {
        
		try {
			FileWriter fileWriter = new FileWriter(filePath);
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        for(String txt : args) {
		        bufferedWriter.write(txt);
		        bufferedWriter.newLine();
	        }
	        bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writePublicKeys(File filePath, String publicKey, String name) {
		if(lineCount(filePath) >= 2) {
			try {
		        FileWriter fwOb = new FileWriter(filePath, false);
		        PrintWriter pwOb = new PrintWriter(fwOb, false);
		        pwOb.flush();
		        pwOb.close();
		        fwOb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} 
		try {
			FileWriter fileWriter = new FileWriter(filePath, true);
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    bufferedWriter.write(name + ":" + publicKey);
	        bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int lineCount(File file) {
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader( new FileReader(file));
			while (reader.readLine() != null) lines++;
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}
	
}
