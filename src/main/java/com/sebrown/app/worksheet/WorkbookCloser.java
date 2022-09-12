/**
 * 
 */
package com.sebrown.app.worksheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sebrown.app.annotations.LogDebugMsg;

/**
 * @author SteveBrown
 *
 */
public class WorkbookCloser {
	
	@LogDebugMsg(msg = "Closing Output Workbook")
	public static void writeAndCloseWb(XSSFWorkbook wb, String path) {
		OutputStream fileOut;  
		try {
			fileOut = new FileOutputStream(new File(path));
			wb.write(fileOut);
			wb.close();
			fileOut.close();
		} catch (IOException e) {
			//ErrorLoggingAspect
		}		
	}
	
}
