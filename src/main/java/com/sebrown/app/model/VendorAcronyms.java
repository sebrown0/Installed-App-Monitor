/**
 * 
 */
package com.sebrown.app.model;

import java.util.List;

/**
 * @author SteveBrown
 *
 * Get a list of Vendor acronyms.
 * 
 */
public interface VendorAcronyms {

	List<String> getAcronyms();
	void addAcronym(String venName, String acronym);
	
}
