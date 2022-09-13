package com.sebrown.app.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author SteveBrown
 *
 * Log to INFO the annotation parameter.
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface LogInfoMessage {
	String msg();
}
