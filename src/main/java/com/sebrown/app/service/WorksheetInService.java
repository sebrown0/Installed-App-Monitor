/**
 * 
 */
package com.sebrown.app.service;

import org.springframework.stereotype.Service;

import com.sebrown.app.annotations.LogWorkbookComponent;
import com.sebrown.app.config.AppConfig.Sheet;

/**
 * @author SteveBrown
 *
 * Get work sheet data from the audit out WB.
 */
@Service
public class WorksheetInService {
	
	private static final String WB_NAME = "auditIn";
	
	private final SheetService shtServ;
		
	public WorksheetInService(SheetService shtServ) {
		this.shtServ = shtServ;
	}

	@LogWorkbookComponent
	public Sheet getSheet(String shtName) throws IllegalArgumentException {		
		return shtServ.getSheet(WB_NAME, shtName);	
	}
	
	public Sheet getVendorNotFound() {
		return getSheet("vendorNotFound");
	}
		
	public Sheet getInstalledApps() {
		return getSheet("installedApps");
	}
	public Sheet getSystemInfo() {
		return getSheet("systemInfo");
	}
	
}
