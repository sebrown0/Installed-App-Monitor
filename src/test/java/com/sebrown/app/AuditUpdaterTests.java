package com.sebrown.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.UTConfigProperties;
import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.updater.AuditUpdater;

@SpringBootTest
class AuditUpdaterTests {

//	@Value("${auditWorkbookPath}")
	private String auditWorkbookPath;
	
	@Autowired
	private UTConfigProperties testProps;
	
//	@Autowired
//	private AuditInPathService pathService;
	
	@Autowired
	private AuditUpdater updater;
	
//	@Autowired
//	private FileOpenChecker checker;
	
	@Test
	void test() {

		//Reinstate
		auditWorkbookPath = "./" + testProps.getWbOutName();
//		
//		if(Files.exists(Path.of(auditWorkbookPath))) {
//			if(true == checker.isFileOpen(auditWorkbookPath)) {
//				//exit
//				fail("File Open: " + auditWorkbookPath + ": Cannot continue");
//			}	
//		}
				
		//Create the audit out WB if doesn't exist.
		//An existing file will be 'moved' so if 
		//we don't want that to happen we'll 
		//have to amend this.
		AuditOutFileGetter fileGetter = 
				new AuditOutFileGetter(
						auditWorkbookPath,"Vendor Not Found");
		
		fileGetter.getFile();
				
		//Update audit out.
		updater.updateWorkbook();	
				
				
	}		

}
