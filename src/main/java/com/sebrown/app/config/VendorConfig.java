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
public class VendorConfig implements VendorFiles, VendorAcronym {
	
	private String namesFile;
	private String minAcnymLen;
	private String maxAcnymLen;
			
	public void setMinAcnymLen(String minAcnymLen) {
		this.minAcnymLen = minAcnymLen;
	}	
	public void setMaxAcnymLen(String maxAcnymLen) {
		this.maxAcnymLen = maxAcnymLen;
	}
	
	public void setNamesfile(String namesFile) {
		this.namesFile = namesFile;
	}
		
	@Override
	public int getMinAcronymLen() {
		try {
			return Integer.parseInt(minAcnymLen);	
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return 2;
	}
	@Override
	public int getMaxAcronymLen() {
		try {
			return Integer.parseInt(maxAcnymLen);	
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return 5;		
	}
	
	@Override
	public String getVendorFileName() {
		return namesFile;
	}
		
}
