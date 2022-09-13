/**
 * 
 */
package com.sebrown.app.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sebrown.app.annotations.LogWorkbookComponent;
import com.sebrown.app.config.AppConfig.Sheet;

/**
 * @author SteveBrown
 *
 * Get work sheet data from the audit out WB.
 */
@Service
public class WorksheetOutService {
	
	private static final String WB_NAME = "auditOut";
	
	private final SheetService shtServ;
		
	public WorksheetOutService(SheetService shtServ) {
		this.shtServ = shtServ;
	}

	@LogWorkbookComponent
	public Sheet getSheet(String shtName) throws IllegalArgumentException {		
		return shtServ.getSheet(WB_NAME, shtName);	
	}
	
	public Sheet getVendorNotFound() {
		return getSheet("vendorNotFound");
	}
		
	public Map<String, String> getVendorNotFoundMappings() {
		return getVendorNotFound().getColumnMappings();
	}

	public Optional<Integer> getColumnNumber(String forName) {		
		Optional<Integer> col = Optional.empty();
		try {
			col = Optional.of(
					Integer.parseInt(
							getVendorNotFoundMappings().get(forName)));
		} catch (NumberFormatException e) {			
		}		
		return col;
	}
	
}
