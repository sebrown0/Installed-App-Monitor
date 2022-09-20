/**
 * 
 */
package com.sebrown.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 * Get or write the vendor files.
 * 
 */
@Component
public abstract class VendorFile implements VendorRepo {
	
	@Autowired
	private VendorFileIn reader;
	
	@Autowired
	private VendorFileOut writer;

	protected abstract FilePath getFilePath(); 
	
	@Override
	public List<String> getList() {
		return reader.getNames(getPathAndFileName());	
	}

	@Override
	public void writeList(List<String> names) {
		writer.writeList(names, getPathAndFileName());
	}	
	
	private String getPathAndFileName() {
		return this.getFilePath().getPathAndFileName();
	}
	
}
