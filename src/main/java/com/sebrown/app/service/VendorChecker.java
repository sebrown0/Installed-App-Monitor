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
public interface VendorChecker {

	Optional<String> getVendorNameFromList(String name, List<String> vendors);
	
}
