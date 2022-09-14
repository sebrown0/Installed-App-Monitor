/**
 * 
 */
package com.sebrown.app.updater;

import java.util.Objects;

import org.apache.poi.ss.util.WorkbookUtil;

/**
 * @author SteveBrown
 *
 */
public class OldVendorChecker {

	public static String checkVendor(String vendor) {
		String safeName = "Vendor Not Found";
		
		if(Objects.nonNull(vendor) && vendor.length() > 0) {
			safeName = WorkbookUtil.createSafeSheetName(vendor);	
		}
		return safeName;
	}
}
