/**
 * 
 */
package com.sebrown.app.model;

import java.util.List;
import java.util.Map;

/**
 * @author SteveBrown
 *
 * Get the vendors as Map<String, String>
 *  Key = vendor name
 *  Value = acronym (if any)
 *  
 * Or
 *  List<String>
 *   'name':'acronym'
 */
public interface Vendor {
	
	Map<String, String> getCurrentVendorMap();
	List<String> getCurrentAsList();
	void addVendor(String vendor);
	void persistCurrent(); 
	Vendor rollback();
	
}
