/**
 * 
 */
package com.sebrown.app.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 * 
 * Use to mark any classes that are used
 * by tests as helpers.
 * 
 */
@Component
@Retention(RUNTIME)
@Target({TYPE})
public @interface TestHelper {

}
