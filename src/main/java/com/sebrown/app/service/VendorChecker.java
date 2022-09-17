/**
 * 
 */
package com.sebrown.app.service;

import java.util.List;
import java.util.Optional;

/**
 * @author SteveBrown
 *
 */

/*
 * TODO - DO WE NEED THIS CLASS??
 */
public interface VendorChecker {

	Optional<String> getVendorNameFromList(String name, List<String> vendors);
	
}
