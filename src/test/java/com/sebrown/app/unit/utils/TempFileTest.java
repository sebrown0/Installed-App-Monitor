/**
 * 
 */
package com.sebrown.app.unit.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.config.UTConfig;
import com.sebrown.app.utils.TempFile;

/**
 * @author SteveBrown
 *
 */
@UnitTest
@TestMethodOrder(OrderAnnotation.class)
class TempFileTest {
			
	private static String FILE_PATH;
	private static String TEMP_FILE = "temp.xlsx";
	private static TempFile tempFile;
	
	@BeforeAll
	public static void setFilePath(
		@Autowired TempFile tf,
		@Autowired UTConfig config) throws IOException {
		
		FILE_PATH = 
				config.getResourcePath() + "/" + TEMP_FILE;
				
		tempFile = tf;
		tempFile.setPath(FILE_PATH);
		tempFile.deleteTempFile();
	}
		
	@Test @Order(1)
	void createTempFile() {				
		tempFile.createFile();
		
		var tempCreated = Files.exists(
				Paths.get(tempFile.getTempFilePath()));
		
		assertTrue(tempCreated);
	}

	@Test @Order(2)	
	void restoreOriginalFile() {
		tempFile.restoreOriginal();
		
		var tempExists = Files.exists(
				Paths.get(tempFile.getTempFilePath()));
		
		assertFalse(tempExists);
	}
	
}
