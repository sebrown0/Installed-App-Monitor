package com.sebrown.app.logging;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppLoggingAspect {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	

//	@Pointcut("within(com.sebrown.app.updater.AuditUpdater.updateWorkbook(..))")
//	public void pointcutUpdateWorkbook() {
//		//empty - name location of the pointcut
//	}
//	@Around("pointcutUpdateWorkbook")
//	public Object logAroundupdateWorkbook(ProceedingJoinPoint jp) {
//		LOG.info("Found audit workbook");
//		Object o = null;
//		try {
//			o = jp.proceed();
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return o;
//	}
	
//	@Pointcut("within(com.sebrown.app.updater..*	)")
//	public void definePackagePointcuts() {
//		//empty - name location of the pointcut
//	}		
//	@Around("definePackagePointcuts()")
//	public Object logAround(ProceedingJoinPoint jp) {
//		LOG.debug("\n\n");
//		LOG.debug(" ---***--- BEFORE METHOD EXECUTION ---***--- \n");
//		LOG.debug(
//				"{}.{} with args {}", 
//				jp.getSignature().getDeclaringTypeName(), 
//				jp.getSignature().getDeclaringType(), 
//				Arrays.toString(jp.getArgs()));
//		
//		Object o = null;
//		try {
//			o = jp.proceed();
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		LOG.debug(" ---***--- AFTER METHOD EXECUTION ---***--- \n");
//		LOG.debug(
//				"{}.{} with args {}", 
//				jp.getSignature().getDeclaringTypeName(), 
//				jp.getSignature().getDeclaringType(), 
//				Arrays.toString(jp.getArgs()));
//		
//		LOG.debug("---------------------------------------------------------\n");
//		
//		return o;	
//	}
	
//	@After("definePackagePointcuts()")
//	public void logBefore(JoinPoint jp) {
//		LOG.debug("\n\n");
//		LOG.debug(" ---***--- BEFORE METHOD EXECUTION ---***--- \n");
//		LOG.debug(
//				"{}.{} with args {}", 
//				jp.getSignature().getDeclaringTypeName(), 
//				jp.getSignature().getDeclaringType(), 
//				Arrays.toString(jp.getArgs()));
//		LOG.debug("---------------------------------------------------------\n");
//		
//	}
	
}
