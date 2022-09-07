/**
 * 
 */
package com.sebrown.app.log;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author SteveBrown
 *
 */
public interface LogMessageFromJoinPoint {
	String getMsgFromJoinPoint(ProceedingJoinPoint pjp);
}
