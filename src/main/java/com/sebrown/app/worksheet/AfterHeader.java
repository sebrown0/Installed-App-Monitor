/**
 * 
 */
package com.sebrown.app.worksheet;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * @author SteveBrown
 *
 */
public interface AfterHeader {

	int getNextRowNumIn(XSSFSheet sheet);
	
}
