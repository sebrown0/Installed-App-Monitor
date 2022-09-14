/**
 * 
 */
package com.sebrown.app.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sebrown.app.config.MappingProperties;

/**
 * @author SteveBrown
 *
 */
@Component
/*
 * 
 * Have interface: VendorList -> List<vendor name>
 * CLass VendorFile -> get from txt file
 * 
 */
public class VendorWsMapperFile implements VendorWsMapping {

	private final MappingProperties mappingProps;
	
	private Map<String, String> mappings;

	public VendorWsMapperFile(MappingProperties mappingProps) {
		this.mappingProps = mappingProps;
	}

	public VendorWsMapping setMappings(Map<String, String> mappings) {
		this.mappings = mappings;
		return this;
	}

	@Override
	public Map<String, String> readMappings() {

		return null;
	}
	
	@Override
	public void writeMappings() {
		// TODO Auto-generated method stub
		
	}


}
