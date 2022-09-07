/**
 * 
 */
package com.sebrown.app.service;

import org.springframework.stereotype.Service;

import com.sebrown.app.annotations.LogInfoMessage;

/**
 * @author SteveBrown
 *
 */
@Service
public class WorkbookPathService {
	
	private final WorkbookService wbServ;
		
	public WorkbookPathService(WorkbookService wbServ) {
		this.wbServ = wbServ;
	}

	@LogInfoMessage(msg = 
			"WorkbookPathService.getAuditOutFullPath: "
			+ "Getting Installed (Audit Out) WB Path")
	public String getAuditOutFullPath() {
		return wbServ.getWbAuditOutFullPath();		
	}
	
	@LogInfoMessage(msg = 
			"WorkbookPathService.getAuditInLocation: "
			+ "Getting Audit In location")
	public String getAuditInLocation() {
		return wbServ.getWbAuditInPath();		
	}
}
