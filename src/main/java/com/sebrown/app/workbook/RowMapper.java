/**
 * 
 */
package com.sebrown.app.workbook;

import org.apache.poi.ss.usermodel.Row;

import com.sebrown.app.dto.AppRowData;

/**
 * @author SteveBrown
 *
 * Get an object with the row's fields as properties.
 */
public interface RowMapper {

	AppRowData mapRow(Row row);
	
}
