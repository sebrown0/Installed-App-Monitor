package com.sebrown.app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sebrown.app.dao.VendorNameFileIn;
import com.sebrown.app.dao.VendorNameFileOut;
import com.sebrown.app.file.FileReader;
import com.sebrown.app.file.FileWriter;

/**
 * 
 * @author SteveBrown
 *
 */
@Configuration
public class BeanConfig {
	
	@Autowired
	private ResourceConfig resource;
	
	@Autowired
	private VendorConfig vendor;
	
	@Autowired
	private FileReader<List<String>> fileLineReader;
	
	@Autowired
	private FileWriter<List<String>> fileLineWriter;
	
	@Bean
	VendorNameFileIn vendorNameFileIn() {
		String path = resource.getPath();
		String fName = vendor.getVendorFileName();
		
		return new VendorNameFileIn(path + "/" + fName, fileLineReader);
	}
	
	@Bean
	VendorNameFileOut vendorNameFileOut() {
		String path = resource.getPath();
		String fName = vendor.getVendorFileName();
		
		return new VendorNameFileOut(path + "/" + fName, fileLineWriter);
	}

}
