/**
 * 
 */
package com.sebrown.app.model;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 * Get an acronym for a name from
 * the repo if it exists.
 */
@Component
public class ExistingAcronymForVendor {
	
	public Optional<String> getExisting(
			List<String> acrInRepo, String forName) {
			
			return acrInRepo.stream()
				.map(ln -> ln.split(":"))
				.filter(parts -> parts.length == 2)
				.filter(parts -> {
					var key = parts[0];
					return key.equals(forName);
				})
				.map(parts -> Optional.ofNullable(parts[1]))
				.findFirst()
				.orElseGet(Optional::empty);				
		}
	
}
