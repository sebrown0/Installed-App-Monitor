/**
 * 
 */
package com.sebrown.app.config;

import java.util.Map;

/**
 * @author SteveBrown
 *
 * Get the properties for unit tests.s
 */
public interface UTConfig {
	
	String getAuditOutWbName(); //The destination WB, i.e., Installed Software	
	Map<String, String> getUnitProps();	
	
}
