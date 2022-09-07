/**
 * 
 */
package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AuditPathIteratorServiceTests {

	@Autowired
	AuditPathIteratorService pathItrServ;
	
	@Autowired
	WorkbookService wbServ;
	
	@Test @Order(1)	
	void getInstance() {
		System.out.println(">>>>>>>1");
		assertNotNull(pathItrServ);
	}

	@Test @Order(2)
	void hasNext_before_getNext_returnsTrue() {
		System.out.println(">>>>>>>2");
		assertTrue(pathItrServ.hasNext());
	}
	
	@Test @Order(3)
	void getNext() {
		Path p = pathItrServ.getNext();
		String startsWith = wbServ.getWbAuditIn().getNameStartsWith();
		
		assertEquals(startsWith, p
					.getFileName()
					.toString().substring(0, startsWith.length()));	
	}
	
	@Test @Order(4)
	void hasNext_after_getNext_returnsFalse() {
		Path p = pathItrServ.getNext();
		String startsWith = wbServ.getWbAuditIn().getNameStartsWith();
		
		assertEquals(startsWith, p
					.getFileName()
					.toString().substring(0, startsWith.length()));	
		
		assertFalse(pathItrServ.hasNext());
	}
	
}
