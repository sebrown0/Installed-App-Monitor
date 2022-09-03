package com.sebrown.app.updater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sebrown.app.worksheet.WorksheetCloser;

@Component
public class AuditWbIn {
		
	@Value("${auditWorkbookPath}")
	private String auditWorkbookPath;
		
	private XSSFWorkbook wbAuditIn;
		
	private FileInputStream fileIn;
	private final List<XSSFSheet> existingWorksheets = new ArrayList<>();
		
	public AuditWbIn setWorkbook() {
		try {
			fileIn =	new FileInputStream(new File(auditWorkbookPath));
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
	
	public void getExistingSheets() {
		wbAuditIn
			.sheetIterator()
			.forEachRemaining(s -> existingWorksheets.add((XSSFSheet) s));		
	}	
	
	public Optional<XSSFSheet> containsWs(String wsName) {
		return 
			existingWorksheets.stream()
				.filter(s -> s.getSheetName().equals(wsName))
				.findFirst();
	}
	
	public XSSFSheet addWs(String shtName) {
		var sht = wbAuditIn.createSheet(shtName);
		existingWorksheets.add(sht);
		
		return sht;
	}

	public void closeWb() {
		try {
			fileIn.close();			
			WorksheetCloser.writeAndCloseWb(wbAuditIn, auditWorkbookPath);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
