package com.sebrown.app.unit.file;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.file.FileRenamer;

@SpringBootTest
@ActiveProfiles("test")
class FileRenamerTests {

	private static final String testFile = 
			"./src/test/resources/RenameMe.txt";
	
	private static final String expectedFile = 
			"./src/test/resources/xRenameMe.txt";
	
	@Autowired
	private FileRenamer fileRenamer;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		if(false == Files.exists(Path.of(testFile))) {
			new FileOutputStream(new File(testFile)).close();
		}
	}

	@Test
	void test() {
		fileRenamer.prependCharAndRename('x', testFile);
		assertTrue(Files.exists(Path.of(expectedFile)));
		assertFalse(Files.exists(Path.of(testFile)));
	}

	@AfterAll
	static void cleanUp() throws Exception {
		FileUtils.forceDelete(new File(expectedFile));
	}
	
}
