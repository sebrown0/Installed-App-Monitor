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

import com.sebrown.app.config.IntegrationTestProps;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("integration")
class IntegrationPropsTests {
	
	@Autowired
	IntegrationTestProps itProps;
	
	@Test
	void getInstance() {
		assertNotNull(itProps);
	}

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
