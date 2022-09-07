/**
 * 
 */
package com.sebrown.app.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author SteveBrown
 *
 */
public @interface LogDefaultDebugMessage {
	/*
	 * DO NOT USE ON METHODS THAT HAVE ProceedingJoinPoint 
	 * AS PARAMETER.
	 * 
	 */
}
