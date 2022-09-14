/**
 * 
 */
package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class VendorCheckerTests {

	@Autowired
	VendorChecker vendorChecker;
	
	@Test
	void getInstance() {
		assertNotNull(vendorChecker);
	}
	
	@Test
	void getMicrosoft_fromList() {
		var vendors = Arrays.asList("Microsoft", "Adobe");
		var venName = "Microsoft Corporation";
		
		assertEquals(
				"Microsoft", 
				vendorChecker.getVendorNameFromList(venName, vendors).get());
	}
	
	@Test
	void getAdobe_fromList() {
		var vendors = Arrays.asList("Microsoft", "Adobe");
		var venName = "This string should return adobe";
		
		assertEquals(
				"Adobe", 
				vendorChecker.getVendorNameFromList(venName, vendors).get());
	}
	
	@Test
	void getNothing_fromList() {
		var vendors = Arrays.asList("Microsoft", "Adobe");
		var venName = "A Corporation";
		
		assertFalse(vendorChecker
				.getVendorNameFromList(venName, vendors)
				.isPresent());		
	}
	
	@Test
	void getNothing_fromNullName() {
		var vendors = Arrays.asList("Microsoft", "Adobe");
		
		assertFalse(vendorChecker
				.getVendorNameFromList(null, vendors)
				.isPresent());		
	}
	
	@Test
	void getNothing_fromNullList() {
		var venName = "A Corporation";
		
		assertFalse(vendorChecker
				.getVendorNameFromList(venName, null)
				.isPresent());		
	}

}
