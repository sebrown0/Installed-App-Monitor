/**
 * 
 */
package com.sebrown.app.service;

/**
 * @author SteveBrown
 *
 * Get (create) a name for a vendor from the string.
 */
public interface VendorNameSanitiser {
	
	String generateName(String fromString);
	
}
