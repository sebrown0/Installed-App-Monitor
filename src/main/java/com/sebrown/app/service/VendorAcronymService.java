/**
 * 
 */
package com.sebrown.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.sebrown.app.model.ExistingAcronymChecker;
import com.sebrown.app.model.VendorAcronyms;

/**
 * @author SteveBrown
 *
 */

@Service @Lazy
public class VendorAcronymService {

	@Autowired @Lazy
	private ExistingAcronymChecker existingAcr;
	
	@Autowired @Lazy
	private VendorAcronymCreator creator;
	
	@Autowired @Lazy
	private	VendorAcronyms vendorAcronyms;
	
	private String acronymForName;	
	
	public Optional<String> getAcronym(String forName) {
		
		existingAcr
			.getAcronymForName(forName)
			.ifPresentOrElse(
					acc -> acronymForName = acc, 
					() -> { 
						acronymForName = creator.getAcronym(forName);
						acronymForName = existingAcr.checkAcronym(acronymForName);
						vendorAcronyms.addAcronym(forName, acronymForName);
					});
				
		return Optional.ofNullable(acronymForName);
	}
		
}
