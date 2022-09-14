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
		
	FileInputStream fis;
	
	@Test @Order(1)
	void updateAuditOutWithDataFromAuditWBs() throws IOException {		
		AuditOutFileGetter fileGetter = 
				new AuditOutFileGetter(
						props,"Vendor Not Found");
		
		fileGetter.getFile();
				
		//Update audit out.
		updater.updateWorkbook(props);		
	}
	
	@Test @Order(2)
	void test() {
		String val = null;
		
		try(FileInputStream fis =	
				new FileInputStream(new File(props.getAuditOutFullPath()));) {
			
			try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
				XSSFSheet sht = wb.getSheet("Microsoft");
				val = getStrVal(sht, 1, 1);
				wb.close();
			}
			
			fis.close();
			
		} catch (IOException e) {}
						
		assertEquals("Microsoft Office Access database engine 2007 (English)", val);		
	}
	
	//Helpers
	private String getStrVal(XSSFSheet fromSht, int rowNum, int cellNum) {
		return fromSht
				.getRow(rowNum).getCell(cellNum)
				.getStringCellValue();
	}
	
//	private XSSFWorkbook getWB() {		
//		try (
//			 fis =	
//				new FileInputStream(new File(props.getAuditOutFullPath()));){
//			
//			return new XSSFWorkbook(fis);			
//		} catch (Exception e) {	}
//		
//		return null;					
//	}
	
}
