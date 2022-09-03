package com.sebrown.app.file;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class FileRenamerTests {

	private static final String testFile = 
			"./src/test/resources/RenameMe.txt";
	
	private static final String expectedFile = 
			"./src/test/resources/xRenameMe.txt";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		if(false == Files.exists(Path.of(testFile))) {
			new FileOutputStream(new File(testFile)).close();
		}
	}

	@Test
	void test() {
		FileRenamer.prependCharAndRename('x', testFile);
		assertTrue(Files.exists(Path.of(expectedFile)));
		assertFalse(Files.exists(Path.of(testFile)));
	}

	@AfterAll
	static void cleanUp() throws Exception {
		FileUtils.forceDelete(new File(expectedFile));
	}
	
}
