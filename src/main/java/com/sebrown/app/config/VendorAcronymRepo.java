/**
 * 
 */
package com.sebrown.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebrown.app.dao.VendorAccronymFile;
import com.sebrown.app.dao.VendorFile;

/**
 * @author SteveBrown
 *
 * Get the repo based on the config.
 */
@Component
public class VendorAcronymRepo extends VendorRepoGetter {
	
	@Autowired
	private VendorAccronymFile vendorAcronymFile;

	@Override
	protected VendorFile getX() {
		return vendorAcronymFile;
	}	

}
