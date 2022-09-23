/**
 * 
 */
package com.sebrown.app.model;

import java.util.List;
import java.util.Optional;

/**
 * @author SteveBrown
 *
 * Get a list of Vendor acronyms.
 * 
 */
public interface VendorAcronyms {

	List<String> getAcronyms();
	void addAcronym(String venName, String acronym);
	boolean isExisting(String acronym);
	Optional<String> getAcronymForName(String name);
	
}
