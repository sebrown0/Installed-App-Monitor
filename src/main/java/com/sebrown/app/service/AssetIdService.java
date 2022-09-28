package com.sebrown.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

/**
 * 
 * @author SteveBrown
 *
 * Get the asset ID from the 
 * WB name and Host Name.
 * 
 * Host Name is in the System Info sheet.
 */
@Service
public class AssetIdService {
		
	private final SheetService shtServ;
	
	private XSSFSheet shtSystem;
	private XSSFWorkbook wb;
		
	public AssetIdService(SheetService shtServ) {		
		this.shtServ = shtServ;
	}

	public String getAssetId(Path wbPath) {		
		if(Objects.nonNull(wbPath)) {			
			setSheet(wbPath);
			if(Objects.nonNull(shtSystem)) {				
				return String.format("%s;",	getHostName());	
			}			
		}
		closeWb();		
		return getNoId();		
	}	
			
	private void setSheet(Path wbPath) {
		try (var fileIn =	new FileInputStream(new File(wbPath.toString()))){
			wb = new XSSFWorkbook(fileIn);
			shtSystem = wb.getSheet(shtServ.getSheet("auditIn", "systemInfo").getName());
		} catch (IOException | IllegalArgumentException e) {
			//TODO - LOG
			//DO NOTHING - NO ID WILL BE RETURNED
		}
	}
			
	private String getNoId() {
		return "NO-ID-" + Instant.now().getEpochSecond();
	}
	
	private String getHostName() {
		String hostName = "HOST_NOT_FOUND";
		
		Row row = shtSystem.getRow(0);
		if(Objects.nonNull(row)) {
			Cell cell = row.getCell(1);
			if(Objects.nonNull(cell) && cell.getCellType().equals(CellType.STRING)) {
				hostName = cell.getStringCellValue();	
			}
		}
		return hostName; 
	}
	
	private void closeWb() {
		if(Objects.nonNull(wb)) {
			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}		
	}
	
}
