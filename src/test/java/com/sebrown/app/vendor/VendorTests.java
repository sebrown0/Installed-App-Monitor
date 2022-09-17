/**
 * 
 */
package com.sebrown.app.vendor;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.ResourceConfig;
import com.sebrown.app.config.VendorConfig;
import com.sebrown.app.dao.VendorRepo;
import com.sebrown.app.service.VendorName;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class VendorTests {

	@Autowired
	private VendorName vendor;
	
	@Autowired
	private VendorRepo repo;	
	
	@Autowired
	private ResourceConfig resource;
	
	@Autowired
	private VendorConfig venCnfg;
	
	@Test
	void getInstance() {
		assertNotNull(vendor);
	}
	
	@Test
	void getVendorThatIsNotInFile_shouldAddNameToFile	() throws IOException {		
		//Get the original so we can restore
		List<String> original = repo.getVendorNames();
		
		//Get a vendor name that's not in the file.
		vendor.getVendor("Citrix, INC");
		vendor.flush();
		
		//Get the updated list from file
		List<String> names = repo.getVendorNames();
		
		//Put it back to what it was		
		Path fPath = Paths.get(
				resource.getPath() + "/" + venCnfg.getVendorFileName());
		
		Files.write(fPath, original, TRUNCATE_EXISTING, WRITE);
				
		assertEquals("Citrix", names.get(1));
	}

}
