package com.sebrown.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.model.VendorList;
import com.sebrown.app.row.RowCreator;

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
