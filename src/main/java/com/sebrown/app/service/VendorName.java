/**
 * 
 */
package com.sebrown.app.service;

import static java.util.Objects.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sebrown.app.dao.VendorRepo;

/**
 * @author SteveBrown
 *
 */
@Service
@Scope("prototype")
public class VendorName {

	private List<String> existingNames;
	private List<String> newNames;
	
	private final VendorRepo repo;
	private final VendorNameRules vendorNameRules;
		
	public VendorName(VendorRepo repo, VendorNameRules vendorNameRules) {	
		this.repo = repo;
		this.vendorNameRules = vendorNameRules;
	}

	public String getVendor(String fromString) {
		String name = vendorNameRules.applyRulesTo(fromString);
		if(notExisting(name)) {
			addName(name);
		}
		return name;
	}
	
	private boolean notExisting(String name) {	
		return (getExisting().contains(name)) ? false : true;
	}
	
	private List<String> getExisting(){
		if(isNull(existingNames)) {
			existingNames = repo.getList();
		}
		return existingNames;
	}
	
	public void addName(String name) {
		if(isNull(newNames)) { newNames = new ArrayList<>(); }
		if(false == newNames.contains(name)) {
			newNames.add(name);			
		}		
	}
	
	public void flush() {
		if(nonNull(newNames)) {
			existingNames.addAll(newNames);
			existingNames.sort(String::compareToIgnoreCase);
			repo.writeList(existingNames);	
		}		
	}
	
}
