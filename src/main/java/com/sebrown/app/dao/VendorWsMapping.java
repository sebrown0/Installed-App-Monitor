/**
 * 
 */
package com.sebrown.app.dao;

import java.util.Map;

/**
 * @author SteveBrown
 *
 */
public interface VendorWsMapping {
	void writeMappings();
	Map<String, String> readMappings();
}
