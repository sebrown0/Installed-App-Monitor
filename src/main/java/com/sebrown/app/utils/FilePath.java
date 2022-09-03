/**
 * 
 */
package com.sebrown.app.utils;

import java.io.File;

/**
 * @author SteveBrown
 *
 */
public class FilePath {

	public static String getFullPathFromApp(final String endOfPath) {
		File currDir = new File(".");
    String path = currDir.getAbsolutePath();
    return path.substring(0, path.length() - 1) + endOfPath;	
	}
	
}
