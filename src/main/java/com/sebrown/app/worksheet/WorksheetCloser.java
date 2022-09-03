/**
 * 
 */
package com.sebrown.app.worksheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author SteveBrown
 *
 */
public class WorksheetCloser {
	
	public static void writeAndCloseWb(final XSSFWorkbook wb, final String path) {
		OutputStream fileOut;  
		try {
			fileOut = new FileOutputStream(new File(path));
			wb.write(fileOut);
			wb.close();
			fileOut.close();
		} catch (IOException e) {
			// TODO - Log
			// Log that (or handle) the fact that the Audited-SOftware file could be open.
			e.printStackTrace();
		}		
	}
	
}
