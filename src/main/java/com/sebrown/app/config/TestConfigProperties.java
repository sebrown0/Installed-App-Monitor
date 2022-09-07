/**
 * 
 */
package com.sebrown.app.config;

import java.util.Map;

/**
 * @author SteveBrown
 *
 */
public interface TestConfigProperties {
	Map<String, String> getIntegrationProps();
	Map<String, String> getUnitProps();	
}
