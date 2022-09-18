/**
 * 
 */
package com.sebrown.app.unit.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.UnitTestProps;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class UTPropsTests {

	@Autowired
	UnitTestProps utProps;
		
	@Test
	void getInstance() {
		assertNotNull(utProps);
	}
	
	@Test
	void getUTResourcePath() {		
		String res = utProps.getResourcePath();		
		assertEquals("src/test/resources", res);
	}
	
	@Test
	void getUTOutputWbPath() {		
		String res = utProps.getAuditOutFullPath();		
		assertEquals("src/test/resources/Installed Software.xlsx", res);
	}

}
