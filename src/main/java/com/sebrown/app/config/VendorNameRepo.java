/**
 * 
 */
package com.sebrown.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebrown.app.dao.VendorFile;
import com.sebrown.app.dao.VendorNameFile;

/**
 * @author SteveBrown
 *
 * Get the repo based on the config.
 */
@Component
public class VendorNameRepo extends VendorRepoGetter {
	
	@Autowired
	private VendorNameFile vendorNameFile;

	@Override
	protected VendorFile getX() {
		return vendorNameFile;
	}	

}
