/**
 * 
 */
package com.sebrown.app.unit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.model.ExistingAcronymChecker;
import com.sebrown.app.model.Vendor;
import com.sebrown.app.model.VendorNames;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class ExistingAccronymTests {

	@Autowired 
	private ExistingAcronymChecker acrChecker;
	
	@Autowired 
	private Vendor vendor;
		
	@Test
	void getInstance() {
		assertNotNull(acrChecker);	
	}
	
	@Test
	void getAccronymForMicrosoft_shouldReturn_MS() {
		assertEquals(true, acrChecker.isExisting("MS"));
	}
	
	@Test
	void getAccronymForAdobe_shouldReturn_ADOBE() {
		assertEquals("ADOBE", acrChecker.getAcronymForName("Adobe").get());
	}
	
	@Test
	void startSequence_forTwoCharAcr() {
		String acr = acrChecker.checkAcronym("MS");
		
		assertEquals("MS_1", acr);
	}
	
	@Test
	void startSequence_forThreeCharAcr() {
		vendor.rollback().addVendor("MST:MST");
		String acr = acrChecker.checkAcronym("MST");
		
		assertEquals("MST_1", acr);
	}
	
	@Test
	void startSequence_forFourCharAcr() {
		vendor.rollback();
		vendor.addVendor("MST:MST_1");
		vendor.addVendor("MSTP:MSTP");
		String acr = acrChecker.checkAcronym("MSTP");
		
		assertEquals("MST_2", acr);
	}
	@Test
	void incrementSequence() {
		vendor.addVendor("Micro Services:MS_1");
		String acr = acrChecker.checkAcronym("MS_1");
		
		assertEquals("MS_2", acr);
	}

}
