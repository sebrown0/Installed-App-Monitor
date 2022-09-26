/**
 * 
 */
package com.sebrown.app.worksheet;

import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 */
@Component
public class RowNumber implements AfterHeader {

	@Override
	public int getNextRowNumIn(XSSFSheet sheet) {
		
		int num = 0;
		
		if(Objects.nonNull(sheet)) {
			var idx = 1;
			var rw = sheet.getRow(idx);
			
			while(Objects.nonNull(rw)) {
				rw = sheet.getRow(++idx);
			}			
			num = idx;
		}
		return num;
	}
	
}
