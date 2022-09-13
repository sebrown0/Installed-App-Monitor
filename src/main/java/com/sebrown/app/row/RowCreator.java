/**
 * 
 */
package com.sebrown.app.row;

import java.nio.file.Path;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.sebrown.app.dto.RowData;

/**
 * @author SteveBrown
 * 
 * Create a row in the WS.
 */
public interface RowCreator {
	void createRow(XSSFSheet ws, RowData rowData, Path wbAuditted);
}
