package com.sebrown.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class XSSFWorkbookService {
	
	public XSSFWorkbook getWorkbook(final String fileLocation) throws IOException  {
    
    FileInputStream file = new FileInputStream(new File(fileLocation));
    XSSFWorkbook workbook = new XSSFWorkbook(file);
        
		return workbook;
	}

}
