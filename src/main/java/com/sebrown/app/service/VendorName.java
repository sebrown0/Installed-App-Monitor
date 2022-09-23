/**
 * 
 */
package com.sebrown.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebrown.app.model.Vendor;
import com.sebrown.app.model.VendorNames;

/**
 * @author SteveBrown
 *
 * Get vendor name from string, 
 * using the rules given.
 */
@Service
public class VendorName {
	
	@Autowired
	private VendorNameRules vendorNameRules;
	
	@Autowired
	private VendorNames vendor;

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
		return ((VendorNames) vendor).getNames();
	}
	
	public void addName(String name) {
		vendor.addNames(Arrays.asList(name));
	}
	
	public void flush() {		
		((Vendor) vendor).persistCurrent();				
	}
	
}
