/**
 * 
 */
package com.sebrown.app.dao;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 * Get or write the vendor names from/to text file.
 * 
 */
@Component
public final class VendorNameFile implements VendorRepo {

	private final VendorNameReader reader;
	private final VendorNameWriter writer;

	public VendorNameFile(VendorNameReader reader, VendorNameWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}

	@Override
	public List<String> getVendorNames() {		
		return reader.getNames();		
	}

	@Override
	public void writeVendorNames(List<String> names) {		
		writer.writeNames(names);
	}	
	
}
