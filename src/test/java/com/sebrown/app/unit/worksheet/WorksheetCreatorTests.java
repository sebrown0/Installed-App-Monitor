/**
 * 
 */
package com.sebrown.app.unit.worksheet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.UnitTestProps;
import com.sebrown.app.file.FileOpenChecker;
import com.sebrown.app.service.XSSFWorkbookService;
import com.sebrown.app.worksheet.WorksheetCreator;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class WorksheetCreatorTests {

	@Autowired
	private FileOpenChecker checker;
	
	@Autowired
	private UnitTestProps testProps;
	
	@Autowired
	WorksheetCreator wsCreator;
	
	@Autowired
	XSSFWorkbookService wbServ;
	
	@Test
	void getInstance() {
		assertNotNull(wsCreator);
	}
	
	@Test
	void test() throws IOException {
		String auditWorkbookPath = testProps.getAuditOutFullPath();
		
		if(Files.exists(Path.of(auditWorkbookPath))) {
			if(true == checker.isFileOpen(auditWorkbookPath)) {
				fail("File Open: " + auditWorkbookPath + ": Cannot continue");
			}	
		}
		
//		XSSFWorkbook wb = wbServ.setWorkBookPath(auditWorkbookPath).getWorkbook();
//		wsCreator.addWs("New WS", new AuditHeadings(), wbServ.setWorkBookPath(auditWorkbookPath));
	}

}
