package com.sebrown.app.unit.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.config.UTConfig;
import com.sebrown.app.service.SoftwareIdService;
import com.sebrown.app.utils.TempFile;
import com.sebrown.app.worksheet.RowNumber;

@UnitTest
class SoftwareIdServiceTests {
	
	@Autowired
	private UTConfig config;
	
	@Autowired
	private RowNumber rowNumber;
	
	@Autowired
	private SoftwareIdService idServ;

	private static TempFile tempFile;
	private static XSSFWorkbook wb;	
	private static String SOFT_ID_PATH;

	@BeforeAll
	public static void copyWB(
		@Autowired UTConfig config, 
		@Autowired TempFile tf) {
		
		SOFT_ID_PATH = config.getSoftwareIDFullPath();
		
		tempFile = tf;				
		tempFile.setPath(SOFT_ID_PATH);
		tempFile.deleteTempFile();
		tempFile.createFile();
		
		try (var fileIn =	new FileInputStream(new File(SOFT_ID_PATH))){
			wb = new XSSFWorkbook(fileIn);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	@AfterAll
	public static void restoreWB() throws IOException {
		wb.close();
		tempFile.restoreOriginal();				
	}

	@Test
	void getInstance() {
		assertNotNull(idServ);
		assertNotNull(config);
	}

	@Test
	void getNextRowNum_inVNF() {
		var sht = wb.getSheet("Vendor Not Found");
		var nextRow = rowNumber.getNextRowNumIn(sht);
		
		assertEquals(2, nextRow);
	}
	
	@Test
	void getNextRowNum_inMS() {
		var sht = wb.getSheet("Microsoft");
		var nextRow = rowNumber.getNextRowNumIn(sht);
		
		assertEquals(1, nextRow);
	}
	
	/*
	 * See note in SoftwareIdService
	 */
//	@Test
//	void getNextId_forMS() {
//		var sht = wb.getSheet("Microsoft");
//		
//		assertEquals("MS_1", idServ.getId(sht));
//	}		
//	@Test
//	void getNextId_forVNF() {
//		var sht = wb.getSheet("Vendor Not Found");		
//		
//		assertEquals("VNF_2", idServ.getId(sht));
//	}
	
	@Test
	void getNextId_forNonExistantSheet() {
		var sht = wb.getSheet("Not A Sheet");		
		
		assertEquals("NO_ID", idServ.getId(sht));
	}
	
}
