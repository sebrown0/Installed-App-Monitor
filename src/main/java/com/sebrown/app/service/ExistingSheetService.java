/**
 * 
 */
package com.sebrown.app.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.sebrown.app.annotations.LogInfoMessage;

/**
 * @author SteveBrown
 *
 */
@Service
public class ExistingSheetService {

	@LogInfoMessage(msg = 
			"ExistingSheetService.getExistingSheets: " +
			"Getting existing sheets.")	
	public List<XSSFSheet> getExistingSheets(XSSFWorkbook wb) {		
		List<XSSFSheet> existingWorksheets = new ArrayList<>();
		
		wb
			.sheetIterator()
			.forEachRemaining(s -> 
				existingWorksheets.add((XSSFSheet) s));
		
		return existingWorksheets;
	}	
	
}
