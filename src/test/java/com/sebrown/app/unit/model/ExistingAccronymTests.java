/**
 * 
 */
package com.sebrown.app.unit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.model.ExistingAcronymChecker;
import com.sebrown.app.model.Vendor;

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
		vendor.rollback().addVendor("Micro Services:MS_1");
		String acr = acrChecker.checkAcronym("MS_1");
		
		assertEquals("MS_2", acr);
	}
	
	@Test
	void incrementInvalidSequence() {
		vendor.rollback().addVendor("Micro Services:MS_1");
		String acr = acrChecker.checkAcronym("MS_*");
		
		assertEquals(null, acr);
	}
	
	@Test
	void incrementSequence_toDoubleDigits() {
		vendor.rollback();
		vendor.addVendor("Micro Services:MS_1");
		vendor.addVendor("Micro Service:MS_2");
		vendor.addVendor("Micro Servic:MS_3");
		vendor.addVendor("Micro Servi:MS_4");
		vendor.addVendor("Micro Serv:MS_5");
		vendor.addVendor("Micro Ser:MS_6");
		vendor.addVendor("Micro Se:MS_7");
		vendor.addVendor("Micro S:MS_8");
		vendor.addVendor("Micro :MS_9");
		
		String acr = acrChecker.checkAcronym("MS_1");
		
		assertEquals("MS_a", acr);
	}
	
	@Test
	void incrementSequence_toDoubleDigits_withChar() {
		vendor.rollback();
		vendor.addVendor("Micro Services:MS_1");
		vendor.addVendor("Micro Service:MS_2");
		vendor.addVendor("Micro Servic:MS_3");
		vendor.addVendor("Micro Servi:MS_4");
		vendor.addVendor("Micro Serv:MS_5");
		vendor.addVendor("Micro Ser:MS_6");
		vendor.addVendor("Micro Se:MS_7");
		vendor.addVendor("Micro S:MS_8");
		vendor.addVendor("Micro :MS_9");
		vendor.addVendor("Micro:MS_a");
		
		String acr = acrChecker.checkAcronym("MS_9");
		
		assertEquals("MS_b", acr);
	}

}
