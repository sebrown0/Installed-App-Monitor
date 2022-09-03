/**
 * 
 */
package com.sebrown.app.service;

import static org.apache.poi.ss.usermodel.CellType.STRING;

import java.nio.file.Path;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;

import com.sebrown.app.dto.InstalledAppRowData;
import com.sebrown.app.dto.RowData;
import com.sebrown.app.row.RowCreator;

/**
 * @author SteveBrown
 *
 */
@Service
public class AuditRowCreatorService implements RowCreator {
	
	private final AssetIdService assetIdServ;
	private final SoftwareIdService softIdServ;
	
	public AuditRowCreatorService(AssetIdService assetIdServ, SoftwareIdService softIdServ) {	
		this.assetIdServ = assetIdServ;
		this.softIdServ = softIdServ;
	}

	@Override
	public void createRow(XSSFSheet ws, RowData rowData, Path wbAuditted) {
		var data = (InstalledAppRowData) rowData;
		var row = ws.createRow(ws.getLastRowNum() + 1);
		
		row.createCell(1, STRING).setCellValue(data.getName());
		row.createCell(2, STRING).setCellValue(data.getIdentifyingNumber());
		row.createCell(3, STRING).setCellValue(data.getVersion());
		
		setSoftwareId(row, ws);
		setAssetId(row, wbAuditted);
	}
	
	private void setSoftwareId(Row row, XSSFSheet ws) {
		row.createCell(0, STRING).setCellValue(softIdServ.getId(ws));
	}
	
	private void setAssetId(Row row, Path wbAuditted) {		
		String assetId = assetIdServ.getAssetId(wbAuditted);
		row.createCell(4, STRING).setCellValue(assetId + ";");
	}
}
