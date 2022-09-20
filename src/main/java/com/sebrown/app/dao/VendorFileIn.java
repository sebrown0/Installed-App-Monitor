/**
 * 
 */
package com.sebrown.app.dao;

import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sebrown.app.file.FileReader;

/**
 * @author SteveBrown
 *
 * Read from vendor file.
 * 
 */

@Component
public class VendorFileIn {
	
	private final FileReader<List<String>> reader;
	
	public VendorFileIn(FileReader<List<String>> reader) {
		this.reader = reader;
	}
	
	public List<String> getNames(String fullPathToFile) {
		return (List<String>) reader.readFile(Paths.get(fullPathToFile));
	}
	
}