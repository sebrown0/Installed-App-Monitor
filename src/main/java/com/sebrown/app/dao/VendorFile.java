/**
 * 
 */
package com.sebrown.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 * Get or write the vendor files.
 * 
 */
@Component
public class VendorFile implements VendorRepo {
	
	@Autowired
	private VendorFileIn reader;
	
	@Autowired
	private VendorFileOut writer;
	
	@Autowired @Qualifier("vendorFilePath")
	private FilePath vendorPath;
	
	@Override
	public List<String> getList() {
		return reader.getNames(getPathAndFileName());	
	}

	@Override
	public void writeList(List<String> names) {
		writer.writeList(names, getPathAndFileName());
	}	
	
	private String getPathAndFileName() {
		return vendorPath.getPathAndFileName();
	}
	
}
