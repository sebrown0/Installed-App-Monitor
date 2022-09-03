/**
 * 
 */
package com.sebrown.app.row;

import static com.sebrown.app.utils.ValidCell.isValidStrCell;

import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;;

/**
 * @author SteveBrown
 *
 */
public class RowFinder {
	
	public static Row findRowWithStringInCell(XSSFSheet sht, String value, int cellNum) {		
		if(Objects.nonNull(sht)) {
			for(Row row: sht) {
				Cell cell = row.getCell(cellNum);
				if(isValidStrCell(cell)) {
					String cellVal = cell.getStringCellValue();
					if(cellVal.equals(value)) {
						return row;
					}	
				}else {
					//TODO - why is the cell not string type?
				}
			}
		}					
		return null;
	}

}
