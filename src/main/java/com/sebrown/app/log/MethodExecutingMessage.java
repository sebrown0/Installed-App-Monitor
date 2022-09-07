/**
 * 
 */
package com.sebrown.app.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 */
@Component
public class MethodExecutingMessage implements LogMessageFromJoinPoint {

	private final MethodArgs methodArgs;
	
	public MethodExecutingMessage(MethodArgs methodArgs) {
		super();
		this.methodArgs = methodArgs;
	}

	@Override
	public String getMsgFromJoinPoint(ProceedingJoinPoint pjp) {
		var sig = pjp.getSignature();
		return String.format("[%s.%s] with args[%s] - Executing now.", 
				sig.getDeclaringTypeName(),
				sig.getName(),
				methodArgs.getArgsAsString(pjp.getArgs()));
	}
	
	
}
