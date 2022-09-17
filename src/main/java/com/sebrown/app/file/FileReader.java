/**
 * 
 */
package com.sebrown.app.file;

import java.nio.file.Path;

/**
 * @author SteveBrown
 *
 */
public interface FileReader <R> {

	R readFile(Path path);
	
}
