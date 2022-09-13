/**
 * 
 */
package com.sebrown.app.config;

import java.util.Map;
import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author SteveBrown
 *
 */

@Configuration
@ConfigurationProperties(prefix = "props")
public class TestConfig implements UTConfig, ITConfig {
	
	private TestProps testProps;

	public void setTest(TestProps testProps) {
		this.testProps = testProps;
	}
	
	@Override //UTConfig, ITConfig
	public String getAuditOutWbName() { 
		String name = testProps.auditOutWb; 
		return (Objects.nonNull(name)) ? name : "Installed Software.xlxs";
	}
	
	@Override //UTConfig
	public Map<String, String> getUnitProps() {
		return testProps.unit;
	}
	
	@Override //ITConfig
	public Map<String, String> getIntegrationProps() {
		return testProps.integration;
	}
	
	public static class TestProps {
		private String auditOutWb;		
		private Map<String, String> unit;
		private Map<String, String> integration;		
		
		public void setAuditOutWb(String auditOutWb) {
			this.auditOutWb = auditOutWb;
		}
		public void setIntegration(Map<String, String> integration) {
			this.integration = integration;
		}	
		public void setUnit(Map<String, String> unit) {
			this.unit = unit;
		}	
	}
	
}
