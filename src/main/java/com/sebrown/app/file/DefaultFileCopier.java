/**
 * 
 */
package com.sebrown.app.file;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.sebrown.app.annotations.HandleErr;
import com.sebrown.app.error.ErrorHandler;
import com.sebrown.app.error.ErrorToHandle.ErrorSeverity;
import com.sebrown.app.error.RaisedError;

/**
 * @author SteveBrown
 *
 */
@Component
public class DefaultFileCopier {
		
	private final RaisedError errHandler;		
	private final FilePathConstructor pathCnstr;
	
	public DefaultFileCopier(RaisedError errHandler, FilePathConstructor pathCnstr) {	
		this.errHandler = errHandler;
		this.pathCnstr = pathCnstr;
	}

	@HandleErr
	public Optional<ErrorHandler> copyFile(String srcPath) {				
		String destPath = pathCnstr.getDefaultCopyPath(srcPath);
		
		try {			
			FileUtils.copyFile(new File(srcPath), new File(destPath));			
		} catch (IOException e) {
			
			return Optional.of(
					errHandler
						.setMsg("Failed to copy file")
						.setSeverity(ErrorSeverity.CRITICAL));
			
		}
		return Optional.ofNullable(null);
	}

}
