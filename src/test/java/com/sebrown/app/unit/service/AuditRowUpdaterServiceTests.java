package com.sebrown.app.unit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.UTConfig;
import com.sebrown.app.dto.InstalledAppRowData;
import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.service.AuditRowUpdaterService;
import com.sebrown.app.service.WorksheetOutService;

/**
 * 
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class AuditRowUpdaterServiceTests {
	
	@Autowired
	private UTConfig config;
	
	@Autowired
	private WorksheetOutService shtServ;
	
	@Autowired
	private AuditRowUpdaterService updaterServ;
	
	private static String INSTALLED_SOFT_WB_PATH;
	private static String SHT_NAME;
		
	@BeforeAll
	public static void createWB(
		@Autowired UTConfig config, 
		@Autowired WorksheetOutService shtServ,
		@Autowired AuditOutFileGetter fileGetter) {
		
		fileGetter.getFile(
			new InstalledAppRowData.Builder()
				.setName("Some Software")
				.setIdentifyingNumber("{8055CFEA-3871-4C76-A321-E32F63637CC4}")
				.setVersion("1.2.3")						
				.getInstance());
		
		SHT_NAME = shtServ.getVendorNotFound().getName();
		INSTALLED_SOFT_WB_PATH = config.getAuditOutFullPath();
	}
	
	@AfterAll
	public static void deleteWB() throws IOException {
		FileUtils.forceDelete(
				new File(INSTALLED_SOFT_WB_PATH));
	}
	
	@Test
	void getInstance() {
		assertNotNull(updaterServ);
	}

	@Test
	void getIdForVendorNotFoundSheet() throws IOException {
		XSSFSheet sht;
		XSSFWorkbook wb = null;
		String id = null;
		String actName = null;
		int nameColPos = shtServ.getColumnNumber("name").orElse(2);
		
		try (var fileIn =	new FileInputStream(
				new File(INSTALLED_SOFT_WB_PATH))) {
			
			wb = new XSSFWorkbook(fileIn);
			sht = wb.getSheet(SHT_NAME);
						
			Row row1 = sht.getRow(1);
						
			id = updaterServ.updateRow(
					row1, 
					new InstalledAppRowData.Builder()
						.setName("Some Software")
						.setIdentifyingNumber("{8055CFEA-3871-4C76-A321-E32F63637CC4}")
						.setVersion("1.2.3")						
						.getInstance(),
						Path.of(config.getWbInFullPath()));
			
			actName = row1.getCell(nameColPos).getStringCellValue();
			
		} catch (IOException e) {	
			fail("Error getting WB: " + config.getAuditOutFullPath());
		}
		
		wb.close();
		
		assertTrue(id.startsWith("NO-ID"));
		assertEquals("Some Software", actName);
		
	}
	
}
