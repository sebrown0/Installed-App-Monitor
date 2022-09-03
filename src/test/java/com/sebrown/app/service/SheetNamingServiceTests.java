package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SheetNamingServiceTests {

	@Autowired
	private SheetNamingService namingServ;
	
	@Test
	void getInstance() {
		assertNotNull(namingServ);
	}

	@Test
	void testMySql() {
		assertEquals("MySql", namingServ.getSheetName("mysqlAB"));
	}
	
	@Test
	void testMicrosoft() {
		assertEquals("Microsoft", namingServ.getSheetName("MiCROsoft XYZ"));
	}

	@Test
	void testAMD() {
		assertEquals("AMD", namingServ.getSheetName("advanced micro devices XYZ"));
	}
	
	@Test
	void testSun() {
		assertEquals("Sun Microsystems", namingServ.getSheetName("sUn Microsystems XYZ"));
	}
}
