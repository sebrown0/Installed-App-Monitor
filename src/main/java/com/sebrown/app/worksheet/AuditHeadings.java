/**
 * 
 */
package com.sebrown.app.worksheet;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;

/**
 * @author SteveBrown
 *
 */
@Service
public class AuditHeadings implements ColumnHeading {
	private final List<String> headings =	List.of(
					"ID",	"Name",	"Identifying Number",	
					"Version",	"Installed Asset IDs");
	
	@Override
	public void createHeadings(XSSFSheet sht) {
		Row row = sht.createRow(0);
		for(int i = 0; i < headings.size(); i++) {
			Cell cell = row.createCell(i, CellType.STRING);
			cell.setCellValue(headings.get(i));			
		}
	}

}
