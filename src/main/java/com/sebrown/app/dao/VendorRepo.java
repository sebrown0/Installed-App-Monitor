/**
 * 
 */
package com.sebrown.app.dao;

import java.util.List;

/**
 * @author SteveBrown
 *
 */
public interface VendorRepo {

	List<String> getVendorNames();
	void writeVendorNames(List<String> lst);
	
}
