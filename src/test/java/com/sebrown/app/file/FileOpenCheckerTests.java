package com.sebrown.app.file;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileOpenCheckerTests {

	@Value("${auditWorkbookPath}")
	private String auditWorkbookPath;
	
	@Autowired
	private FileOpenChecker checker;
	
	@Test	@Order(1)
	void getInstance() {
		assertNotNull(checker);
	}

	/*
	 * NOTE: The following tests are mutually exclusive and one
	 * will always fail.
	 * 
	 * We have to open/close the file manually.
	 */
	@Test @Order(2)
	void fileIsNotOpen_returnsFalse() {
		boolean isOpen = checker.isFileOpen(auditWorkbookPath);
		assertFalse(isOpen);		
	}
	
	@Test @Order(3)
	void fileIsOpen_returnsTrue() {		
		boolean isOpen = checker.isFileOpen(auditWorkbookPath);
		assertTrue(isOpen);
	}
}
