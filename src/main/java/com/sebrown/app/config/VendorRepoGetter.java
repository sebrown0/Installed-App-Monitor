/**
 * 
 */
package com.sebrown.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebrown.app.dao.VendorFile;
import com.sebrown.app.dao.VendorRepo;

/**
 * @author SteveBrown
 *
 * Get the repo based on the config.
 */
@Component 
public class VendorRepoGetter {
	
	@Autowired
	private VendorFile vendorFile;
	
	@Autowired
	private PersistanceConfig persistanceCnfg;
	
	public VendorRepo getRepo() {
		String type = persistanceCnfg.getType();
		
		VendorRepo repo = null;
		if(type.toLowerCase().equals("file")) {
			repo = vendorFile;
		}else {
			/*
			 * Will be DB repo. 
			 * Add dependency and make both lazy?
			 */
		}		
		return repo;
	}
}
