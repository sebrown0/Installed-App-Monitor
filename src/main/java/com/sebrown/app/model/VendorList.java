/**
 * 
 */
package com.sebrown.app.model;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class VendorList implements Vendor, VendorNames, VendorAcronyms {

	private List<String> list;
	private Map<String, String> map;
	
	private final VendorRepo repo;	

	public VendorList(VendorRepo repo) {	
		this.repo = repo;
	}
	
	public VendorList setList() throws IllegalStateException {
		if(Objects.nonNull(repo) ){
			list = repo.getList();
		}
			
		if(Objects.isNull(list)) {
			throw new IllegalStateException("Unable to get data from the Vendor repo");
		}
	
		constructMap();
		return this;
	}

	/*
	 * Sorted map of the vendors 
	 *  Key = vendor name
	 *  Value = acronym (not required)
	 */
	private void constructMap() {
		map = list.stream()
				.map(line -> line.split(":"))
				.collect(Collectors.toMap(
						a -> a[0], a -> a.length > 1 ? a[1] : null, 
								(a,b) -> a, TreeMap::new ));
	}
	
	private Stream<Entry<String, String>> getStreamOfEntries(){
		return map.entrySet().stream();
	}
	
	@Override // VendorNames
	public List<String> getNames() {
		return getStreamOfEntries()
				.map(es -> es.getKey())
				.toList();
	}

	@Override // VendorNames
	public void addNames(List<String> names) {
		if(Objects.nonNull(names)) { //TODO - other checks?
			names.forEach(n -> {
				map.putIfAbsent(n, null);
			});
		}		
	}
	
	@Override // VendorAcronyms
	public List<String> getAcronyms() {
		return getStreamOfEntries()
					.map(es -> es.getValue())
					.toList();
	}
	
	@Override // VendorAcronyms
	public void addAcronym(String venName, String acronym) {
		map.putIfAbsent(venName, acronym);
	}

	@Override // Vendor
	public Map<String, String> getCurrentVendorMap() {
		return map;
	}

	@Override // Vendor
	public List<String> getCurrentAsList() {
		return 
			getStreamOfEntries()
				.map(e -> String.format("%s:%s", e.getKey(), e.getValue()))
				.toList();
	}
	
}
