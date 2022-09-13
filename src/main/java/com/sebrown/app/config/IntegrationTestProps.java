/**
 * 
 */
package com.sebrown.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 * Properties for integration tests.
 */
@Component
public class IntegrationTestProps implements Config {

	@Autowired
	TestConfig config;

	@Override
	public String getResourcePath() {
		return config
				.getIntegrationProps()
				.getOrDefault("wbpath", "src/test/resources/integration");
	}

	@Override
	public String getAuditOutFullPath() {
		return 
			getResourcePath() + 
			"/" + 
			config.getAuditOutWbName();				
	}	
	
}
