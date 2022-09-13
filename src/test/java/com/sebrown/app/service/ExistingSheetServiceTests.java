/**
 * 
 */
package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.UnitTestProps;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class ExistingSheetServiceTests {

	@Autowired
	private UnitTestProps props;
	
	@Autowired
	private ExistingSheetService shtServ;
	
	@Autowired
	private XSSFWorkbookService xssfWbServ;
	
	@Autowired
	private AuditInPathService pathService;
	
	@Test
	void test() {
		assertNotNull(shtServ);
	}
		
	@Test
	void getFirstSheet_fromTestAuditIn_shouldBeSystemInfo() throws IOException {
		//Get the WBs on the resource path that start
		//with the Audit In prefix, i.e., ISO-Audit.
		List<Path> paths = pathService.getPaths(props);
		
		//Should only be one WB on in test resources that starts
		//with the Audit In prefix.
		String fromLoc = paths.get(0).toString();
		
		XSSFWorkbook wb = xssfWbServ.setWorkBookPath(fromLoc).getWorkbook();		
		XSSFSheet sheet = shtServ.getExistingSheets(wb).get(0);
		
		assertEquals("System Info", sheet.getSheetName());
	}
}
