package com.sebrown.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.sebrown.app.config.MappingProperties;
import com.sebrown.app.config.WorkbookProperties;
import com.sebrown.app.service.ResourceService;
import com.sebrown.app.service.SheetService;

@SpringBootTest
@Profile("TEST")
class AppConfigTests {

	@Autowired
	private MappingProperties mapProps;
	
//	@Autowired
//	private SheetService shtServ;
	
//	@Autowired
//	ResourceService resServ;
	
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
		
	
}
