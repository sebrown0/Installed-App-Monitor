/**
 * 
 */
package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.AppConfig.Sheet;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class SheetServiceTests {

	@Autowired
	SheetService shtServ;
	
	@Test
	void getInstanceOfSheetService() {
		assertNotNull(shtServ);
	}
	
	@Test
	void getVendorNotFoundName() {
		Sheet vendorNotFound = shtServ.getSheet("auditOut", "vendorNotFound");
		assertEquals("Vendor Not Found", vendorNotFound.getName());
	}
	
	@Test
	void invalidSheetName_returnsNullObj() {
		Sheet sht = shtServ.getSheet("auditOut","XvendorNotFoundX"); 
		assertNull(sht);
	}

}
