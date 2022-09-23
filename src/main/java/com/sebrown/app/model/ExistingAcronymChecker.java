/**
 * 
 */
package com.sebrown.app.model;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sebrown.app.config.VendorConfig;

/**
 * @author SteveBrown
 *
 * Check if an acronym exists in current vendors.
 */
@Component
public class ExistingAcronymChecker {
	
	private final VendorAcronyms venAcr;
	private final VendorConfig config;
		
	public ExistingAcronymChecker(VendorAcronyms venAcr, VendorConfig config) {	
		this.venAcr = venAcr;
		this.config = config;
	}

	public Optional<String> getAcronymForName(String name) {		
		return venAcr.getAcronymForName(name);
	}	
	
	public boolean isExisting(String acronym) {		
		return venAcr.isExisting(acronym);
	}	
	
	public String checkAcronym(String acronym) {
		if(isExisting(acronym)) {
			if(isNumbered(acronym)) {
				return incrementSequence(acronym);
			}else {
				return startSequence(acronym);
			}
		}
		return acronym;
	}
	
	private boolean isNumbered(String acronym) {
		return acronym.contains("_");
	}
	
	private String startSequence(String acronym) {
		int max = config.getMaxAcronymLen();
		int acrLen = acronym.length();
		int diff = 2 - (max - acrLen);
		boolean reduce = diff > 0;
		
		if(reduce) {
			var acr = acronym.substring(0, max - (diff + 1)) + "_1"; 
			return this.checkAcronym(acr);
		}else {
			return acronym + "_1";
		}
		
	}
	
	private String incrementSequence(String acronym) {
		int pos = acronym.indexOf("_");
		int num = Integer.parseInt(acronym.substring(pos+1, pos+2)) + 1;
		String res = acronym.substring(0, pos+1) + num;
		
		return res;
	}
	
}
