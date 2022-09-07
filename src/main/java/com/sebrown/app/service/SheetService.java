/**
 * 
 */
package com.sebrown.app.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.sebrown.app.annotations.LogWorkbookComponent;
import com.sebrown.app.config.AppConfig.Sheet;

/**
 * @author SteveBrown
 *
 */
@Service
public class SheetService {
	
	private final WorkbookService wbServ;
	
	public SheetService(WorkbookService wbServ) {
		super();
		this.wbServ = wbServ;
	}

	public Sheet getInstalledApps() {
		return getSheet("auditIn", "installedApps");
	}
	public Sheet getSystemInfo() {
		return getSheet("auditIn", "systemInfo");
	}
	
	public Sheet getVendorNotFound() {
		return getSheet("auditOut", "vendorNotFound");
	}
	
	@LogWorkbookComponent
	public Sheet getSheet(String wbName, String shtName) throws IllegalArgumentException {		
		Sheet sht = null;
		var wb = wbServ.getWorkbook(wbName);
		
		if(Objects.nonNull(wb)) {
			sht = wb.getSheets().get(shtName);
			if(Objects.nonNull(sht)) {
				return sht;			
			}
		}
		
		throw new IllegalArgumentException(
				String.format("Workbook: %s, Sheet: %s", wbName, shtName));
		
	}

}
