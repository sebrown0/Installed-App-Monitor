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
public class VendorConfig implements VendorFiles {
	
	private String namesFile;
	private String accronymFile;
	
	public void setNamesfile(String namesFile) {
		this.namesFile = namesFile;
	}
	public void setAccronymFile(String accronymFile) {
		this.accronymFile = accronymFile;
	}
	
	@Override
	public String getVendorFileName() {
		return namesFile;
	}
	@Override
	public String getAccronymFileName() {
		return accronymFile;
	}
	
	
	
}
