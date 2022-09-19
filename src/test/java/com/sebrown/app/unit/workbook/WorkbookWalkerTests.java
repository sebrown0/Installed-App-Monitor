package com.sebrown.app.unit.workbook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.config.UTConfig;
import com.sebrown.app.utils.FilePath;
import com.sebrown.app.workbook.WorkbookWalker;

@SpringBootTest
@ActiveProfiles("test")
class WorkbookWalkerTests {
	
	@Autowired
	private UTConfig config;
	
	@Test
	void test() {    
		WorkbookWalker walker = new WorkbookWalker();
		List<Path> paths = 
			walker.getPathsOfWorkbooks(
					"ISO-Audit", 
					FilePath.getFullPathFromApp(config.getResourcePath()));
		
		assertEquals(
				"ISO-Audit-Test-26-08-22_08-47-37.xlsm", 
				paths.get(0).getFileName().toString());
	}

}
