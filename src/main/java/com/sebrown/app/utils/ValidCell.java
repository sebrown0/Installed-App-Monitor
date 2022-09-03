/**
 * 
 */
package com.sebrown.app.utils;

import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @author SteveBrown
 *
 */
public class ValidCell {

	public static boolean isValidStrCell(Cell cell) {
		return (
				Objects.nonNull(cell) && 
				cell.getCellType().equals(STRING));
	}

	public static boolean isValidNumCell(Cell cell) {
		return (
				Objects.nonNull(cell) && 
				cell.getCellType().equals(NUMERIC));
	}
	
	public static boolean isValidBooleanCell(Cell cell) {
		return (
				Objects.nonNull(cell) && 
				cell.getCellType().equals(BOOLEAN));
	}
}
