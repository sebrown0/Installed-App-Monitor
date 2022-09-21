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
public class VendorConfig implements VendorFiles, VendorAccronym {
	
	private String namesFile;
	private String accronymFile;
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
	public void setAccronymFile(String accronymFile) {
		this.accronymFile = accronymFile;
	}
	
	@Override
	public int getMinAcnymLen() {
		try {
			return Integer.parseInt(minAcnymLen);	
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return 2;
	}
	@Override
	public int getMaxAcnymLen() {
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
	@Override
	public String getAccronymFileName() {
		return accronymFile;
	}
		
}
