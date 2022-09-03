/**
 * 
 */
package com.sebrown.app.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 * @author SteveBrown
 *
 * Get the cell's contents as an Integer. 
 */
public class IntegerType implements NumericType {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getNumericValue(Cell cell) {
		if(cell.getCellType().equals(CellType.NUMERIC)) {
			return (T) Integer.valueOf(
	  			Double.valueOf(cell.getNumericCellValue()).intValue());	
		}
		return (T) Integer.valueOf(0);		
	}

}
