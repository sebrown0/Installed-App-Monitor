/**
 * 
 */
package com.sebrown.app.workbook;

import static com.sebrown.app.utils.NumericCellsAsInteger.getValueFromCell;

import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;

import com.sebrown.app.dto.InstalledAppRowData;
import com.sebrown.app.dto.AppRowData;

/**
 * @author SteveBrown
 *
 */
public class InstalledAppRowMapper implements RowMapper {
	
	@Override
	public AppRowData mapRow(Row row) {
		AppRowData data = null;
		
		if(Objects.nonNull(row)) {
			data = new InstalledAppRowData.Builder()
					.setDescription(getStrVal(row, 0))
					.setIdentifyingNumber(getStrVal(row, 1))
					.setInstallDate(getIntVal(row, 2))					 
					.setName(getStrVal(row, 3))					
					.setProductID(getStrVal(row, 4))
					.setRegCompany(getStrVal(row, 5))
					.setRegOwner(getStrVal(row, 6))					
					.setVendor(getStrVal(row, 7))
					.setVersion(getStrVal(row, 8))					
					.getInstance();		
		}				
		return data;
	}

	private int getIntVal(Row row, int cellNum) {
		var val = getValueFromCell(row, cellNum);
		if(Objects.nonNull(val)) {
			if(val instanceof String) {
				return Integer.parseInt((String) val);
			}else {
				return (int) val;
			}
		}else {
			return 0;
		}		
	}
	
	private String getStrVal(Row row, int cellNum) {
		var val = getValueFromCell(row, cellNum);
		return (Objects.nonNull(val)) ? String.valueOf(val) : "";
	}
		
}
