/**
 * 
 */
package com.sebrown.app.service;

import static org.apache.poi.ss.usermodel.CellType.STRING;

import java.nio.file.Path;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.sebrown.app.dto.InstalledAppRowData;
import com.sebrown.app.dto.AppRowData;
import com.sebrown.app.utils.ExistingAsset;

/**
 * @author SteveBrown
 *
 */
@Service
public class AuditRowUpdaterService {
	
	private final AssetIdService assetIdServ;	
	private String allIds;
	
	public AuditRowUpdaterService(AssetIdService assetIdServ) {
		this.assetIdServ = assetIdServ;
	}

	public String updateRow(Row row, AppRowData rowData, Path wbAuditted) {
		var data = (InstalledAppRowData) rowData;
		
		String idNum = row.getCell(2).getStringCellValue();
		String ver = row.getCell(3).getStringCellValue();
		
		if(Objects.nonNull(data)) {
			if(idNum.equals(data.getIdentifyingNumber()) && ver.equals(data.getVersion())) {
				String assetIds = row.getCell(4).getStringCellValue();				
				setAssetId(row, wbAuditted, assetIds);					
			}
		}
		return allIds;
	}
	
	private void setAssetId(Row row, Path wbAuditted, String existingIds) {		
		String assetId = assetIdServ.getAssetId(wbAuditted);
		allIds = existingIds;
		if(false == ExistingAsset.isExistingAssetId(existingIds, assetId)){
			allIds = allIds + assetId;	
			row.createCell(4, STRING).setCellValue(allIds);
		}	
	}
	
}
