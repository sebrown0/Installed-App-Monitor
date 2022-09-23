/**
 * 
 */
package com.sebrown.app.model;

import com.sebrown.app.dao.VendorRepo;

/**
 * @author SteveBrown
 *
 * Keep a current list of all
 * acronyms and keys;
 */

/*
 * Not on component scan. In configuration file.
 */
public class AcronymList {

	private final VendorRepo repo;

	public AcronymList(VendorRepo repo) {	
		this.repo = repo;
	}
	
	
}
