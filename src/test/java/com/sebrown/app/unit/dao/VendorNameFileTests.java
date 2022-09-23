/**
 * 
 */
package com.sebrown.app.unit.dao;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.config.ResourceConfig;
import com.sebrown.app.config.VendorConfig;
import com.sebrown.app.dao.VendorRepo;
import com.sebrown.app.model.VendorNames;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class VendorNameFileTests {

	@Autowired 	
	private VendorRepo repo;	
	
	@Autowired
	private ResourceConfig resource;
	
	@Autowired
	private VendorNames vendorNames;
	
	@Autowired
	private VendorConfig vendor;
	
	@Test 
	void getInstance() {
		assertNotNull(vendorNames);
	}
	
	@Test
	void getSecondName_shouldBeMicrosoft() {
		assertEquals("Microsoft", vendorNames.getNames().get(1));
	}
	
	@Test
	void appendNames() throws IOException {
		List<String> original = vendorNames.getNames();

		vendorNames.addNames(Arrays.asList("SAP", "CISCO"));
		repo.writeList(vendorNames.getNames());
		
		//Put it back to what it was
		Path fPath = Paths.get(
				resource.getPath() + "/" + vendor.getVendorFileName());
		
		Files.write(fPath, original, TRUNCATE_EXISTING, WRITE);
				
		assertEquals("Adobe", vendorNames.getNames().get(0));
		assertEquals("CISCO", vendorNames.getNames().get(1));
		assertEquals("Microsoft", vendorNames.getNames().get(2));
		assertEquals("SAP", vendorNames.getNames().get(3));
	}

}
