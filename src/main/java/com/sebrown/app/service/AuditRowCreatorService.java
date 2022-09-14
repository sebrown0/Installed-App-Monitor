/**
 * 
 */
package com.sebrown.app.service;

import static org.apache.poi.ss.usermodel.CellType.STRING;

import java.nio.file.Path;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;

import com.sebrown.app.dto.AppRowData;
import com.sebrown.app.dto.InstalledAppRowData;
import com.sebrown.app.row.RowCreator;
import com.sebrown.app.utils.RowValidator;

/**
 * @author SteveBrown
 *
 */
@Service
public class AuditRowCreatorService implements RowCreator {
	
	private final AssetIdService assetIdServ;
	private final SoftwareIdService softIdServ;	
	private final WorksheetOutService shtServ;
	
	public AuditRowCreatorService(
		AssetIdService assetIdServ, SoftwareIdService softIdServ, WorksheetOutService shtServ) {
		
		this.assetIdServ = assetIdServ;
		this.softIdServ = softIdServ;
		this.shtServ = shtServ;
	}

	@Override
	public boolean createRow(XSSFSheet ws, AppRowData rowData, Path wbAuditted) {		
		if(RowValidator.isValidRow(rowData)) {
			var row = ws.createRow(ws.getLastRowNum() + 1);
			var data = (InstalledAppRowData) rowData;
			
			createCell(shtServ.getColumnNumber("name"), row, data.getName());
			createCell(shtServ.getColumnNumber("identifyingNumber"), row, data.getIdentifyingNumber());
			createCell(shtServ.getColumnNumber("version"), row, data.getVersion());
			
			setSoftwareId(row, ws);
			setAssetId(row, wbAuditted);
			
			return true;
		}
		return false;
	}
	
	private void createCell(Optional<Integer> colNum, XSSFRow rw, String val) {
		colNum.ifPresent(n -> {
			//Column nums are zero based so when getting the value 
			//from the config file we subtract 1.
			rw.createCell(n - 1, STRING).setCellValue(val);			
		});
	}
	
	private void setSoftwareId(XSSFRow row, XSSFSheet ws) {
		createCell(shtServ.getColumnNumber("id"), row, softIdServ.getId(ws));
	}
	
	private void setAssetId(XSSFRow row, Path wbAuditted) {		
		String assetId = assetIdServ.getAssetId(wbAuditted);
		createCell(shtServ.getColumnNumber("installedAssetIDs"), row, assetId);
	}
	
}
