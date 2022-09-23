/**
 * 
 */
package com.sebrown.app.unit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.model.Vendor;
import com.sebrown.app.model.VendorAcronyms;
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
	
	@Test
	void getAcronymsFromVendorFile() {
		VendorAcronyms venAcronyms = vendorList;
		var acronyms = venAcronyms.getAcronyms();
		
		assertEquals("ADOBE", acronyms.get(0));	
		assertEquals("MS", acronyms.get(1));			
	}

	@Test
	void addSomeNames() {
		VendorNames venNames = vendorList;
		venNames.addNames(List.of("Name 1","Name 2"));
		var names = venNames.getNames();

		//Reset
		vendorList.setList();
		
		assertEquals("Name 1", names.get(2));
		assertEquals("Name 2", names.get(3));
	}
	
	@Test
	void addAcronymToExistingName() {
		//Add some names
		VendorNames venNames = vendorList;
		venNames.addNames(List.of("Name 1","Name 2"));
		
		//Give name 1 an acronym
		VendorAcronyms venAcronyms = vendorList;
		venAcronyms.addAcronym("Name 1", "NAME1");
		
		String res = venAcronyms.getAcronyms().get(2);

		//Reset
		vendorList.setList();
		
		assertEquals("NAME1", res);		
	}

	@Test
	void getCurrentVendors() {
		//Add some names
		VendorNames venNames = vendorList;
		venNames.addNames(List.of("Name 1","Name 2"));
		
		//Give name 1 an acronym
		VendorAcronyms venAcronyms = vendorList;
		venAcronyms.addAcronym("Name 1", "NAME1");
		
		Vendor vendor = vendorList;
		var vendors = vendor.getCurrentVendorMap();

		//Reset
		vendorList.setList();
		
		assertEquals("NAME1", vendors.get("Name 1"));
		assertTrue(null == vendors.get("Name 2"));
	}
	
	@Test
	void getCurrentVendorsAsList() {
		//Add some names
		VendorNames venNames = vendorList;
		venNames.addNames(List.of("Name 1","Name 2"));
		
		//Give name 1 an acronym
		VendorAcronyms venAcronyms = vendorList;
		venAcronyms.addAcronym("Name 1", "NAME1");
		
		Vendor vendor = vendorList;
		var vendors = vendor.getCurrentAsList();

		//Reset
		vendorList.setList();
		
		assertEquals("Microsoft:MS", vendors.get(1));
		assertEquals("Adobe:ADOBE", vendors.get(0));
		assertEquals("Name 1:NAME1", vendors.get(2));
		assertEquals("Name 2:null", vendors.get(3));
	}
}
