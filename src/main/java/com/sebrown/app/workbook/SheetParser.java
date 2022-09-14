/**
 * 
 */
package com.sebrown.app.workbook;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.sebrown.app.dto.AppRowData;

/**
 * @author SteveBrown
 *
 */
public final class SheetParser {
	
	private final List<AppRowData> dataRows = new ArrayList<>();		
	
	public SheetParser parseSheet(final XSSFSheet sheet, final RowMapper rowMapper) {
		mapRows(sheet, rowMapper);		
		return this;		
	}
	
	private void mapRows(final XSSFSheet sheet, final RowMapper rowMapper) {
		for(Row row: sheet) {
			if(row.getRowNum() > 0) {
				dataRows.add(rowMapper.mapRow(row));
			}						
		}
	}

	public List<AppRowData> getRowData() {
		return dataRows;
	}
	
}
