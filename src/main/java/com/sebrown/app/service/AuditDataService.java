/**
 * 
 */
package com.sebrown.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.sebrown.app.dto.RowData;
import com.sebrown.app.workbook.InstalledAppRowMapper;
import com.sebrown.app.workbook.SheetParser;

/**
 * @author SteveBrown
 *
 */
@Service
public class AuditDataService {
		
	public List<RowData> getAuditDataFromWb(Path p, String shtName)  {
		List<RowData> rowData = null;
		
		try (var fis = new FileInputStream(new File(p.toString()))){			
			XSSFWorkbook wbDeviceAudit = new XSSFWorkbook(fis);	    
			
			rowData = new SheetParser()
					.parseSheet(wbDeviceAudit.getSheet(shtName), new InstalledAppRowMapper())
					.getRowData();			
			
			wbDeviceAudit.close();
		} catch (IOException e) {
			// TODO - Log
			e.printStackTrace();
		}
		return rowData;
	}
}
