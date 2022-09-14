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
public class UnitTestProps implements UTConfig {
	
	@Autowired
	TestConfig config;

	@Override
	public String getResourcePath() {
		return config
				.getUnitProps()
				.getOrDefault("wbpath", "src/test/resources");
	}

	@Override
	public String getAuditOutFullPath() {
		return 
			getResourcePath() + 
			"/" + 
			config.getAuditOutWbName();				
	}	

	@Override
	public String getWbInName() {
		return 
			config
				.getUnitProps()
				.getOrDefault("wbname", "ISO-Audit-Test-26-08-22_08-47-37.xlsm");
	}

	@Override
	public String getWbInFullPath() {
		return this.getResourcePath() + "/" + this.getWbInName();
	}
	
}
