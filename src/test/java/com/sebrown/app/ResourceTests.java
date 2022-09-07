/**
 * 
 */
package com.sebrown.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.sebrown.app.service.ResourceService;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@Profile(value = {"TEST"})
class ResourceTests {

	@Autowired
	ResourceService resServ;
	
	@Test
	void getInstance() {
		assertNotNull(resServ);
	}
	
	@Test
	void getPath() {
		assertEquals("src/test/resources", resServ.getPath());
	}

}
