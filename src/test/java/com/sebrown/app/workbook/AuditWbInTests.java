/**
 * 
 */
package com.sebrown.app.workbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.updater.AuditWbIn;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class AuditWbInTests {

	@Autowired
	AuditWbIn wbIn;
	
	@Test
	void getInstance() {
		assertNotNull(wbIn);
	}

}
