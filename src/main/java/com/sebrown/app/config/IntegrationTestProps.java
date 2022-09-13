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
public class IntegrationTestProps {

	@Autowired
	ITConfig config;

	public String getITResourcePath() {
		return config
				.getIntegrationProps()
				.getOrDefault("wbpath", "src/test/resources/integration");
	}

	public String getAuditOutFullPath() {
		return 
			getITResourcePath() + 
			"/" + 
			config.getAuditOutWbName();				
	}	
	
}
