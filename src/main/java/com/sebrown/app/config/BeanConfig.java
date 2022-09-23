package com.sebrown.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.sebrown.app.dao.VendorAccronymFile;
import com.sebrown.app.dao.VendorNameFile;
import com.sebrown.app.dao.VendorRepo;
import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.row.RowCreator;
import com.sebrown.app.service.VendorAcronymService;
import com.sebrown.app.service.VendorName;
import com.sebrown.app.service.VendorNameRules;

/**
 * 
 * @author SteveBrown
 *
 */
@Configuration
public class BeanConfig {
	
	@Autowired
	private Config config;
	
	@Autowired
	private PersistanceConfig persistanceCnfg;
	
	@Autowired
	private RowCreator rowServ;
	
	@Autowired
	private VendorNameRules venNameRules;
	
	@Autowired
	private VendorNameFile vendorNameFile;
	
	@Autowired
	private VendorAccronymFile vendorAccFile;
	
	@Bean @Scope("prototype")
	VendorName vendorName() {
		String type = persistanceCnfg.getType();
		
		VendorRepo repo = null;
		if(type.toLowerCase().equals("file")) {
			repo = vendorNameFile;
		}else {
			//will be DB repo.
		}
		return new VendorName(repo, venNameRules);
	}
	
	//TODO - HAVE CLASS TO DO ABOVE & BELOW BOILER PLATE
	
	@Bean //@Scope("prototype")
	VendorAcronymService vendorAccronymService() {
		String type = persistanceCnfg.getType();
		
		VendorRepo repo = null;
		if(type.toLowerCase().equals("file")) {
			repo = vendorAccFile;
		}else {
			//will be DB repo.
		}
		return new VendorAcronymService(repo);
	}
	
	@Bean
	AuditOutFileGetter auditOutFileGetter() {
		return new AuditOutFileGetter(
				config, "Vendor Not Found", rowServ);
	}

}
