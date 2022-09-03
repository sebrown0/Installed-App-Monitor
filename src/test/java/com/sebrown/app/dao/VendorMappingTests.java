/**
 * 
 */
package com.sebrown.app.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class VendorMappingTests {

	@Autowired
	VendorWsMapping venMapping;
	
	@Test
	void getInstance() {
		assertNotNull(venMapping);
	}

}
