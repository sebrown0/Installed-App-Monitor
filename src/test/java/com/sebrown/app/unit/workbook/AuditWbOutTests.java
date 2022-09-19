/**
 * 
 */
package com.sebrown.app.unit.workbook;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.UTConfig;
import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.updater.AuditWbOut;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class AuditWbOutTests {
	
	@Autowired
	private AuditWbOut auditWbOut;
		
	private static String INSTALLED_SOFT_WB_PATH;
	
	@BeforeAll
	public static void createWB(
		@Autowired UTConfig config,		
		@Autowired AuditOutFileGetter fileGetter) {
		
		fileGetter.getFile();
		
		INSTALLED_SOFT_WB_PATH = config.getAuditOutFullPath();
	}
	
	@AfterAll
	public static void deleteWB() throws IOException {
		FileUtils.forceDelete(
				new File(INSTALLED_SOFT_WB_PATH));
	}
	
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
