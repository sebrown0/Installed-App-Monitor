package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.dto.InstalledAppRowData;

@SpringBootTest
class AuditRowUpdaterServiceTests {

	@Value("${testPath}")
	private String testPath;
	
	@Value("${testWorkbook}")
	private String testWorkbook;
							
	@Value("${auditWorkbookPath}")
	private String auditWorkbookPath;
	
	@Autowired
	AuditRowUpdaterService updaterServ;
	
	@Test
	void getInstance() {
		assertNotNull(updaterServ);
	}

	@Test
	void getIdForVendorNotFoundSheet() throws IOException {
		XSSFSheet sht;
		XSSFWorkbook wb = null;
		String id = null;
		
		try (var fileIn =	new FileInputStream(new File(auditWorkbookPath))){
			wb = new XSSFWorkbook(fileIn);
			sht = wb.getSheet("Vendor Not Found");
			
			Row row1 = sht.getRow(1);
			String idNum = row1.getCell(2).getStringCellValue();
			String ver = row1.getCell(3).getStringCellValue();
			
			id = updaterServ.updateRow(
					row1, 
					new InstalledAppRowData.Builder()
						.setIdentifyingNumber(idNum)
						.setVersion(ver)
						.getInstance(), 
					Path.of(testPath + testWorkbook));
			
		} catch (IOException e) {	}
		
		wb.close();
		
		assertEquals("some asset id;Test-BOARDROOM-4TH", id);
	}
	
}
