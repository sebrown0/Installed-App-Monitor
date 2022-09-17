/**
 * 
 */
package com.sebrown.app.file;

import java.nio.file.Path;

/**
 * @author SteveBrown
 *
 */
public interface FileWriter <T> {

	void truncateExistingAndWrite(Path path, T t);
	
}
