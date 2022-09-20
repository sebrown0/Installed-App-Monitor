/**
 * 
 */
package com.sebrown.app.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.config.ResourcePath;

/**
 * @author SteveBrown
 *
 */
public abstract class VendorFilePath implements FilePath {
	
	@Autowired
	private ResourcePath resPath;
		
	@Override
	public String getPath() {
		return resPath.getPath();
	}

	@Override
	public String getPathAndFileName() {		
		return String.format("%s/%s", getPath(), this.getFileName());
	}

}
