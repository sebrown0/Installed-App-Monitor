package com.sebrown.app.unit.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.service.SoftwareIdService;

@SpringBootTest
class SoftwareIdServiceTests {

	@Value("${auditWorkbookPath}")
	private String auditWorkbookPath;
	
	@Autowired
	SoftwareIdService idServ;
	
	@Test
	void getInstance() {
		assertNotNull(idServ);
	}

	@Test
	void getIdForVendorNotFoundSheet() throws IOException {
		XSSFSheet sht;
		XSSFWorkbook wb = null;
		String id = null;
		
		try (var fileIn =	new FileInputStream(new File(auditWorkbookPath))){
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
