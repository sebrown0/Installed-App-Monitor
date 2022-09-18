/**
 * 
 */
package com.sebrown.app.unit.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.helpers.ProceedingJoinPointImpl;
import com.sebrown.app.log.LogMessageFromJoinPoint;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class MethodExecutingMessageTests {

	@Autowired
	LogMessageFromJoinPoint logMsg;
	
	@Autowired
	ProceedingJoinPointImpl pjpImpl;
	
	@Test
	void getInstances() {
		assertNotNull(logMsg);
		assertNotNull(pjpImpl);
	}

	@Test
	void getMessageFromJoinPoint() {
		String msg = logMsg.getMsgFromJoinPoint(pjpImpl);
		
		assertEquals(
				"[com.sebrown.app.methodName] " +
				"with args[Arg1, 22] - Executing now.",
				msg);
	}
		
}
