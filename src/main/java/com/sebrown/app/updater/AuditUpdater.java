/**
 * 
 */
package com.sebrown.app.updater;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebrown.app.config.Config;
import com.sebrown.app.dto.InstalledAppRowData;
import com.sebrown.app.dto.RowData;
import com.sebrown.app.file.FileRenamer;
import com.sebrown.app.row.RowCreator;
import com.sebrown.app.row.RowFinder;
import com.sebrown.app.service.AuditDataService;
import com.sebrown.app.service.AuditInPathService;
import com.sebrown.app.service.AuditRowUpdaterService;
import com.sebrown.app.service.SheetNamingService;
import com.sebrown.app.service.WorksheetInService;
import com.sebrown.app.worksheet.ColumnHeading;
import com.sebrown.app.worksheet.WorksheetCreator;

/**
 * @author SteveBrown
 *
 */

@Component
public class AuditUpdater {
	
	@Autowired
	private WorksheetCreator wsCreator;
	
	@Autowired
	private AuditDataService auditDataService;
	
	@Autowired
	private AuditInPathService pathService;
		
	@Autowired
	private SheetNamingService namingService;
	
	@Autowired
	private WorksheetInService wbInShtServ;
	
	@Autowired
	private FileRenamer fileRenamer;
	
	@Autowired
	AuditWbOut auditWbOut;
	
	private final AuditRowUpdaterService updaterServ;
	private final RowCreator auditRowCreatorServ;
	private final ColumnHeading auditHeadings;
	
	public AuditUpdater(
			RowCreator auditRowCreator, 
			AuditRowUpdaterService updaterServ, ColumnHeading auditHeadings) {
			
			this.auditRowCreatorServ = auditRowCreator;
			this.updaterServ = updaterServ;
			this.auditHeadings = auditHeadings;
		}

	public void updateWorkbook(Config props) {
		auditWbOut.setOutputWorkbook(props);
		
		List<Path> paths = pathService.getPaths(props);

		paths.forEach(wbPath -> {	
			List<RowData> rowData = getAuditDataFromWb(wbPath); 
			updateEachRowInVendorSheet(rowData, wbPath);		
//			markFileAsRead(wbPath);
		});				
	}  
	
	private List<RowData> getAuditDataFromWb(Path p)  {		
		String shtName = 
				wbInShtServ.getInstalledApps().getName();
		
		return 
			auditDataService
				.getAuditDataFromWb(p, shtName);		
	}
	
	private void markFileAsRead(Path fPath) {
		fileRenamer.prependCharAndRename('x', fPath.toString());		
	}

	private void updateEachRowInVendorSheet(List<RowData> rowData, Path audittedWbPath) {		
		for (RowData rd : rowData) {
			InstalledAppRowData appRowData = (InstalledAppRowData) rd;
			
			var wsCurr = getCurrentSheet(appRowData);

			if(Objects.nonNull(wsCurr)) {
				Row row = RowFinder
						.findRowWithStringInCell(wsCurr, appRowData.getIdentifyingNumber(), 2);
				
				updateOrInsertRow(row, wsCurr, appRowData, audittedWbPath);	
			}			
		}		
	}
	
	private XSSFSheet getCurrentSheet(InstalledAppRowData data) {
		String vendor = VendorChecker.checkVendor(data.getVendor());
				
		return wsCreator.addWs(
				namingService.getSheetName(vendor), auditHeadings, auditWbOut);

	}
	
	private void updateOrInsertRow(Row row, XSSFSheet ws, InstalledAppRowData data, Path audittedWbPath) {
		if(Objects.nonNull(row)) {
			updaterServ.updateRow(row, data, audittedWbPath);
		}else {
			auditRowCreatorServ.createRow(ws, data, audittedWbPath);
		}
	}
		
}