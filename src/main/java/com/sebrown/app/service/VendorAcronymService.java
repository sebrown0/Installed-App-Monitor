/**
 * 
 */
package com.sebrown.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.dao.VendorRepo;
import com.sebrown.app.model.ExistingVendorAcronym;

/**
 * @author SteveBrown
 *
 */
/*
 * Not on component scan. In configuration file.
 */
public class VendorAcronymService {

	@Autowired
	private ExistingVendorAcronym existingAcc;
	
	@Autowired
	private VendorAcronymCreator creator;
	
	private List<String> acronymsInRepo;
	private String acronymForName;
	
	private final VendorRepo repo;		
	
	public VendorAcronymService(VendorRepo repo) {	
		this.repo = repo;	
	}
	
	public Optional<String> getAcronym(String forName) {
		acronymsInRepo = repo.getList();
		
		existingAcc
			.getExisting(acronymsInRepo, forName)
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
