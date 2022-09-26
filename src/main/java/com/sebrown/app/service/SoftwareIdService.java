/**
 * 
 */
package com.sebrown.app.service;

import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.sebrown.app.worksheet.AfterHeader;

/**
 * @author SteveBrown
 *
 */
@Service @Lazy
public class SoftwareIdService {
	
	private static final String NO_ID = "NO_ID";
	
//	@Autowired
//	private WorksheetOutService shtServ;
	
	@Autowired
	private VendorAcronymService venAcr;
	
	@Autowired
	private AfterHeader afterHeader;
	
	public String getId(XSSFSheet ws) {
		String id = NO_ID;
		
		if(Objects.nonNull(ws)) {					
			int nextRw = afterHeader.getNextRowNumIn(ws);
			
			if(nextRw >= 1) {
				id = getId(ws, nextRw);
			}
			
		}		
		return id;
	}
	
	private String getId(XSSFSheet ws, int nextRw) {
		String wsName = ws.getSheetName();
		String venAcronym = venAcr.getAcronym(wsName).orElse(NO_ID);
		
		return venAcronym + "_" + (nextRw - 1);
	}
			
}
