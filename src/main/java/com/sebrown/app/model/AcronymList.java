/**
 * 
 */
package com.sebrown.app.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
public class AcronymList implements AcronymKeys{

	private List<String> list;
	private Map<String, String> map;
	
	private final VendorRepo repo;	

	public AcronymList(VendorRepo repo) {	
		this.repo = repo;
	}
	
	public AcronymList setList() throws IllegalStateException {
		if(Objects.nonNull(repo) ){
			list = repo.getList();
		}
			
		if(Objects.isNull(list)) {
			throw new IllegalStateException("Unable to get data from the Vendor repo");
		}
	
		constructMap();
		return this;
	}

	private void constructMap() {
		map = list.stream()
				.map(line -> line.split(":"))
				.collect(Collectors.toMap(
						a -> a[0], a -> a.length > 1 ? a[1] : "" ));
	}
	
	@Override
	public List<String> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
