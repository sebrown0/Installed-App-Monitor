/**
 * 
 */
package com.sebrown.app.file;

import java.nio.file.Path;

import org.springframework.stereotype.Component;

import com.sebrown.app.annotations.LogDefaultDebugMessage;

/**
 * @author SteveBrown
 *
 */
@Component
public class FilePathConstructor {

	/**
	 * Create a default path for the given path
	 * i.e. prepend a char to the file.
	 * 
	 * @param  srcPath: the path and file name.
	 * @return the srcPath with _ prepended to the file name;
	 */
	@LogDefaultDebugMessage
	public String getDefaultCopyPath(String srcPath) {	
		Path fPath = Path.of(srcPath);			
		String fName = "_" + fPath.getFileName().toString();
		
		return fPath.getParent().toString() + "/" + fName;
	}
	
}
