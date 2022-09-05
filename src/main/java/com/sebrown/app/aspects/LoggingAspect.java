/**
 * 
 */
package com.sebrown.app.aspects;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;

/**
 * @author SteveBrown
 *
 */
@Aspect
@Component
public class LoggingAspect {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Around("@annotation(LogWorkbookComponent)")
	public Object log(ProceedingJoinPoint jp) {
		var signature = jp.getSignature();
		Object returnVal = null;
		
		LOG.debug(String.format(
				"%s.%s: Getting WB component", 
				signature.getDeclaringType().getSimpleName(), 
				signature.getName()));
		
		try {
			returnVal = jp.proceed();
		} catch (Throwable e) {
			LOG.error(String.format(
					"%s.%s: Failed getting WB component for [%s]", 
					signature.getDeclaringType().getSimpleName(), 
					signature.getName(),
					e.getMessage()));
		}
		
		return returnVal;
	}
	
}
