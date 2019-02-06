package hr.matlazar.ecc.fileRW;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ClearFile {

	public static void clearFile(String filePath) {
		File file = new File(filePath);
		FileWriter fwOb;
		try {
			fwOb = new FileWriter(file, false);
	        PrintWriter pwOb = new PrintWriter(fwOb, false);
	        pwOb.flush();
	        pwOb.close();
	        fwOb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
