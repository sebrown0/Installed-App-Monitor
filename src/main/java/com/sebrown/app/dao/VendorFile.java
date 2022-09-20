/**
 * 
 */
package com.sebrown.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebrown.app.config.ResourcePath;
import com.sebrown.app.config.VendorFiles;

/**
 * @author SteveBrown
 *
 * Get or write the vendor names from/to text file.
 * 
 */
@Component
public final class VendorFile implements VendorRepo {
	
	@Autowired
	private ResourcePath resPath;
		
	@Autowired
	private VendorFiles fileNames;
	
	private final VendorFileIn reader;
	private final VendorFileOut writer;
	
	public VendorFile(VendorFileIn reader, VendorFileOut writer) {
		this.reader = reader;
		this.writer = writer;
	}

	@Override
	public List<String> getList() {
		return reader.getNames(resPath + fileNames.getVendorFileName());
	}

	@Override
	public void writeList(List<String> names) {		
		writer.writeList(names, resPath + fileNames.getVendorFileName());
	}	
	
}
