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
		fromName = originalName.toLowerCase();
		
		if(fromName.startsWith("microsoft")) return "Microsoft";
		
		if(fromName.startsWith("mysql")) return "MySql";
		
		if(fromName.startsWith("advanced micro devices")) return "AMD";
		
		if(fromName.startsWith("sun microsystems")) return "Sun Microsystems";
		
		if(fromName.startsWith("citrix")) return "Citrix";		
		
		if(fromName.startsWith("cisco")) return "CISCO";
		
		if(fromName.startsWith("check point")) return "CheckPoint";
		
		return originalName;
	}
	
}
