/**
 * 
 */
package com.sebrown.app.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.AppConfig.Workbook;
import com.sebrown.app.service.WorkbookService;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class WorkbookServiceTests {

	@Autowired
	WorkbookService wbServ;
	
	@Test
	void getInstance() {
		assertNotNull(wbServ);
	}
	
	@Test
	void getAuditIn() {
		Workbook auditIn = wbServ.getWorkbook("auditIn");		
		assertEquals("Audit In", auditIn.getName());
	}
	
	@Test
	void forNullWbName_getNullObj() {
		Workbook auditIn = wbServ.getWorkbook("XauditInX");
		assertNull(auditIn);		
	}
	
}
