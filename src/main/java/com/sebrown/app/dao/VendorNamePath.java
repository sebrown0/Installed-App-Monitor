/**
 * 
 */
package com.sebrown.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebrown.app.config.VendorFiles;

/**
 * @author SteveBrown
 *
 */
@Component
public final class VendorNamePath extends VendorFilePath {
			
	@Autowired
	private VendorFiles fileNames;

	@Override
	public String getFileName() {
		return fileNames.getVendorFileName();
	}

}
