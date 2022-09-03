/**
 * 
 */
package com.sebrown.app.utils;

import org.apache.poi.ss.usermodel.Row;

/**
 * @author SteveBrown
 *
 */
public class NumericCellsAsInteger {
	
	public static <T> T getValueFromCell(Row row, int num) {
		return new CellValueGetter(
				row.getCell(num), new IntegerType()).getValue();
	}
	
}
