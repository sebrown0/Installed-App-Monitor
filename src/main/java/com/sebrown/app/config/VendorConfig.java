/**
 * 
 */
package com.sebrown.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author SteveBrown
 *
 */
@Configuration
@ConfigurationProperties(prefix = "vendor")
public class VendorConfig {
	
	private String namesFile;
	
	public void setNamesfile(String namesFile) {
		this.namesFile = namesFile;
	}
	
	public String getVendorFileName() {
		return namesFile;
	}
	
}
