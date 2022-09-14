/**
 * 
 */
package com.sebrown.app.row;

import java.nio.file.Path;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.sebrown.app.dto.AppRowData;

/**
 * @author SteveBrown
 * 
 * Create a row in the WS.
 */
public interface RowCreator {
	boolean createRow(XSSFSheet ws, AppRowData rowData, Path wbAuditted);
}
