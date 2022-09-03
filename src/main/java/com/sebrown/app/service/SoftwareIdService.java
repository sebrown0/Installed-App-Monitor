/**
 * 
 */
package com.sebrown.app.service;

import java.time.Instant;
import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;

/**
 * @author SteveBrown
 *
 */
@Service
public class SoftwareIdService {

	public String getId(XSSFSheet ws) {
		String id = null;
		if(Objects.nonNull(ws)) {
			var parts = ws.getSheetName().split(" "); 
			id = 	parts[0] + "_" + Instant.now().toEpochMilli();
		}		
		return id;
	}
	
}
