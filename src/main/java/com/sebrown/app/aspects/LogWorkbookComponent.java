/**
 * 
 */
package com.sebrown.app.aspects;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author SteveBrown
 *
 * Use to annotate methods that get a Workbook component,
 * i.e. Workbook, Sheet etc.
 * 
 */
public @interface LogWorkbookComponent {
}
