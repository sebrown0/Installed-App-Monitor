/**
 * 
 */
package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.DefaultVendor;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class SimpleVendorNameTests {

	@Autowired
	VendorNameRules vendorNameRules;
	
	@Autowired
	DefaultVendor defaultVendor;
	
	@Test
	void getInstance() {
		assertNotNull(vendorNameRules);
		assertNotNull(defaultVendor);
	}
	
	@Test
	void excludeWordsAfterPunctuation_butKeepAllB4() {
		String name = vendorNameRules.applyRulesTo("My Company, Inc");
		assertEquals("My Company", name);
	}
	
	@Test
	void givenNull_expectDefault() {
		String name = vendorNameRules.applyRulesTo(null);
		assertEquals(defaultVendor.getName(), name);
	}

	@Test
	void sanitisedNameIsTooShort() {
		String name = vendorNameRules.applyRulesTo("My C, Inc");
		assertEquals("My C Inc", name);
	}
	
	@Test
	void sanitisedNameIsTooLong() {
		String name = vendorNameRules.applyRulesTo("My Company With A Very Long Name, Inc");
		assertEquals("My Company With A Very", name);
	}
}
