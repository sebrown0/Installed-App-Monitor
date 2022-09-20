/**
 * 
 */
package com.sebrown.app.dao;

import java.util.List;

/**
 * @author SteveBrown
 *
 */
public interface VendorNameReader {

	List<String> getNames(String fullPathToFile);
	
}
