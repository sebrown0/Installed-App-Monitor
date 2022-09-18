/**
 * 
 */
package com.sebrown.app.unit.workbook;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.updater.AuditWbOut;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class AuditWbOutTests {
	
	@Autowired
	private AuditWbOut auditWbOut;
	
	@Test
	void getInstance() {
		assertNotNull(auditWbOut);
	}
	
	@Test
	void containsVendorNotFoundSheet() throws IOException {				
			
		Optional<XSSFSheet> sht = 
				auditWbOut.containsWs("Vendor Not Found");
		
		assertTrue(sht.isPresent());
				
	}

}
