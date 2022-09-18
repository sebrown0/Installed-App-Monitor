/**
 * 
 */
package com.sebrown.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 * Properties for integration tests.
 */
@Component
@Profile("integration")
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
	
	public String getExpectedResultsFullPath() {
		return config
				.getIntegrationProps()
				.getOrDefault(
						"expectedpath", 
						"src/test/resources/integration/IntegrationTest-ExpectedResults.xlsx");
	}
}
