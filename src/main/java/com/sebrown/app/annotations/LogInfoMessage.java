package com.sebrown.app.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author SteveBrown
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface LogInfoMessage {
	String msg();
}
