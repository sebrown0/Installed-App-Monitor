/**
 * 
 */
package com.sebrown.app.unit.vendor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.dao.FilePath;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class VendorFilePathTests {

	@Autowired @Qualifier("vendorNamePath")
	private FilePath name;
	
	@Autowired @Qualifier("vendorAccronymPath")
	private FilePath accronym;
	
	@Test
	void getInstance() {
		assertNotNull(name);
		assertNotNull(accronym);
	}

	@Test
	void vendorPath() {
		assertEquals("src/test/resources", name.getPath());
	}

	@Test
	void vendorName() {
		assertEquals("VendorNames.txt", name.getFileName());
	}
	
	@Test
	void vendorPathAndName() {
		assertEquals("src/test/resources/VendorNames.txt", name.getPathAndFileName());
	}
	
	@Test
	void accronymPath() {
		assertEquals("src/test/resources", accronym.getPath());
	}

	@Test
	void accronymName() {
		assertEquals("VendorAccronyms.txt", accronym.getFileName());
	}
	
	@Test
	void accronymPathAndName() {
		assertEquals("src/test/resources/VendorAccronyms.txt", accronym.getPathAndFileName());
	}
	
}
