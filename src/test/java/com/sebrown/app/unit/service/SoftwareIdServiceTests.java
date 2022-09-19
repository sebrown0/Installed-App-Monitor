package com.sebrown.app.unit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.UTConfig;
import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.service.SoftwareIdService;
import com.sebrown.app.service.WorksheetOutService;

@SpringBootTest
@ActiveProfiles("test")
class SoftwareIdServiceTests {

	@Autowired
	private UTConfig config;
	
	@Autowired
	private SoftwareIdService idServ;
	
	private static String INSTALLED_SOFT_WB_PATH;
	
	@BeforeAll
	public static void createWB(
		@Autowired UTConfig config, 
		@Autowired WorksheetOutService shtServ,
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
		assertNotNull(idServ);
	}

	@Test
	void getIdForVendorNotFoundSheet() throws IOException {
		XSSFSheet sht;
		XSSFWorkbook wb = null;
		String id = null;
		
		try (var fileIn =	new FileInputStream(new File(config.getAuditOutFullPath()))){
			wb = new XSSFWorkbook(fileIn);
			sht = wb.getSheet("Vendor Not Found");
			id = idServ.getId(sht);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		wb.close();
		
		assertTrue(id.startsWith("Vendor_"));
	}
}
