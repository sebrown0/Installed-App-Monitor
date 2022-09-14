/**
 * 
 */
package com.sebrown.app.dao;

import java.util.List;

/**
 * @author SteveBrown
 *
 */
public interface VendorList {

	List<String> getList();
	void updateList(List<String> lst);
	
}
