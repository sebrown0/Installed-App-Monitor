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
import java.nio.file.Paths;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sebrown.app.config.Config;
import com.sebrown.app.dto.InstalledAppRowData;
import com.sebrown.app.row.RowCreator;
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

/*
 * Not in component scan, using BeanConfig.
 */
public class AuditOutFileGetter {
	//TODO re-factor whole class
	//--------------------------
	
	private final String path;
	private final String defaultSheetName;
	private final RowCreator rowServ;
	
	public AuditOutFileGetter(Config props, String defaultSheetName) {		
		this.path = props.getAuditOutFullPath();
		this.defaultSheetName = defaultSheetName;
		this.rowServ = null;
	}
	
	public AuditOutFileGetter(
		Config props, 
		String defaultSheetName, 
		RowCreator rowServ) {
		
		this.path = props.getAuditOutFullPath();
		this.defaultSheetName = defaultSheetName;
		this.rowServ = rowServ;
	}
		
	public void getFile(InstalledAppRowData...rows) {
		Path fPath = Path.of(path);
		
		if(Files.exists(fPath)) {			
			String fName = FilenameUtils.removeExtension(fPath.getFileName().toString());			
			String dest = fPath.getParent() + "/" + fName + "_" + DateFormatter.getSimpleNow() + ".xlsx";
			try {
				FileUtils.copyFile(new File(path), new File(dest));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}		
		createFile(rows);		
	}	

	private void createFile(InstalledAppRowData...rows) {
		XSSFWorkbook wb = new XSSFWorkbook();
		try(var os = new FileOutputStream(new File(path))) {
			var sht = wb.createSheet(defaultSheetName);
			sht.createRow(0);
			ColumnHeading headings = new AuditHeadings();
			headings.createHeadings(sht);
			writeAnyData(sht, rows);
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
	
	private void writeAnyData(
		XSSFSheet sht, InstalledAppRowData...rows) {
		
		if(Objects.nonNull(rowServ)) {
			for(InstalledAppRowData data : rows) {
				rowServ.createRow(sht, data, Paths.get(path));				
			}
		}		
	}
	
}
