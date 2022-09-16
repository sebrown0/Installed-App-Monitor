/**
 * 
 */
package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class VendorNameSanitiserTests {

	@Autowired
	private VendorChecker checker;
	
	@Autowired
	private VendorNameSanitiser creator;
	
	@Test
	void getInstances() {
		assertNotNull(checker);
		assertNotNull(creator);
	}
	
	@Test
	void limitToMaxSize() {
		String name = creator.getName("0123456789 0123456789 0123456789");
		assertEquals("0123456789 0123456789", name);
	}

	@Test
	void excludeAfterPeriod_forSecondWord() {
		String name = creator.getName("0123456789 0.123456789 0123456789");
		assertEquals("0123456789", name);
	}
	
	@Test
	void excludeAfterPeriod_forFirstWord() {
		String name = creator.getName("0123.456789 0123456789 0123456789");
		assertEquals("0123", name);
	}
	
	@Test
	void excludeWordsAfterPunctuation_butKeepAllB4() {
		String name = creator.getName("My Company, Inc");
		assertEquals("My Company", name);
	}
	
	@Test
	void includeWordsAfterPunctuation_inFirstWord() {
		String name = creator.getName(".My Company, Inc");
		assertEquals("My Company", name);
	}
	
	@Test
	void excludeWordsWithInvalid_afterFirstWord() {
		String name = creator.getName("My CompanyInc");
		assertEquals("My", name);
	}
	
}
