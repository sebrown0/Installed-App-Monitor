package com.sebrown.app.worksheet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.dto.InstalledAppRowData;
import com.sebrown.app.dto.RowData;
import com.sebrown.app.workbook.InstalledAppRowMapper;
import com.sebrown.app.workbook.SheetParser;
import com.sebrown.app.workbook.WorkbookGetter;

@SpringBootTest
class ParseSheetTests {
	
	@Value("${testPath}")
	private String testPath;

	@Value("${testWorkbook}")
	private String testWorkbook;
	
	@Value("${shtInstalledApps}")
	private String shtName;
	
	@Autowired
	private WorkbookGetter wbGetter;
		
	@Test
	void getSheetParser_shouldReturnInstance_forValidWbAndSht() throws IOException {
		SheetParser sp = new SheetParser();
		
		assertNotNull(sp);
	}

	@Test
	void mapRowsToListOfRowData_checkFirstRow() throws IOException {
		XSSFWorkbook wb = wbGetter.getWorkbook(testPath + testWorkbook);
		SheetParser sp = new SheetParser();
		
		List<RowData> rowData = sp
				.parseSheet(wb.getSheet(shtName), new InstalledAppRowMapper())
				.getRowData();
		
		InstalledAppRowData row = (InstalledAppRowData) rowData.get(0);

		assertEquals("Microsoft DCF MUI (English) 2013", row.getName());
		assertEquals("15.0.4569.1506", row.getVersion());
		assertEquals("Microsoft Corporation", row.getVendor());
		assertEquals("15/06/2017", row.getInstallDate());
	}
	
}
