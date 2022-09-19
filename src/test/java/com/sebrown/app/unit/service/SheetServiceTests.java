/**
 * 
 */
package com.sebrown.app.unit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.AppConfig.Sheet;
import com.sebrown.app.service.SheetService;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class SheetServiceTests {

	@Autowired
	SheetService shtServ;
	
	@Test
	void getInstanceOfSheetService() {
		assertNotNull(shtServ);
	}
		
	@Test
	void invalidSheetName_returnsNullObj() {
		Sheet sht = shtServ.getSheet("auditOut","XvendorNotFoundX"); 
		assertNull(sht);
	}
	
}
