/**
 * 
 */
package com.sebrown.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 */
@Component
public class UnitTestProps {

	@Autowired
	UTConfig config;

	public String getUTResourcePath() {
		return config
				.getUnitProps()
				.getOrDefault("wbpath", "src/test/resources");
	}

	public String getAuditOutFullPath() {
		return 
			getUTResourcePath() + 
			"/" + 
			config.getAuditOutWbName();				
	}	
	
	public String getWbInName() {
		return 
			config.getUnitProps()
				.getOrDefault("wbname", "ISO-Audit-Test-26-08-22_08-47-37.xlsm");
	}
	
}
