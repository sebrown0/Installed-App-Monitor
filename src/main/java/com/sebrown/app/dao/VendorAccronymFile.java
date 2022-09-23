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
public final class VendorAccronymFile extends VendorFile {

	@Autowired @Qualifier("vendorAccronymPath")
	private FilePath acc;

	@Override
	protected FilePath getFilePath() {
		return acc;
	}
	
}
