/**
 * 
 */
package com.sebrown.app.utils;

import java.util.Objects;

import com.sebrown.app.dto.AppRowData;

/**
 * @author SteveBrown
 *
 * Check each value that should be validated in the App Row
 */
public class RowValidator {

	public static boolean isValidRow(AppRowData data) {
		for(String s: data.validateData()) {
			if(false == isValidValue(s)) {
				return false;
			}
		}					
		return true;
	}
	
	private static boolean isValidValue(String s) {
		return 
			(Objects.nonNull(s) && s.length() >= 1) ? 
					true : false;
	}
	
}
