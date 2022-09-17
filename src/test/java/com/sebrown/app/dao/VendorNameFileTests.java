/**
 * 
 */
package com.sebrown.app.dao;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.ResourceConfig;
import com.sebrown.app.config.VendorConfig;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class VendorNameFileTests {

	@Autowired
	private VendorRepo repo;	
	
	@Autowired
	private ResourceConfig resource;
	
	@Autowired
	private VendorConfig vendor;
	
	@Test 
	void getInstance() {
		assertNotNull(repo);
		assertNotNull(resource);
		assertNotNull(vendor);
	}
	
	@Test
	void getFirstName_shouldBeMicrosoft() {
		assertEquals("Microsoft", repo.getVendorNames().get(0));
	}
	
	@Test
	void appendNames() throws IOException {		
		List<String> names = repo.getVendorNames();
		List<String> original = new ArrayList<>(names);

		names.addAll(Arrays.asList("SAP", "CISCO"));
		names.sort(String::compareToIgnoreCase);
		repo.writeVendorNames(names);
		
		//Put it back to what it was
		Path fPath = Paths.get(
				resource.getPath() + "/" + vendor.getVendorFileName());
		
		Files.write(fPath, original, TRUNCATE_EXISTING, WRITE);
				
		assertEquals("Adobe", names.get(0));
		assertEquals("CISCO", names.get(1));
		assertEquals("Microsoft", names.get(2));
		assertEquals("SAP", names.get(3));
	}

}
