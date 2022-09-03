/**
 * 
 */
package com.sebrown.app.file;

import java.io.File;

import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 */
@Component
public class FileOpenChecker {

	public boolean isFileOpen(String path) {
		File file = new File(path);
		return !file.renameTo(file);
	}
	
}
