/**
 * 
 */
package com.sebrown.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 */
@Component @Lazy
public final class VendorNameFile extends VendorFile {

	@Autowired @Qualifier("vendorNamePath")
	private FilePath name;

	@Override
	protected FilePath getFilePath() {
		return name;
	}
	
}
