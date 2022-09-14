/**
 * 
 */
package com.sebrown.app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * @author SteveBrown
 *
 */
@Service
public class StringContainsVendor implements VendorChecker {

	@Override
	public Optional<String> getVendorNameFromList(String name, List<String> vendors) {
		if(Objects.nonNull(name) && Objects.nonNull(vendors)) {
			String nameToFind = name.toLowerCase();
			return vendors.stream()
					.filter(v -> nameToFind.contains(v.toLowerCase()))
					.findFirst();			
		}else {
			return Optional.empty();
		}		
	}

}
