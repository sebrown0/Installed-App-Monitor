/**
 * 
 */
package com.sebrown.app.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Retention(RUNTIME)
@Target(METHOD)
@Component
/**
 * @author SteveBrown
 *
 */
public @interface HandleErr {

}
