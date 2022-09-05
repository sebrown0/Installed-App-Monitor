/**
 * 
 */
package com.sebrown.app.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author SteveBrown
 *
 */

@Configuration
@ConfigurationProperties(prefix = "props")
public class TestConfig implements TestConfigProperties {

	private TestProps testProps;

	public void setTest(TestProps testProps) {
		this.testProps = testProps;
	}
	
	@Override
	public Map<String, String> getUnitProps() {
		return testProps.unit;
	}
	@Override
	public Map<String, String> getIntegrationProps() {
		return testProps.integration;
	}
		
	public static class TestProps {
		private Map<String, String> unit;
		private Map<String, String> integration;		
		
		public void setIntegration(Map<String, String> integration) {
			this.integration = integration;
		}	
		public void setUnit(Map<String, String> unit) {
			this.unit = unit;
		}	
	}
	
}
