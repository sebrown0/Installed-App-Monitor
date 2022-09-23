/**
 * 
 */
package com.sebrown.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebrown.app.model.ExistingAcronymChecker;

/**
 * @author SteveBrown
 *
 */

@Service
public class VendorAcronymService {

	@Autowired
	private ExistingAcronymChecker existingAcr;
	
	@Autowired
	private VendorAcronymCreator creator;
	
	private String acronymForName;	
	
	public Optional<String> getAcronym(String forName) {

		existingAcr
			.getAcronymForName(forName)
			.ifPresentOrElse(
					acc -> acronymForName = acc, 
					() -> { 
						acronymForName = creator.getAcronym(forName);
						/*
						 * HAVE TO CHECK IF THIS IS IN THE LIST/FILE....
						 */
					});
		
		
		return Optional.ofNullable(acronymForName);
	}
		
}
