/**
 * 
 */
package com.sebrown.app.error;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.error.ErrorToHandle.ErrorSeverity;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class ErrorToHandleTests {

	@Autowired
	ErrorToHandle err;
	
	@Test
	void testToString_withArgs() {
		err
			.setFromClass("AClass")
			.setFromMethod("AMethod")
			.setMsg("some messgage")
			.setSeverity(ErrorSeverity.HIGH)
			.setArgs(Arrays.asList("Arg1","Arg2"));
		
		assertEquals(
				"[AClass.AMethod], with args[Arg1, Arg2], caused and error of severity [HIGH]," +
				" with msg[some messgage]", err.toString());		
	}
	
	@Test
	void testToString_withoutArgs() {
		err
		.setFromClass("AClass")
		.setFromMethod("AMethod")
		.setMsg("some messgage")
		.setSeverity(ErrorSeverity.HIGH);
		
		assertEquals(
				"[AClass.AMethod], with args[none], caused and error of severity [HIGH]," +
				" with msg[some messgage]", err.toString());		
	}
	
	@Test
	void testToString_withoutArgsClassAndMethod() {				
		assertEquals(
				"[unknown.unknown], with args[none], caused and error of severity [unknown]," +
				" with msg[unknown]", err.toString());		
	}

}
