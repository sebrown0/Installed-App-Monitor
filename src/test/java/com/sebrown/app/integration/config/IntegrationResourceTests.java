/**
 * 
 */
package com.sebrown.app.integration.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.ResourceConfig;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("integration")
class IntegrationResourceTests {

	@Autowired
	private ResourceConfig resource;
	
	@Test
	void test() {
		assertNotNull(resource);
	}

	@Test
	void resourcePath() {
		assertEquals("src/test/resources/integration", resource.getPath());
	}
}
