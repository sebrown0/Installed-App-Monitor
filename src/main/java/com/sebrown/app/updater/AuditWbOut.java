package com.sebrown.app.updater;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.sebrown.app.config.Config;
import com.sebrown.app.error.ErrorHandler;
import com.sebrown.app.error.ErrorToHandle;
import com.sebrown.app.error.ErrorToHandle.ErrorSeverity;
import com.sebrown.app.error.LogAndHandleError;
import com.sebrown.app.error.RaisedError;
import com.sebrown.app.service.ExistingSheetService;
import com.sebrown.app.service.WorkbookReader;
import com.sebrown.app.worksheet.WorkbookCloser;

/**
 * 
 * @author SteveBrown
 * 
 * 'Installed Software' WB.
 *
 */
@Component
public class AuditWbOut implements AutoCloseable {
	
	private XSSFWorkbook wbAuditOut;		
	private FileInputStream fis;
	private List<XSSFSheet> existingWorksheets;
		
	private final ExistingSheetService shtServ;		
	
	private String wbOutPath;
	private boolean outputWritten;
	private WorkbookReader wbReader;
	
	public AuditWbOut(ExistingSheetService shtServ) {		
		this.shtServ = shtServ;		
	}
	
//	NOT WORKING !!!!
//	@HandleErr
//	@LogInfoMessage(msg = "Opening audit out WB") 
	protected Optional<ErrorHandler> setOutputWorkbook(Config props) {
		wbOutPath = props.getAuditOutFullPath();
		outputWritten = false;
				
		try {					
			fis =	new FileInputStream(new File(wbOutPath));
			wbAuditOut = new XSSFWorkbook(fis);			
		} catch (IOException e) {
			RaisedError err = new ErrorToHandle(new LogAndHandleError());
			return Optional.of(err.setSeverity(ErrorSeverity.CRITICAL));
		}	
		
		return Optional.empty();
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
		getExistingWorksheets().add(sht);
		
		return sht;
	}
	
//	@HandleErr
	private void closeWb() {
		try {
//			wbReader.close();
			fis.close();
			if(false == outputWritten) {
				WorkbookCloser.writeAndCloseWb(wbAuditOut, wbOutPath);		
				outputWritten = true;
			}
		} catch (Exception e) { 
			//ErrorLoggingAspect			
		}
	}

	@Override
	public void close() {		
		closeWb();
	}
}
