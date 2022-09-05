/**
 * 
 */
package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class ExistingSheetServiceTests {

	@Autowired
	ExistingSheetService shtServ;
	
	@Test
	void test() {
		assertNotNull(shtServ);
	}
	
	@Test
	void getSheets() {
		List<XSSFSheet> sheets = shtServ.getExistingSheets();		
		assertNotNull(sheets);
	}

	@Test
	void getFirstSheet_shouldBeVendorNotFound() {
		XSSFSheet sheet = shtServ.getExistingSheets().get(0);
		
		assertEquals("Vendor Not Found", sheet.getSheetName());
	}
}
