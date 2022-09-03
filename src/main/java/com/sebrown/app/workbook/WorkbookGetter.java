package com.sebrown.app.workbook;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface WorkbookGetter {
	XSSFWorkbook getWorkbook(final String fileName) throws IOException;
}
