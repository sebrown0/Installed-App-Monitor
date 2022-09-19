/**
 * 
 */
package com.sebrown.app.unit.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.log.MethodArgs;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class MethodArgsTests {

	@Autowired
	private MethodArgs methodArgs;
	
	@Test
	void getInstance() {
		assertNotNull(methodArgs);
	}

	@Test
	void getString_fromNoArgs() {
		String res = methodArgs.getArgsAsString(null);
		
		assertEquals("", res);
	}
	
	@Test
	void getString_fromArgs() {
		String res = methodArgs.getArgsAsString(
				new Object[] { "Arg1", 22} );
		
		assertEquals("Arg1, 22", res);
	}
}
