/**
 * 
 */
package com.sebrown.app.dao;

import java.util.List;

/**
 * @author SteveBrown
 *
 */
public interface VendorRepo {

	List<String> getList();
	void writeList(List<String> lst);
	
}
