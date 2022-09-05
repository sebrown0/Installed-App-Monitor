package com.sebrown.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.MappingProperties;
import com.sebrown.app.config.ResourceProperties;
import com.sebrown.app.config.WorkbookProperties;
import com.sebrown.app.service.SheetService;

@SpringBootTest
class AppConfigTests {

	@Autowired
	MappingProperties mapProps;
	
	@Autowired
	SheetService shtServ;
	
	@Test
	void getInstance() {
		assertNotNull(mapProps);
	}
	
	@Test
	void vendorShouldNotBeNull() {
		assertNotNull(mapProps.getMappingProps().get("vendor"));
	}
	
	@Test
	void workbook() {
		WorkbookProperties wbProps = (WorkbookProperties) mapProps;
		assertEquals("Audit In", wbProps.getWorkbooks().get("auditIn").getName());
	}
	
	@Test
	void workbookSheetAuditIn() {
		WorkbookProperties wbProps = (WorkbookProperties) mapProps;
		assertEquals("4", 
				wbProps.getWorkbooks().get("auditIn")
				.getSheets().get("installedApps").getColumnMappings().get("name"));
	}
	
	@Test
	void workbookSheetAuditOut() {
		WorkbookProperties wbProps = (WorkbookProperties) mapProps;
		assertEquals("4", 
				wbProps.getWorkbooks().get("auditOut")
				.getSheets().get("vendorNotFound").getColumnMappings().get("version"));
	}
	
	@Test
	void workbookSheetAuditOut_throwIllegalArgEx() {
		WorkbookProperties wbProps = (WorkbookProperties) mapProps;
		
//		assertThrows(IllegalArgumentException.class, () -> {
//			wbProps.getSheet("auditIn", "null");	
//		});		
	}
	
	@Test
	void getResourcePath() {
		ResourceProperties resProps = (ResourceProperties) mapProps;
		assertEquals(".", resProps.getProps().getPath());
	}
	

}
