package com.sebrown.app.unit.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.AppProperties;
import com.sebrown.app.config.DefaultVendor;
import com.sebrown.app.config.MappingProperties;
import com.sebrown.app.config.ResourceConfig;
import com.sebrown.app.config.VendorConfig;
import com.sebrown.app.config.WorkbookProperties;

@SpringBootTest
@ActiveProfiles("test")
class AppConfigTests {

	@Autowired
	private ResourceConfig resource;
	
	@Autowired
	private VendorConfig vendor;
	
	@Autowired
	private MappingProperties mapProps;
		
	@Autowired
	private DefaultVendor defaultVendor;
	
	@Autowired
	private AppProperties appProps;
	
	@Test
	void getInstance() {
		assertNotNull(mapProps);
		assertNotNull(defaultVendor);
		assertNotNull(appProps);
		assertNotNull(resource);
		assertNotNull(vendor);
	}
	
	@Test
	void resourcePath() {
		assertEquals("src/test/resources", resource.getPath());
	}
	
	@Test
	void vendorFile() {
		assertEquals("VendorNames.txt", vendor.getVendorFileName());
	}
	
	@Test
	void maxSheetNameLen() {
		assertTrue(appProps.getMaxSheetNameLen() == 22);
	}
	@Test
	void defaultVendorName() {
		assertEquals("Vendor Not Found", defaultVendor.getName());
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
		assertEquals("3", 
				wbProps.getWorkbooks().get("auditIn")
				.getSheets().get("installedApps").getColumnMappings().get("name"));
	}
	
	@Test
	void workbookSheetAuditOut() {
		WorkbookProperties wbProps = (WorkbookProperties) mapProps;
		assertEquals("3", 
				wbProps.getWorkbooks().get("auditOut")
				.getSheets().get("vendorNotFound").getColumnMappings().get("version"));
	}
		
	
}
