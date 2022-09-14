package com.sebrown.app.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.IntegrationTestProps;
import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.updater.AuditUpdater;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AuditUpdaterIntegrationTests {
	
	@Autowired
	private AuditUpdater updater;
	
	@Autowired
	private IntegrationTestProps props;
		
//	FileInputStream fis;
	
	@Test @Order(1)
	void updateAuditOutWithDataFromAuditWBs() throws IOException {		
		AuditOutFileGetter fileGetter = 
				new AuditOutFileGetter(
						props,"Vendor Not Found");
		
		fileGetter.getFile();
				
		//Update audit out.
		updater.updateWorkbook(props);		
	}
	
	@Test 
	void nameIsNotNull() {						
		assertEquals(
				"Microsoft Visual C++ 2019 X86 Minimum Runtime - 14.24.28127", 
				getValFromLoc("Microsoft", 2, 1));		
	}
	
	
	//*****************************  Helpers  *****************************
	private String getValFromLoc(String fromSht, int rowNum, int cellNum) {
		String val = null;
		
		try(FileInputStream fis =	
				new FileInputStream(
						new File(props.getAuditOutFullPath()));) {
			
			try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
				XSSFSheet sht = wb.getSheet(fromSht);
				val = getStrVal(sht, rowNum, cellNum);
				wb.close();
			}			
			fis.close();
			
		} catch (IOException e) {}
		
		return val;
	}
	
	private String getStrVal(XSSFSheet fromSht, int rowNum, int cellNum) {
		return fromSht
				.getRow(rowNum).getCell(cellNum)
				.getStringCellValue();
	}
	
	
}
