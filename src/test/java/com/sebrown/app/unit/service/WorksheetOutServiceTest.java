/**
 * 
 */
package com.sebrown.app.unit.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.AppConfig.Sheet;
import com.sebrown.app.service.WorksheetOutService;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class WorksheetOutServiceTest {

	@Autowired
	WorksheetOutService wsOutServ;
	
	@Test
	void getInstance() {
		assertNotNull(wsOutServ);
	}
	
	@Test
	void getVendorNotFoundName() {
		Sheet vendorNotFound = wsOutServ.getSheet("vendorNotFound");
		assertEquals("Vendor Not Found", vendorNotFound.getName());
	}
	
	@Test
	void columnMappingsForVendorNotFound() {
		Map<String, String> mappings =
				wsOutServ.getVendorNotFoundMappings();
		int val = Integer.parseInt(mappings.get("name"));
		assertTrue(val > 0);
	}
	
	@Test
	void columnMappingsForVendorNotFound_name() {		
		assertTrue(wsOutServ.getColumnNumber("name").get() > 0);
	}

}
