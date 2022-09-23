/**
 * 
 */
package com.sebrown.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.sebrown.app.dao.VendorFile;
import com.sebrown.app.dao.VendorRepo;

/**
 * @author SteveBrown
 *
 * Get the repo based on the config.
 */
@Component 
public abstract class VendorRepoGetter {
	
	@Autowired
	private PersistanceConfig persistanceCnfg;
	
	//will have to be changed to DB as well when added...
	protected abstract VendorFile getX();
	
	public VendorRepo getRepo() {
		String type = persistanceCnfg.getType();
		
		VendorRepo repo = null;
		if(type.toLowerCase().equals("file")) {
			repo = this.getX();
		}else {
			//will be DB repo.
		}		
		return repo;
	}
}
