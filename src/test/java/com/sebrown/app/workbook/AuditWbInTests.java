/**
 * 
 */
package com.sebrown.app.workbook;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.service.AuditInPathService;
import com.sebrown.app.service.ExistingSheetService;
import com.sebrown.app.updater.AuditWbIn;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class AuditWbInTests {
	
	@Autowired
	private AuditInPathService pathService;
		
	@Autowired
	private ExistingSheetService shtServ;
		
	@Test
	void containsSystemInfoSheet() throws IOException {
		//Get the WBs on the resource path that start
		//with the Audit In prefix, i.e., ISO-Audit.
		List<Path> paths = pathService.getPaths();
		
		//Should only be one WB on in test resources that starts
		//with the Audit In prefix.
		String fromLoc = paths.get(0).toString();
		
		try (AuditWbIn auditWbIn = 
				new AuditWbIn(shtServ);) {
			
			Optional<XSSFSheet> shtSysInfo = 
				auditWbIn
					.setInputWorkbook(fromLoc)
					.containsWs("System Info");
			
			assertTrue(shtSysInfo.isPresent());
			
		} catch (Exception e) {	}		
	}
	
}
