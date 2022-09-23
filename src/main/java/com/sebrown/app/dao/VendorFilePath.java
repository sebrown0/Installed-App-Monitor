/**
 * 
 */
package com.sebrown.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebrown.app.config.ResourcePath;
import com.sebrown.app.config.VendorFiles;

/**
 * @author SteveBrown
 *
 */
@Component
public class VendorFilePath implements FilePath {
	
	@Autowired
	private ResourcePath resPath;
			
	@Autowired
	private VendorFiles fileNames;
	
	@Override
	public String getFileName() {
		return fileNames.getVendorFileName();
	}

	@Override
	public String getPath() {
		return resPath.getPath();
	}

	@Override
	public String getPathAndFileName() {		
		return String.format("%s/%s", getPath(), this.getFileName());
	}

}
