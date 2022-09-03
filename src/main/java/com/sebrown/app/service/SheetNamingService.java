/**
 * 
 */
package com.sebrown.app.service;

import org.springframework.stereotype.Service;

/**
 * @author SteveBrown
 *
 */
@Service
public class SheetNamingService {

	public String getSheetName(String fromName) {
		String originalName = fromName;
		fromName = fromName.toLowerCase();
		
		if(fromName.startsWith("microsoft")) return "Microsoft";
		
		if(fromName.startsWith("mysql")) return "MySql";
		
		if(fromName.startsWith("advanced micro devices")) return "AMD";
		
		if(fromName.startsWith("sun microsystems")) return "Sun Microsystems";		
		
		return originalName;
	}
}
