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

import com.sebrown.app.config.AppConfig;
import com.sebrown.app.config.AppConfig.Sheet;
import com.sebrown.app.config.AppConfig.Workbook;
import com.sebrown.app.dto.InstalledAppRowData;
import com.sebrown.app.dto.RowData;
import com.sebrown.app.file.FileRenamer;
import com.sebrown.app.row.RowCreator;
import com.sebrown.app.row.RowFinder;
import com.sebrown.app.service.AuditDataService;
import com.sebrown.app.service.AuditRowUpdaterService;
import com.sebrown.app.service.SheetNamingService;
import com.sebrown.app.service.WorkbookService;
import com.sebrown.app.utils.FilePath;
import com.sebrown.app.workbook.WorkbookWalker;
import com.sebrown.app.worksheet.ColumnHeading;
import com.sebrown.app.worksheet.WorksheetCreator;

/**
 * @author SteveBrown
 *
 */
@Component
public class AuditUpdater {
		
//	@Value("${shtInstalledApps}")
//	private String shtName;
//	
//	@Value("${auditWBsStartWith}")
//	private String workbooksStartWith;
//		        
//	@Value("${auditedWorkbookPath}")
//	private String auditedWorkbookPath;
	
	@Autowired
	private AppConfig appConfig;
	
//	@Autowired
	private WorkbookService wbService;
	
	@Autowired
	private AuditWbIn wbIn;
	
	@Autowired
	private WorksheetCreator wsCreator;
	
	@Autowired
	private AuditDataService auditDataService;
	
	@Autowired
	private WorkbookWalker workbookWalker;
	
	@Autowired
	private SheetNamingService namingService;
	
	private AuditRowUpdaterService updaterServ;
	private RowCreator auditRowCreatorServ;
	private ColumnHeading auditHeadings;
	private Workbook wbAuditIn;
	
	public AuditUpdater(WorkbookService wbService, RowCreator auditRowCreator, AuditRowUpdaterService updaterServ, ColumnHeading auditHeadings) {		
		this.auditRowCreatorServ = auditRowCreator;
		this.updaterServ = updaterServ;
		this.auditHeadings = auditHeadings;
		this.wbAuditIn = wbService.getWbAuditIn();
	}

	public void updateWorkbook() { 
		
		//Get Installed Software.xlsx 
		wbIn.setWorkbook().getExistingSheets();
		
		var auditedWorkbooks = workbookWalker.getPathsOfWorkbooks(
					wbAuditIn.getNameStartsWith(), 
					FilePath.getFullPathFromApp(appConfig.getProps().getPath()));
	
		auditedWorkbooks.forEach(wbPath -> {	
			List<RowData> rowData = getAuditDataFromWb(wbPath); 
			updateEachRowInVendorSheet(rowData, wbPath);		
			markFileAsRead(wbPath);
		});		 
		
		wbIn.closeWb(); 
	}  
	
	private void markFileAsRead(Path fPath) {
		FileRenamer.prependCharAndRename('x', fPath.toString());		
	}

	private List<RowData> getAuditDataFromWb(Path p)  {
		var shtInstalledApps = wbAuditIn.getSheets().get("installedApps");
		return auditDataService.getAuditDataFromWb(p, shtInstalledApps.getName());		
	}
	
//	private void updateEachRow(List<RowData> rowData, Path audittedWbPath) {
//		updateEachRowInVendorSheet(rowData, audittedWbPath);
//	}
	
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
		return wsCreator.addWs(namingService.getSheetName(vendor), auditHeadings);
	}
	
	private void updateOrInsertRow(Row row, XSSFSheet ws, InstalledAppRowData data, Path audittedWbPath) {
		if(Objects.nonNull(row)) {
			updaterServ.updateRow(row, data, audittedWbPath);
		}else {
			auditRowCreatorServ.createRow(ws, data, audittedWbPath);
		}
	}
		
}
