package com.sebrown.app.integration;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.IntegrationTestProps;
import com.sebrown.app.config.ResourceConfig;
import com.sebrown.app.config.VendorConfig;
import com.sebrown.app.dao.VendorRepo;
import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.updater.AuditUpdater;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("integration")
class AuditUpdaterIntegrationTests {
	
	@Autowired
	private AuditUpdater updater;
	
	@Autowired
	private IntegrationTestProps props;
	
	@Autowired
	private VendorConfig venCnfg;
	
	@Autowired
	private ResourceConfig resource;
		
	/*
	 * Create the Installed Software WB 
	 * from the 2 input WBs.
	 */
	@Test @Order(1)
	void updateAuditOutWithDataFromAuditWBs_noErrorIsImplicitPass() throws IOException {	
		AuditOutFileGetter fileGetter = 
				new AuditOutFileGetter(
						props,"Vendor Not Found");
		
		fileGetter.getFile();
				
		//Update audit out.
		updater.updateWorkbook(props);		
	}
	
	@Test
	void sheetsAlign() throws IOException {
		
		//Open actual and expected WBs.
		var fisActual =	
				new FileInputStream(
						new File(props.getAuditOutFullPath()));
			
		var wbAct = new XSSFWorkbook(fisActual);
		
		var fisExp =	
				new FileInputStream(
						new File(props.getExpectedResultsFullPath()));
			
		var wbExp = new XSSFWorkbook(fisExp);
		
		//Create list of actual & expected sheet names
		var actListNames = new ArrayList<>();
		var expListNames = new ArrayList<>();
		int actNumShts = wbAct.getNumberOfSheets() - 1;
		
		for (var idx = 0; idx <= actNumShts; idx++) {			
			actListNames.add(wbAct.getSheetAt(idx).getSheetName());			
			try {
				var sht = wbExp.getSheetAt(idx);
				expListNames.add(sht.getSheetName());	
			} catch (IllegalArgumentException e) {	}			
		}
		
		//Remove expected names from actual.
		var diff = new HashSet<>();
		diff.addAll(actListNames);
		diff.removeAll(expListNames);		
		
		//If there's a difference there's an error
		boolean err = (Objects.nonNull(diff) && diff.size() > 0);

		//Clean up.
		cleanUpSheetsAlign(wbExp, fisExp, wbAct, fisActual);
		
		//Assert any error
		if(err) {
			fail("Workbooks do not align" + diff.toString());
		}
			
	}
	
	@Test 
	void nameIsNotNull() {						
		assertEquals(
				"Microsoft Visual C++ 2019 X86 Minimum Runtime - 14.24.28127", 
				getValFromLoc("Microsoft", 2, 1));		
	}
	
	
	//**************************  Helpers Below **************************//
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
	
	
	
	private void cleanUpSheetsAlign(
		XSSFWorkbook wbExp, 
		FileInputStream fisExp,
		XSSFWorkbook wbAct, 
		FileInputStream fisActual) throws IOException{
		
		wbExp.close();			
		fisExp.close();			
		wbAct.close();			
		fisActual.close();
		restoreVenNameFile();
	}
	
	void restoreVenNameFile() throws IOException {
		Path fPath = Paths.get(
				resource.getPath() + "/" + 
				venCnfg.getVendorFileName());
		
		Files.write(
				fPath, 
				Arrays.asList("Microsoft", "Adobe"), 
				TRUNCATE_EXISTING, WRITE);		
	}
	
}
