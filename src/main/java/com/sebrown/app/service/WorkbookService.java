/**
 * 
 */
package com.sebrown.app.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.sebrown.app.aspects.LogWorkbookComponent;
import com.sebrown.app.config.AppConfig.Workbook;
import com.sebrown.app.config.WorkbookProperties;

/**
 * @author SteveBrown
 *
 */
@Service
public class WorkbookService {

	private final WorkbookProperties wbProps;
	
	public WorkbookService(WorkbookProperties wbProps) {	
		this.wbProps = wbProps;
	}
	
	public Workbook getWbAuditIn() {
		return getWorkbook("auditIn");
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
