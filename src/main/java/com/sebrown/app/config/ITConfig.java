/**
 * 
 */
package com.sebrown.app.config;

import java.util.Map;

/**
 * @author SteveBrown
 *
 * Get the properties for integration tests.
 */
public interface ITConfig {
	
	String getAuditOutWbName(); //The destination WB, i.e., Installed Software	
	Map<String, String> getIntegrationProps();
	
}
