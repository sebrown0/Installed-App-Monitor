/**
 * 
 */
package com.sebrown.app.file;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.UnitTestProps;
import com.sebrown.app.error.ErrorHandler;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class DefaultFileCopierTests {

	@Autowired
	private UnitTestProps utProps;
	
	@Autowired 
	private FilePathConstructor pathCnstr;
	
	@Autowired 
	private DefaultFileCopier fCopier;
	
	@Test
	void getInstance() {
		assertNotNull(utProps);
	}

	@Test
	void copyFile_fromInvalidPath_returnsErrorHandler() {
		Optional<ErrorHandler> err = fCopier.copyFile("x/y.txt");
		err.ifPresent(e -> e.handleError(true));
		assertTrue(err.isPresent());		
	}
	
	@Test
	void copyFile_fromValidPath_returnsEmptyError() {
		String loc = utProps.getResourcePath();
		String fName = utProps.getWbInName();
		String path = loc + "/" + fName;		

		Optional<ErrorHandler> err = fCopier.copyFile(path);
		assertTrue(err.isEmpty());		
	}
	
	@Test
	void copyFile_fromValidPath_checkFileExists() throws IOException {
		String loc = utProps.getResourcePath();
		String fName = utProps.getWbInName();
		String path = loc + "/" + fName;
		
		removeFile(path);
		fCopier.copyFile(path);
		assertTrue(Files.exists(Path.of(path)));
		removeFile(path);
	}
	
	private void removeFile(String path) throws IOException {
		FileUtils.forceDelete(new File(pathCnstr.getDefaultCopyPath(path)));
	}
	
}
