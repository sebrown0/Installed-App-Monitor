/**
 * 
 */
package com.sebrown.app.dao;

import java.util.List;

/**
 * @author SteveBrown
 *
 * Read or write to vendor persistence object.
 */
public interface VendorRepo {

	List<String> getList();
	void writeList(List<String> lst);
	
}
