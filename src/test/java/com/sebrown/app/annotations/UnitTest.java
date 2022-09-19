package com.sebrown.app.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * 
 * @author SteveBrown
 * 
 * Annotate Spring unit tests.
 * 
 */
@SpringBootTest
@ActiveProfiles("test")
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface UnitTest {

}
