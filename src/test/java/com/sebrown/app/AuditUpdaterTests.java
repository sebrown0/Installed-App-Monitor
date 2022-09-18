package com.sebrown.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.UnitTestProps;
import com.sebrown.app.file.AuditOutFileGetter;
import com.sebrown.app.updater.AuditUpdater;

@SpringBootTest
@ActiveProfiles("test")
class AuditUpdaterTests {
	
	@Autowired
	private AuditUpdater updater;
	
	@Autowired
	UnitTestProps props;

	/*
	 * JUST UPDATES THE WBs.
	 * HAVE TO ADD VALIDATION.
	 */
	@Test
	void test() {	
		
		//Create the audit out WB if doesn't exist.
		//An existing file will be 'moved' so if 
		//we don't want that to happen we'll 
		//have to amend this.
		AuditOutFileGetter fileGetter = 
				new AuditOutFileGetter(
						props,"Vendor Not Found");
		
		fileGetter.getFile();
				
		//Update audit out.
		updater.updateWorkbook(props);	
				
				
	}		

}
