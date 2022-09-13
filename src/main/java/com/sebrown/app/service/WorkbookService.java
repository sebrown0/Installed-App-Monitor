/**
 * 
 */
package com.sebrown.app.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.sebrown.app.config.AppConfig.Workbook;
import com.sebrown.app.annotations.LogWorkbookComponent;
import com.sebrown.app.config.WorkbookProperties;

/**
 * @author SteveBrown
 *
 * Get work book data for either the in or out WBs.
 */
@Service
public class WorkbookService {

	private final WorkbookProperties wbProps;
	private final ResourceService resServ;
	
	public WorkbookService(
			WorkbookProperties wbProps, ResourceService resServ) {	
		
		this.wbProps = wbProps;
		this.resServ = resServ;
	}
	
	public Workbook getWbAuditIn() {
		return getWorkbook("auditIn");
	}
	public String getWbAuditInPath() {		
		return getResourcePath();
	}
	
	public String getWbAuditOutFullPath() {
		return getFullPathOfWb(getWbAuditOut().getName());
	}
	public Workbook getWbAuditOut() {
		return getWorkbook("auditOut");
	}
	
	private String getResourcePath() {
		return String.format("%s/", resServ.getPath());		
	}
	
	private String getFullPathOfWb(String wbName) {
		return String.format("%s%s", 
				getResourcePath(), wbName);
	}
	
	@LogWorkbookComponent
	public Workbook getWorkbook(String wbName) throws IllegalArgumentException {
		var workbooks = wbProps.getWorkbooks();		
		
		if(Objects.nonNull(wbName) && Objects.nonNull(workbooks)) {
			var wb = workbooks.get(wbName);
			if(Objects.nonNull(wb)) {
				return wb;	
			}
				
		}
		
		throw new IllegalArgumentException(
				String.format("Workbook: %s", wbName));		
	}
	
}
