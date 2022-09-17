/**
 * 
 */
package com.sebrown.app.dao;

import java.nio.file.Paths;
import java.util.List;

import com.sebrown.app.file.FileReader;

/**
 * @author SteveBrown
 *
 * Get vendor names from text file.
 * 
 */

/* NOT USING COMPONENT SCAN AS WE WANT TO 
   INJECT CONSTRUCTOR ARGS.  */

public class VendorNameFileIn implements VendorNameReader {
	
	private final String fullPathToFile;
	private final FileReader<List<String>> reader;
	
	public VendorNameFileIn(String fullPathToFile, FileReader<List<String>> reader) {	
		this.fullPathToFile = fullPathToFile;
		this.reader = reader;
	}

	@Override
	public List<String> getNames() {
		return (List<String>) reader.readFile(Paths.get(fullPathToFile));
	}
	
}
