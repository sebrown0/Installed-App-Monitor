/**
 * 
 */
package com.sebrown.app.unit.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.dao.VendorRepo;
import com.sebrown.app.model.ExistingAcronymForVendor;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class ExistingVendorAccronymTests {

	@Autowired
	private ExistingAcronymForVendor eva;
	
	@Autowired @Qualifier("vendorAccronymFile")
	private VendorRepo repo;
	
	@Test
	void getInstance() {
		assertNotNull(eva);
		assertNotNull(repo);
	}
	
	@Test
	void getAccronymForMicrosoft_shouldReturn_MS() {
		assertEquals("MS", eva.getExisting(repo.getList(), "Microsoft").get());
	}

}
