/**
 * 
 */
package com.sebrown.app.dao;

import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sebrown.app.file.FileWriter;

/**
 * @author SteveBrown
 *
 * Write list to vendor file.
 * 
 */

@Component
public class VendorFileOut {
	
	private final FileWriter<List<String>> writer;
			
	public VendorFileOut(FileWriter<List<String>> writer) {		
		this.writer = writer;
	}

	public void writeList(List<String> names, String fullPathToFile) {
		writer.truncateExistingAndWrite(Paths.get(fullPathToFile), names);
	}
	
}
