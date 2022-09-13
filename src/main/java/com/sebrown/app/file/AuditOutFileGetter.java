/**
 * 
 */
package com.sebrown.app.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sebrown.app.config.Config;
import com.sebrown.app.utils.DateFormatter;
import com.sebrown.app.worksheet.AuditHeadings;
import com.sebrown.app.worksheet.ColumnHeading;

/**
 * @author SteveBrown
 *
 * Create new audit out file (WB).
 * 
 * If there is an existing file rename it
 * before creating a new file.
 * 
 */
public class AuditOutFileGetter {
	//TODO re-factor whole class
	//--------------------------
	
	private final String path;
	private final String defaultSheetName;
	
	public AuditOutFileGetter(Config props, String defaultSheetName) {		
		this.path = props.getAuditOutFullPath();
		this.defaultSheetName = defaultSheetName;
	}
	
	public void getFile() {
		Path fPath = Path.of(path);
		
		if(Files.exists(fPath)) {			
			String fName = FilenameUtils.removeExtension(fPath.getFileName().toString());			
			String dest = fPath.getParent() + "/" + fName + "_" + DateFormatter.getSimpleNow() + ".xlsx";
			try {
				FileUtils.copyFile(new File(path), new File(dest));
//				FileUtils.forceDelete(new File(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}		
		createFile();		
	}	

	private void createFile() {
		XSSFWorkbook wb = new XSSFWorkbook();
		try(var os = new FileOutputStream(new File(path))) {
			var sht = wb.createSheet(defaultSheetName);
			sht.createRow(0);
			ColumnHeading headings = new AuditHeadings();
			headings.createHeadings(sht);
			wb.write(os);
			wb.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
