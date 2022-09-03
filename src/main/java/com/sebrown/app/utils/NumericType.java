/**
 * 
 */
package com.sebrown.app.utils;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @author SteveBrown
 *
 */
public interface NumericType {
	<T> T getNumericValue(Cell cell);
}
