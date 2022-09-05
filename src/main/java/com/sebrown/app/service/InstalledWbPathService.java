/**
 * 
 */
package com.sebrown.app.service;

import org.springframework.stereotype.Service;

import com.sebrown.app.config.ResourceProperties;

/**
 * @author SteveBrown
 *
 */
@Service
public class InstalledWbPathService {

	private final ResourceProperties resourceProps;
	private final WorkbookService wbServ;
	
	public InstalledWbPathService(ResourceProperties resourceProps, WorkbookService wbServ) {
		super();
		this.resourceProps = resourceProps;
		this.wbServ = wbServ;
	}

	public String getWbPath() {
		return 
				resourceProps.getProps().getPath() + 
				"/" +
				wbServ.getWorkbook("auditOut").getName();
	}
}
