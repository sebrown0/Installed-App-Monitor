/**
 * 
 */
package com.sebrown.app.unit.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.config.VendorAccronym;
import com.sebrown.app.dao.VendorRepo;
import com.sebrown.app.service.VendorAccronymCreator;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class AccronymCreatorTests {

	@Autowired
	private VendorAccronymCreator creator;
	
//	@Autowired @Qualifier("vendorAccronymFile")
//	private VendorRepo repo;
	
	@Autowired
	private VendorAccronym venAcc;
	
	@Test
	void getInstance() {
		assertNotNull(creator);
		assertNotNull(venAcc);
	}
	
	@Test
	void minAndMaxAccLens() {
		assertTrue(venAcc.getMinAcnymLen() >= 2);
		assertTrue(venAcc.getMaxAcnymLen() <= 5);
	}

	@Test
	void testRuleOne_firstCharOfWords_lessThanMax_1_equalsVN() {
		String acc = creator.getAccronym("Vendor Name");
		assertEquals("VN", acc);
	}
	
	@Test
	void testRuleOne_firstCharOfWords_lessThanMax_2_equalsVN() {
		String acc = creator.getAccronym("Vendor Name Is Quite A Long Name");
		assertEquals("VNIQA", acc);
	}
	
	@Test
	void testRuleTwo_charsInFirstWord() {
		String acc = creator.getAccronym("Vendor");
		assertEquals("VENDO", acc);
	}
	
	@Test
	void testDefault_randomAccOfMaxLen() {
		String acc = creator.getAccronym("V");
		assertTrue(acc.length() > 1);
	}
		
}
