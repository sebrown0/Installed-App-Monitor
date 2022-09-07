/**
 * 
 */
package com.sebrown.app.error;

import static com.sebrown.app.error.ErrorToHandle.ErrorSeverity.*;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sebrown.app.annotations.LogDefaultDebugMessage;
import com.sebrown.app.error.ErrorToHandle.ErrorSeverity;

/**
 * @author SteveBrown
 *
 */
@Component
public class LogAndHandleError {
	
	private final Logger LOG = 
			LoggerFactory.getLogger(this.getClass());
		
	@LogDefaultDebugMessage
	public void handleError(boolean quitting, ErrorSeverity severity) {		
		
		if (isAcceptableError(severity)) {			
			LOG.info("Encountered error, but can recover.");
		}else {
			handleUnacceptableError(quitting);		
		}				
		
	}

	private boolean isAcceptableError(ErrorSeverity severity) {
		return Objects.nonNull(severity) && 
				(severity.equals(LOW) || (severity.equals(MEDIUM)));
	}
	
	private void handleUnacceptableError(boolean quitting) {
		if(quitting) {
			LOG.info("Error too severe, cannot continue, QUITTING APP!");
			LOG.info("[LogAndHandleError.handleUnacceptableError] TODO HANDLE QUIT");
			//TODO - QUIT!! (remove log above)
		}else {
			LOG.info("Error is severe but quitting = " + 
					quitting + ", so will attempt to continue.");
		}	
	}
}
