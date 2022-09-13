/**
 * 
 */
package com.sebrown.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.IntegrationTestProps;
import com.sebrown.app.config.UnitTestProps;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class TestPropsTests {

	@Autowired
	UnitTestProps utProps;
	
	@Autowired
	IntegrationTestProps itProps;
	
	@Test
	void getInstance() {
		assertNotNull(utProps);
		assertNotNull(itProps);
	}
	
	//Unit Tests
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
	
	//Integration Tests
	@Test
	void getIntegrationResourcePath() {		
		String res = itProps.getResourcePath();		
		assertEquals("src/test/resources/integration", res);
	}
	
	@Test
	void getIntegrationOutputWbPath() {		
		String res = itProps.getAuditOutFullPath();		
		assertEquals("src/test/resources/integration/Installed Software.xlsx", res);
	}

}
