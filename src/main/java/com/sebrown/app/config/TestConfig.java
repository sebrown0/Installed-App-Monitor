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
public class TestConfig implements 
	TestConfigProperties, UTConfigProperties {
	
	private TestProps testProps;

	public void setTest(TestProps testProps) {
		this.testProps = testProps;
	}
	
	@Override //TestConfigProperties
	public Map<String, String> getUnitProps() {
		return testProps.unit;
	}
	@Override //TestConfigProperties
	public Map<String, String> getIntegrationProps() {
		return testProps.integration;
	}

	@Override //UTConfigProperties
	public String getWbPath() {
		return 
			testProps.unit
				.getOrDefault("wbpath", "src/test/resources");
	}

	@Override //UTConfigProperties
	public String getWbInName() {
		return 
			testProps.unit
				.getOrDefault("wbname", "ISO-Audit-Test-26-08-22_08-47-37.xlsm");
	}

	@Override //UTConfigProperties
	public String getWbOutName() {
		return 
				testProps.unit
					.getOrDefault("auditworkbookpath", 
							getWbPath() + "/Installed Software.xlsx");
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
