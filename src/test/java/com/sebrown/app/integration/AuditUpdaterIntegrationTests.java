package com.sebrown.app.integration;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.IntegrationTest;
import com.sebrown.app.config.IntegrationTestProps;
import com.sebrown.app.config.VendorConfig;
import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.updater.AuditUpdater;
import com.sebrown.app.utils.TempFile;

@IntegrationTest
@TestMethodOrder(OrderAnnotation.class)
class AuditUpdaterIntegrationTests {
		
	private static String VEN_NAME_PATH;	
	private static TempFile tempFile;
	private static FileInputStream fisAct;
	private static FileInputStream fisExp;
	private static XSSFWorkbook wbAct;
	private static XSSFWorkbook wbExp;
	
	@BeforeAll
	public static void copyVendorNames(
		@Autowired VendorConfig venCnfg, 
		@Autowired IntegrationTestProps props, 
		@Autowired TempFile tf,
		@Autowired AuditUpdater updater) throws Exception {

		copyVendorNamesFile(venCnfg, props, tf);
		auditTestWorkbooks(updater, props);
		openFileStreams(props);
		openWorkbooks();
	}
	
	private static void copyVendorNamesFile(
		VendorConfig venCnfg, IntegrationTestProps props, TempFile tf) {
		
		tempFile = tf;		
		VEN_NAME_PATH = props.getResourcePath() + "/" + venCnfg.getVendorFileName();
		tempFile.setPath(VEN_NAME_PATH);
		tempFile.createFile();				
	}
	
	//Create the Installed Software WB from the 2 input WBs.
	private static void auditTestWorkbooks(AuditUpdater updater, IntegrationTestProps props) {
		AuditOutFileGetter fileGetter = 
				new AuditOutFileGetter(
						props,"Vendor Not Found");
		
		fileGetter.getFile();
				
		//Update audit out.
		updater.updateWorkbook(props);		
	}
	
	private static void openFileStreams(
		IntegrationTestProps props) throws FileNotFoundException {
		
		fisAct =	
				new FileInputStream(
						new File(props.getAuditOutFullPath()));
			
		fisExp =	
				new FileInputStream(
						new File(props.getExpectedResultsFullPath()));			
	}
	
	private static void openWorkbooks() throws IOException {
		wbAct = new XSSFWorkbook(fisAct);
		wbExp = new XSSFWorkbook(fisExp);
	}
				
	@AfterAll
	public static void restoreVendorNames() throws IOException {
		wbAct.close();
		wbExp.close();
		fisAct.close();
		fisExp.close();		
		tempFile.restoreOriginal();
	}
		
	@Test
	void allCellsInAllSheets() {
		int expNumShts = wbExp.getNumberOfSheets() - 1;
				
		for (var idx = 0; idx <= expNumShts; idx++) {			
			var shtExp = wbExp.getSheetAt(idx);
			var shtAct = wbAct.getSheet(shtExp.getSheetName());
			
			shtExp.iterator().forEachRemaining(rwExp -> {
				var rwIdx = rwExp.getRowNum();
				var rwAct = shtAct.getRow(rwIdx);
				
				rwExp.forEach(
						cellExp -> {
							var expVal = cellExp.getStringCellValue();
							var actVal = rwAct.getCell(cellExp.getColumnIndex()).getStringCellValue();
							
							assertEquals(
								expVal, actVal, String.format("Sheet[%s], Row[%s], Col[%s]", 
									shtExp.getSheetName(), rwIdx, cellExp.getColumnIndex()));
						}					
					); 
				}
			);						
		}
	}
	
	@Test
	void sheetsAlign() throws IOException {				
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
		
		//Assert any error
		if(err) {
			fail("Workbooks do not align" + diff.toString());
		}
			
	}
	
	@Test 
	void nameIsNotNull() {
		assertEquals(
				getValFromExp("Microsoft", 2, 1), 
				getValFromAct("Microsoft", 2, 1));			
	}
	
	@Test
	void assetIDsAreCorrect() {
		assertEquals(
				getValFromExp("Citrix", 4, 0), 
				getValFromAct("Citrix", 4, 0));
	}
	
	//**************************  Helpers Below **************************//	
	private String getValFromExp(String fromSht, int rowNum, int cellNum) {
		return getValFromLoc(wbExp, fromSht, rowNum, cellNum);
	}
	
	private String getValFromAct(String fromSht, int rowNum, int cellNum) {
		return getValFromLoc(wbAct, fromSht, rowNum, cellNum);
	}
	
	private String getValFromLoc(XSSFWorkbook wb, String fromSht, int rowNum, int cellNum) {
		String val = null;		
		XSSFSheet sht = wb.getSheet(fromSht);
		val = getStrVal(sht, rowNum, cellNum);			
		
		return val;
	}
	
	private String getStrVal(XSSFSheet fromSht, int rowNum, int cellNum) {
		return fromSht
				.getRow(rowNum).getCell(cellNum)
				.getStringCellValue();
	}	
		
}
