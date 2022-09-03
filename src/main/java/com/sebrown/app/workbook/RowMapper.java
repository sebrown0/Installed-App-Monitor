/**
 * 
 */
package com.sebrown.app.workbook;

import org.apache.poi.ss.usermodel.Row;

import com.sebrown.app.dto.RowData;

/**
 * @author SteveBrown
 *
 * Get an object with the row's fields as properties.
 */
public interface RowMapper {

	RowData mapRow(Row row);
	
}
