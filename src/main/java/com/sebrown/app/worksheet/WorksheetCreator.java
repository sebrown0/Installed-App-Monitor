/**
 * 
 */
package com.sebrown.app.worksheet;

import java.util.Objects;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;

import com.sebrown.app.updater.AuditWbIn;

/**
 * @author SteveBrown
 *
 */
@Component
public class WorksheetCreator {
	
	private final AuditWbIn wbIn;
	
	public WorksheetCreator(AuditWbIn wbIn) {
		this.wbIn = wbIn;
	}

	public XSSFSheet addWs(final String wsName, final ColumnHeading headings) {
		Optional<XSSFSheet> ws = wbIn.containsWs(wsName);
		XSSFSheet wsReturn = null;
		
		if(!ws.isPresent()) {
			try {
				XSSFSheet wsNew = wbIn.addWs(wsName);
				if(Objects.nonNull(headings)) {
					headings.createHeadings(wsNew);
				}
				wsReturn = wsNew;
			} catch (IllegalArgumentException e) {
				//TODO - Log
				// The WB already has the sheet.
			}							
		}else {
			wsReturn = ws.get();
		}		
		return wsReturn;
	}
	
}
