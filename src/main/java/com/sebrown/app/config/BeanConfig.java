package com.sebrown.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.model.VendorList;
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
	private VendorRepoGetter vendorRepo;
	
	@Autowired
	private RowCreator rowServ;
	
	@Autowired
	private VendorNameRules venNameRules;
			
	@Bean @Scope("prototype")
	VendorName vendorName() {
		return new VendorName(vendorRepo.getRepo(), venNameRules);
	}
		
	@Bean //@Scope("prototype")
	VendorAcronymService vendorAccronymService() {
		return new VendorAcronymService(vendorRepo.getRepo());
	}
	
	@Bean
	AuditOutFileGetter auditOutFileGetter() {
		return new AuditOutFileGetter(
				config, "Vendor Not Found", rowServ);
	}
	
	@Bean
	VendorList acronymList() {		
		return 
			new VendorList(vendorRepo.getRepo())
				.setList();
	}

}
