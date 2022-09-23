/**
 * 
 */
package com.sebrown.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebrown.app.dao.VendorAccronymFile;
import com.sebrown.app.dao.VendorRepo;

/**
 * @author SteveBrown
 *
 * Get the repo based on the config.
 */
@Service
public class VendorRepoGetter {

	@Autowired
	private VendorAccronymFile vendorAccFile;
	
	@Autowired
	private PersistanceConfig persistanceCnfg;
	
	public VendorRepo getRepo() {
		String type = persistanceCnfg.getType();
		
		VendorRepo repo = null;
		if(type.toLowerCase().equals("file")) {
			repo = vendorAccFile;
		}else {
			//will be DB repo.
		}		
		return repo;
	}
}
