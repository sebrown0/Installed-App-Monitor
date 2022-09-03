package com.sebrown.app.worksheet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.workbook.WorkbookGetter;

@SpringBootTest
class OpenWsTests {

	@Value("${testPath}")
	private String testPath;

	@Value("${testWorkbook}")
	private String testWorkbook;
	
	@Value("${shtInstalledApps}")
	private String SHT_NAME;
	
	@Autowired
	private WorkbookGetter wbGetter;
	
	@Test
	void getWorkbookInCurrentDir_throwIoError_forInvalidPath() {		
		assertThrows(IOException.class, () -> wbGetter.getWorkbook("/does/not/exist"));
	}
	
	@Test
	void getWorkbookInCurrentDir_shouldNotBeNull() throws IOException {		
		XSSFWorkbook wb = wbGetter.getWorkbook(testPath + testWorkbook);
		
		assertTrue(wb.getNumberOfSheets() > 1);		
	}

}
