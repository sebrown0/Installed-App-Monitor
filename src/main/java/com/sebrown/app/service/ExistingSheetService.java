/**
 * 
 */
package com.sebrown.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.sebrown.app.worksheet.WorksheetCloser;

/**
 * @author SteveBrown
 *
 */
@Service
public class ExistingSheetService {

	private XSSFWorkbook wbAuditIn;		
	private FileInputStream fileIn;
	private final List<XSSFSheet> existingWorksheets = new ArrayList<>();
	
	private final InstalledWbPathService installedWbPathServ;
	
	public ExistingSheetService(InstalledWbPathService installedWbPathServ) {	
		this.installedWbPathServ = installedWbPathServ;
	}

	public List<XSSFSheet> getExistingSheets() {
		setWorkbook();
		
		wbAuditIn
			.sheetIterator()
			.forEachRemaining(s -> existingWorksheets.add((XSSFSheet) s));
		
		return existingWorksheets;
	}	
		
	private ExistingSheetService setWorkbook() {
		try {					
			var path = installedWbPathServ.getWbPath();
			
	System.out.println(">>>>>>PATH=" + path);
	
			fileIn =	new FileInputStream(
					new File(path));
			wbAuditIn = new XSSFWorkbook(fileIn);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			closeWb();			
			e.printStackTrace(); 
		}					
		return this;
	}
	
	private void closeWb() {
		try {
			fileIn.close();			
			WorksheetCloser.writeAndCloseWb(wbAuditIn, installedWbPathServ.getWbPath());			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
