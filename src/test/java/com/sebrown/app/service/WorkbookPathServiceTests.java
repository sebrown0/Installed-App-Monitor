/**
 * 
 */
package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@Profile("TEST")
class WorkbookPathServiceTests {

	@Autowired
	WorkbookPathService pathServ;
	
	@Autowired
	WorkbookService wbServ;
	
	@Autowired
	ResourceService resServ;
		
	@Test
	void getInstance() {
		assertNotNull(pathServ);
	}

	@Test
	void getAuditInLocation() {
		String resPath = resServ.getPath();
		String instWbName = wbServ.getWorkbook("auditOut").getName();
		String expected = String.format("%s/%s", resPath, instWbName);
			
		assertEquals(expected, String.format("%s/%s", resPath, instWbName));
	}
	
	@Test
	void getAuditOutFullPath() {
		String resPath = resServ.getPath();
		String instWbName = wbServ.getWorkbook("auditOut").getName();
		String expected = String.format("%s/%s", resPath, instWbName);
			
		assertEquals(expected, pathServ.getAuditOutFullPath());
	}
	
}
