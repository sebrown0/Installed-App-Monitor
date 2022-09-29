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
import com.sebrown.app.dto.AppRowData;
import com.sebrown.app.dto.InstalledAppRowData;
import com.sebrown.app.file.FileRenamer;
import com.sebrown.app.row.RowCreator;
import com.sebrown.app.row.RowFinder;
import com.sebrown.app.service.AuditDataService;
import com.sebrown.app.service.AuditInPathService;
import com.sebrown.app.service.AuditRowUpdaterService;
import com.sebrown.app.service.SheetNamingService;
import com.sebrown.app.service.VendorName;
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
	private VendorName vendorName;
	
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
		//Set the O/P WB, i.e., Installed Apps
		auditWbOut.setOutputWorkbook(props);
		
		List<Path> paths = pathService.getPaths(props);

		paths.forEach(wbPath -> {	
			List<AppRowData> rowData = getAuditDataFromWb(wbPath); 
			updateEachRowInVendorSheet(rowData, wbPath);		
			markFileAsRead(wbPath);
		});				
		
		//Persist any new vendors.
		vendorName.flush();
		
		//Close and save th O/P WB.
		auditWbOut.close();
	}  
	
	private List<AppRowData> getAuditDataFromWb(Path p)  {		
		String shtName = 
				wbInShtServ.getInstalledApps().getName();
		
		return 
			auditDataService
				.getAuditDataFromWb(p, shtName);		
	}
	
	private void markFileAsRead(Path fPath) {
		fileRenamer.prependCharAndRename('x', fPath.toString());		
	}

	private void updateEachRowInVendorSheet(List<AppRowData> rowData, Path audittedWbPath) {		
		for (AppRowData rd : rowData) {
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
		String vendor = OldVendorChecker.checkVendor(data.getVendor());
				
		if(Objects.nonNull(vendor) && vendor.length() >= 1) {
			var name = vendorName.getVendor(vendor);
			var shtName = namingService.getSheetName(name);
			return wsCreator.addWs(shtName, auditHeadings, auditWbOut);	
		}
		return null;
	}
	
	private void updateOrInsertRow(Row row, XSSFSheet ws, InstalledAppRowData data, Path audittedWbPath) {
		if(Objects.nonNull(row)) {
			updaterServ.updateRow(row, data, audittedWbPath);
		}else {
			auditRowCreatorServ.createRow(ws, data, audittedWbPath);
		}
	}
		
}