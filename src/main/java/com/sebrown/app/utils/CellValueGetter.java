/**
 * 
 */
package com.sebrown.app.utils;

import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @author SteveBrown
 *
 * Get the value of the cell for the types in the switch statement.
 */
public class CellValueGetter {

	private final Cell cell;
	private final NumericType numericType;
	
	/** 
	 * @param cell				The cell.
	 * @param numericType The type to convert the number to.
	 */
	public CellValueGetter(Cell cell, NumericType numericType) {	
		this.cell = cell;
		this.numericType = numericType;
	} 
	
	@SuppressWarnings("unchecked")
	public <T> T getValue() {
		T value = null;
		
		if(Objects.nonNull(cell)) {
	    switch (cell.getCellType()) {
	      case NUMERIC:
	    		value = numericType.getNumericValue(cell);
	        break;
	      case STRING:
	        value = (T) cell.getStringCellValue();
	        break;
				default: 
					value = (T) "";
					break;
	    } 			
		}
   
		return value;
	}
	
}
