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
import com.sebrown.app.service.VendorAcronymService;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class VendorAcronymServiceTests {

	@Autowired
	private VendorAcronymService accServ;
	
	@Test
	void getInstance() {
		assertNotNull(accServ);
	}

	@Test
	void testRuleOne_firstCharOfWords_lessThanMax_1_equalsVN() {
		String acc = accServ.getAcronym("Vendor Name").get();
		assertEquals("VN", acc);
	}
	
	@Test
	void testRuleOne_firstCharOfWords_lessThanMax_2_equalsVN() {
		String acc = accServ.getAcronym("Vendor Name Is Quite A Long Name").get();
		assertEquals("VNIQA", acc);
	}
	
	@Test
	void testRuleTwo_charsInFirstWord() {
		String acc = accServ.getAcronym("Vendor").get();
		assertEquals("VENDO", acc);
	}
	
	@Test
	void testDefault_randomAccOfMaxLen() {
		String acc = accServ.getAcronym("V").get();
		assertTrue(acc.length() > 1);
	}
}
