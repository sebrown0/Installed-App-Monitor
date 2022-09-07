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

import com.sebrown.app.service.ExistingSheetService;

/**
 * 
 * @author SteveBrown
 *
 */
public class AuditWbIn implements AutoCloseable {
	
	private XSSFWorkbook wbAuditIn;		
	private FileInputStream fileIn;
	private List<XSSFSheet> existingWorksheets;
		
	private final ExistingSheetService shtServ;		
	
	public AuditWbIn(
		ExistingSheetService shtServ,		
		String wbInPath) {
		
		this.shtServ = shtServ;
		
		setWorkbook(wbInPath);
	}

	private AuditWbIn setWorkbook(String wbPath) {
				
		try {					
			fileIn =	new FileInputStream(
					new File(wbPath));
			wbAuditIn = new XSSFWorkbook(fileIn);
		} catch (FileNotFoundException e) {
		} catch (IOException e) { 
			closeWb(); 
		}					
		return this;
	}
		
	private List<XSSFSheet> getExistingWorksheets(){
		return (Objects.isNull(existingWorksheets)) ? 
				shtServ.getExistingSheets(wbAuditIn) : existingWorksheets;
	}
	
	public Optional<XSSFSheet> containsWs(String wsName) {
		return 
				getExistingWorksheets().stream()
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
			System.out.println("+++CLOCSING WB");
//			WorkbookCloser.writeAndCloseWb(
//					wbAuditIn, wbPathServ.getAuditOutFullPath());			
		} catch (IOException e) { 
			//ErrorLoggingAspect			
		}
	}

	@Override
	public void close() throws Exception {
		closeWb();
	}
			
}
