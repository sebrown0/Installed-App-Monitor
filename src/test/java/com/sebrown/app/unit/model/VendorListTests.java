/**
 * 
 */
package com.sebrown.app.unit.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.model.VendorList;
import com.sebrown.app.model.VendorNames;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class VendorListTests {

	@Autowired
	private VendorList vendorList;
	
	@Test
	void getInstance() {
		assertNotNull(vendorList);
	}
	
	@Test
	void getNamesFromVendorFile() {
		VendorNames venNames = vendorList;
		var names = venNames.getNames();
		
		assertEquals("Adobe", names.get(0));
		assertEquals("Microsoft", names.get(1));
	}

}
