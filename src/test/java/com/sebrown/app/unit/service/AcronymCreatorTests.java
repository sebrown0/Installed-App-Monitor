/**
 * 
 */
package com.sebrown.app.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.config.VendorAcronym;
import com.sebrown.app.service.VendorAcronymCreator;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class AcronymCreatorTests {

	@Autowired
	private VendorAcronymCreator creator;
		
	@Autowired
	private VendorAcronym venAcr;
	
	@Test
	void getInstance() {
		assertNotNull(creator);
		assertNotNull(venAcr);
	}
	
	@Test
	void minAndMaxAccLens() {
		assertTrue(venAcr.getMinAcronymLen() >= 2);
		assertTrue(venAcr.getMaxAcronymLen() <= 5);
	}

	@Test
	void testRuleOne_firstCharOfWords_lessThanMax_1_equalsVN() {
		String acc = creator.getAcronym("Vendor Name");
		assertEquals("VN", acc);
	}
	
	@Test
	void testRuleOne_firstCharOfWords_lessThanMax_2_equalsVN() {
		String acc = creator.getAcronym("Vendor Name Is Quite A Long Name");
		assertEquals("VNIQA", acc);
	}
	
	@Test
	void testRuleTwo_charsInFirstWord() {
		String acc = creator.getAcronym("Vendor");
		assertEquals("VENDO", acc);
	}
	
	@Test
	void testDefault_randomAccOfMaxLen() {
		String acc = creator.getAcronym("V");
		assertTrue(acc.length() > 1);
	}
		
}
