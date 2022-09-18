/**
 * 
 */
package com.sebrown.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author SteveBrown
 *
 * Items that should be removed from a string, i.e. vendor name.
 * 
 * TODO - Persist in DB, File etc. and lookup.
 * 
 */
@Service
public class InvalidItemsService implements InvalidVendorItems {

	@Override
	public List<String> getItems() {		
		return Arrays.asList(
				".", ",", "Systems", "Ltd", "LLC", "Corporation", "Corporate", 
				"Inc", "Incorporated"
		);
	}
	
}
