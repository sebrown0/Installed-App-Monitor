/**
 * 
 */
package com.sebrown.app.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sebrown.app.annotations.LogDefaultDebugMessage;
import com.sebrown.app.annotations.LogInfoMessage;
import com.sebrown.app.log.MethodExecutingMessage;

/**
 * @author SteveBrown
 *
 */
@Aspect
@Component
public class LogMesssageAspect {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	private final MethodExecutingMessage msg;
	
	public LogMesssageAspect(MethodExecutingMessage msg) {	
		this.msg = msg;
	}	
	
//	@Pointcut("execution(* com.sebrown.app.*.*(..))")
//	public void anyMethodInApp() {		
//	}
	
	@Around("@annotation(defMsg)")
	public Object logDebugMsg(
		ProceedingJoinPoint pjp, LogDefaultDebugMessage defMsg) throws Throwable {
		
		LOG.debug(msg.getMsgFromJoinPoint(pjp));		
		return pjp.proceed();
	}	
	
	@Before("@annotation(logInfoMsg)")
	public void logInfoMsg(LogInfoMessage logInfoMsg) {
		LOG.info(logInfoMsg.msg());
	}
			
}
