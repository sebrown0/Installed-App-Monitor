/**
 * 
 */
package com.sebrown.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author SteveBrown
 *
 */
public class WorkbookReader implements AutoCloseable {

	/*
	 * NOT USING BECAUSE OF ISSUES WITH SYNCHING THE FIS/FOS
	 */
	private XSSFWorkbook wb;
	private FileInputStream fis;
	
	private final String wbPath;
		
	public WorkbookReader(String wbPath) {		
		this.wbPath = wbPath;
	}

	public XSSFWorkbook getWorkbook() {
		if(Objects.isNull(wb)) {			
			try (FileInputStream file = new FileInputStream(new File(wbPath))){
				wb = new XSSFWorkbook(file);
				fis = file;
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}	      
		return wb;
	}
	
	public XSSFSheet getWorksheet(String wsName) {
		return getWorkbook().getSheet(wsName);
	}
	
	public XSSFRow getRow(String wsName, int rowNum) {
		var sht = getWorksheet(wsName);
		if(Objects.nonNull(sht)) {
			return sht.getRow(rowNum);	
		}
		return null;
	}
	
	public XSSFCell getCell(String wsName, int rowNum, int cellNum) {
		var row = getRow(wsName, rowNum);
		if(Objects.nonNull(row)) {
			
			try {
				var cell = row.getCell(cellNum);
				cell.getAddress(); //Trap any errors with the cell
				return cell;	
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
		return null;
	}

	@Override
	public void close() throws Exception {
		System.out.println("CLOSING WORKBOOK");
		wb.close();
		fis.close();
	}
	
}
