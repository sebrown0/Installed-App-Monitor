/**
 * 
 */
package com.sebrown.app.model;

import java.util.List;

/**
 * @author SteveBrown
 *
 * Get a list of Vendor names.
 * 
 */
public interface VendorNames {

	List<String> getNames();
	void addNames(List<String> names);
	
}
