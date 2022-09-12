package com.sebrown.app.workbook;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.sebrown.app.service.XSSFWorkbookService;
import com.sebrown.app.utils.FilePath;

/**
 * 
 * @author SteveBrown
 *
 * Get a workbook instance of a WB in the App's directory.
 */
@Component
public class WorkbookInAppDir implements WorkbookGetter {
		
	private final XSSFWorkbookService wbService;	

	public WorkbookInAppDir(XSSFWorkbookService wbService) {	
		this.wbService = wbService;
	}

	@Override
	public XSSFWorkbook getWorkbook(final String pathAndName) throws IOException  {
    XSSFWorkbook workbook = 
    		wbService.setWorkBookPath(FilePath.getFullPathFromApp(pathAndName)).getWorkbook();
   
		return workbook;
	} 

}
