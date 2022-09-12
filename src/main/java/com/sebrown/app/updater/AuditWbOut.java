package com.sebrown.app.updater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.sebrown.app.annotations.HandleErr;
import com.sebrown.app.service.ExistingSheetService;
import com.sebrown.app.service.WorkbookService;
import com.sebrown.app.worksheet.WorkbookCloser;

/**
 * 
 * @author SteveBrown
 * 
 * A workbook with the audit info from a device.
 * Use to transfer the info to the 'Installed Software' WB.
 *
 */
@Component
public class AuditWbOut implements AutoCloseable {
	
	private XSSFWorkbook wbAuditOut;		
	private FileInputStream fis;
	private List<XSSFSheet> existingWorksheets;
		
	private final ExistingSheetService shtServ;		
	private final String wbOutPath;
	
	public AuditWbOut(ExistingSheetService shtServ, WorkbookService wbServ) {		
		this.shtServ = shtServ;
		this.wbOutPath = "./" + wbServ.getWbAuditOutFullPath();
		
		setOutputWorkbook();
	}

	private void setOutputWorkbook() {
		try {					
			fis =	new FileInputStream(new File(wbOutPath));
			wbAuditOut = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
		} catch (IOException e) { 
			closeWb(); 
		}					
	}
		
	private List<XSSFSheet> getExistingWorksheets(){
		return (Objects.isNull(existingWorksheets)) ? 
				shtServ.getExistingSheets(wbAuditOut) : existingWorksheets;
	}
	
	public Optional<XSSFSheet> containsWs(String wsName) {
		return 
				getExistingWorksheets().stream()
				.filter(s -> s.getSheetName().equals(wsName))
				.findFirst();
	}
	
	public XSSFSheet addWs(String shtName) {
		var sht = wbAuditOut.createSheet(shtName);
		existingWorksheets.add(sht);
		
		return sht;
	}

	@HandleErr
	private void closeWb() {
		try {
			fis.close();
			WorkbookCloser.writeAndCloseWb(wbAuditOut, wbOutPath);			
		} catch (IOException e) { 
			//ErrorLoggingAspect			
		}
	}

	@Override
	public void close() throws Exception {
		closeWb();
	}
}
