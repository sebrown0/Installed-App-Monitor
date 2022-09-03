/**
 * 
 */
package com.sebrown.app.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

/**
 * @author SteveBrown
 *
 */
public class FileRenamer {

	public static void prependCharAndRename(char c, String srcPath) throws InvalidPathException  {		
		Path fPath = Path.of(srcPath);			
		String fName = c + fPath.getFileName().toString();
		String destPath = fPath.getParent().toString() + "/" + fName;
		try {
			FileUtils.copyFile(new File(srcPath), new File(destPath));
			FileUtils.forceDelete(new File(srcPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
