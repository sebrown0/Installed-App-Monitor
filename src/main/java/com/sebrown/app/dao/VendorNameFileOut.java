/**
 * 
 */
package com.sebrown.app.dao;

import java.nio.file.Paths;
import java.util.List;

import com.sebrown.app.file.FileWriter;

/**
 * @author SteveBrown
 *
 * Append new vendor names to file.
 * 
 */

/*
 * NOT USING COMPONENT SCAN AS WE WANT TO INJECT
 * CONSTRUCTOR ARGS. 
 */

public class VendorNameFileOut implements VendorNameWriter {
	
	private final String fullPathToFile;
	private final FileWriter<List<String>> writer;
			
	public VendorNameFileOut(String fullPathToFile, FileWriter<List<String>> writer) {	
		this.fullPathToFile = fullPathToFile;
		this.writer = writer;
	}

	@Override
	public void writeNames(List<String> names) {
		writer.truncateExistingAndWrite(Paths.get(fullPathToFile), names);
	}
	
}
