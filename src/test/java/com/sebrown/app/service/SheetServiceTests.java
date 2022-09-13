/**
 * 
 */
package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
	void invalidSheetName_returnsNullObj() {
		Sheet sht = shtServ.getSheet("auditOut","XvendorNotFoundX"); 
		assertNull(sht);
	}
	
}
