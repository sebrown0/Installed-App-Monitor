package com.sebrown.app;

import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.UTConfigProperties;
import com.sebrown.app.file.AuditFileGetter;
import com.sebrown.app.file.FileOpenChecker;
import com.sebrown.app.service.AuditInPathService;
import com.sebrown.app.updater.AuditUpdater;

@SpringBootTest
class AuditUpdaterTests {

//	@Value("${auditWorkbookPath}")
	private String auditWorkbookPath;
	
	@Autowired
	private UTConfigProperties testProps;
	
	@Autowired
	private AuditInPathService pathService;
	
	@Autowired
	private AuditUpdater updater;
	
	@Autowired
	private FileOpenChecker checker;
	
	@Test
	void test() {

		auditWorkbookPath = testProps.getWbOutName();
		
		if(Files.exists(Path.of(auditWorkbookPath))) {
			if(true == checker.isFileOpen(auditWorkbookPath)) {
				//exit
				fail("File Open: " + auditWorkbookPath + ": Cannot continue");
			}	
		}
					
//		AuditFileGetter fileGetter = 
//				new AuditFileGetter(
//						auditWorkbookPath,"Vendor Not Found");
//		
//		fileGetter.getFile();
				
		updater.updateWorkbook();	
				
				
	}		

}
