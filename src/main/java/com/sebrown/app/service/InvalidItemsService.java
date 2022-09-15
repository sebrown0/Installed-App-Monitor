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
 */
@Service
public class InvalidItemsService implements InvalidVendorItems{

	@Override
	public List<String> getItems() {		
		return Arrays.asList(
				".", ",", "Ltd", "LLC", "Corporate", 
				"Inc", "Incorporated"
		);
	}
	

}
