/**
 * 
 */
package com.sebrown.app.config;

/**
 * @author SteveBrown
 *
 * Get the properties for tests.
 */
public interface Config {
	
	String getResourcePath(); 	//Location of all resources	
	String getAuditOutFullPath(); //The destination WB, i.e., Installed Software
		
}
