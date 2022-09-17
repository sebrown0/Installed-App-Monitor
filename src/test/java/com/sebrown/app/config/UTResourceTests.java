/**
 * 
 */
package com.sebrown.app.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class UTResourceTests {

	@Autowired
	private ResourceConfig resource;
	
	@Test
	void test() {
		assertNotNull(resource);
	}

	@Test
	void resourcePath() {
		assertEquals("src/test/resources", resource.getPath());
	}
}
