/**
 * 
 */
package com.sebrown.app.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
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
public class FileRenamer {

	private final RaisedError errHandler;		
	
	public FileRenamer(RaisedError errHandler) {	
		this.errHandler = errHandler;	
	}
	
	@HandleErr
	public Optional<ErrorHandler> prependCharAndRename(char c, String srcPath) {		
		
		Path fPath = Path.of(srcPath);			
		String fName = c + fPath.getFileName().toString();
		String destPath = fPath.getParent().toString() + "/" + fName;
	
		try {
			FileUtils.copyFile(new File(srcPath), new File(destPath));
			FileUtils.forceDelete(new File(srcPath));
		} catch (IOException e) {
			return Optional.of(
					errHandler
						.setMsg("Failed to rename file")
						.setSeverity(ErrorSeverity.CRITICAL));
		}
		return Optional.ofNullable(null);
	}
	
}
