package com.sebrown.app.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class InstalledAppRowDataTests {

	@Test
	void allFields() {
		InstalledAppRowData rowIn = 
				new InstalledAppRowData.Builder()
					.setName("Row 1")
					.setVendor("MS")
					.setVersion("1.0.0")
					.setDescription("desc")
					.setIdentifyingNumber("{123}")
					.setInstallDate(20170615)
					.setProductID("prod id - 1")
					.setRegCompany("comp")
					.setRegOwner("owner")
					.getInstance();
		
		assertEquals("Row 1", rowIn.getName());
		assertEquals("MS", rowIn.getVendor());
		assertEquals("1.0.0", rowIn.getVersion());		
		assertEquals("desc", rowIn.getDescription());
		assertEquals("{123}", rowIn.getIdentifyingNumber());
		assertEquals("15/06/2017", rowIn.getInstallDate());
		assertEquals("prod id - 1", rowIn.getProductID());
		assertEquals("comp", rowIn.getRegCompany());
		assertEquals("owner", rowIn.getRegOwner());		
	}
	
	@Test
	void badDate_returnsDefaultDate() {
		InstalledAppRowData rowIn = 
				new InstalledAppRowData.Builder()
					.setName("Row 1")					
					.setInstallDate(0)					
					.getInstance();
		
		assertEquals("Row 1", rowIn.getName());		
		assertEquals("01/01/1900", rowIn.getInstallDate());				
	}
	
}
