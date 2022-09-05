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
	private String wbName;
		
	public AssetIdService(SheetService shtServ) {		
		this.shtServ = shtServ;
	}

	public String getAssetId(Path wbPath) {		
		if(Objects.nonNull(wbPath)) {			
			setSheetAndName(wbPath);
			if(Objects.nonNull(shtSystem)) {				
				return String.format(
						"%s-%s", 
						getUserName(wbName), getHostName());	
			}			
		}
		closeWb();		
		return getNoId();		
	}	
	
	private void setSheetAndName(Path wbPath) {
		setSheet(wbPath);		
		setName(wbPath);
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
	
	private void setName(Path wbPath) {
		wbName = wbPath.getFileName().toString();
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
	
	private String getUserName(String wbName) {
		String name = "NAME_NOT_FOUND";
		String[] parts = wbName.split("-");
		if(parts.length >= 6) {
			name = parts[2];
		}
		return name;
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
