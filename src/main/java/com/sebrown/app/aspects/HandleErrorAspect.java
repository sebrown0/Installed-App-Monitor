/**
 * 
 */
package com.sebrown.app.aspects;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sebrown.app.error.ErrorHandler;
import com.sebrown.app.error.ErrorMessage;
import com.sebrown.app.error.RaisedError;

/**
 * @author SteveBrown
 *
 */
@Aspect
@Component
public class HandleErrorAspect {

	private final Logger LOG = 
			LoggerFactory.getLogger(this.getClass());
		
	@AfterReturning(
			pointcut = 
			"@annotation(com.sebrown.app.annotations.HandleErr)", returning = "error")
	public void logError(JoinPoint jp, Optional<ErrorHandler> error) {
			
		error.ifPresent(e -> {
			var sig = jp.getSignature();
			((RaisedError)e).setFromClass(sig.getDeclaringTypeName());
			((RaisedError)e).setFromMethod(sig.getName());
			((RaisedError)e).setArgs(getArgs(jp.getArgs()));
			
			logError((ErrorMessage) e);	
		});
		
	}
			
	private void logError(ErrorMessage msg) {
		LOG.error(msg.getMessage());
	}
	
	private List<String> getArgs(Object[] args) {
		return 
			Stream.of(args)
				.map(a -> a.toString())
				.toList();
	}

}
