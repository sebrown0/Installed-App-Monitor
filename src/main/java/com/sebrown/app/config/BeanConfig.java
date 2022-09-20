package com.sebrown.app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sebrown.app.dao.VendorFile;
import com.sebrown.app.dao.VendorFileIn;
import com.sebrown.app.dao.VendorFileOut;
import com.sebrown.app.dao.VendorNameReader;
import com.sebrown.app.dao.VendorNameWriter;
import com.sebrown.app.dao.VendorRepo;
import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.file.FileReader;
import com.sebrown.app.file.FileWriter;
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
	private RowCreator rowServ;
	
//	@Autowired
//	private ResourceConfig resource;
//		
//	@Autowired
//	private VendorConfig vendorCnfg;
		
//	@Autowired
//	private FileReader<List<String>> fileLineReader;
//	
//	@Autowired
//	private FileWriter<List<String>> fileLineWriter;
	
//	@Autowired
//	private VendorNameReader reader;
//	@Autowired
//	private VendorNameWriter writer;
	
	/*
	 * vendor repo is file or db
	 * set the repo to one of them and configure
	 *   file path or db conn etc...
	 */
//	@Bean
//	VendorFileIn vendorNameFileIn() {
//		String path = resource.getPath();
//		String fName = vendorCnfg.getVendorFileName();
//		
//		return new VendorFileIn(path + "/" + fName, fileLineReader);
//	}
//	
//	@Bean
//	VendorFileOut vendorNameFileOut() {
//		String path = resource.getPath();
//		String fName = vendorCnfg.getVendorFileName();
//		
//		return new VendorFileOut(path + "/" + fName, fileLineWriter);
//	}

//	@Bean
//	VendorRepo vendorRepo() {
//		return new VendorFile(reader, writer);
//	}
	
	@Bean
	AuditOutFileGetter auditOutFileGetter() {
		return new AuditOutFileGetter(
				config, "Vendor Not Found", rowServ);
	}
}
