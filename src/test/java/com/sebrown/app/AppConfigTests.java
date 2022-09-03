package com.sebrown.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.sebrown.app.config.MappingProperties;
import com.sebrown.app.config.WorkbookProperties;

@SpringBootTest
class AppConfigTests {

	@Autowired
	MappingProperties mapProps;
	
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

}
