/**
 * 
 */
package com.sebrown.app.unit.workbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.UnitTestProps;
import com.sebrown.app.service.WorkbookReader;

/**
 * @author SteveBrown
 *
 */
@SpringBootTest
class WorkbookReaderTests {

	@Autowired
	private UnitTestProps props;
		
	@Test
	void getValidWorkbook() {
		try(WorkbookReader wbReader = new WorkbookReader(props.getWbInFullPath());) {
			var wb = wbReader.getWorkbook();
			assertNotNull(wb);	
		} catch (Exception e) {	
			fail("Error");
		}
	}

	@Test
	void getValidWorksheet() {
		try(WorkbookReader wbReader = new WorkbookReader(props.getWbInFullPath());) {			
			var sht = wbReader.getWorksheet("System Info");
			assertEquals("System Info", sht.getSheetName());	
		} catch (Exception e) {	
			fail("Error");
		}
	}
	
	@Test
	void getValidRow() {
		try(WorkbookReader wbReader = new WorkbookReader(props.getWbInFullPath());) {			
			var row = wbReader.getRow("System Info", 1);
			assertEquals(1, row.getRowNum());	
		} catch (Exception e) {	
			fail("Error");
		}
	}
	
	@Test
	void getInvalidRow_becauseInvalidSheet() {
		try(WorkbookReader wbReader = new WorkbookReader(props.getWbInFullPath());) {			
			var row = wbReader.getRow("xSystem Info", 1);
			assertNull(row);	
		} catch (Exception e) {	
			fail("Error");
		}
	}
	
	@Test
	void getInvalidRow_becauseInvalidRowNum() {
		try(WorkbookReader wbReader = new WorkbookReader(props.getWbInFullPath());) {			
			var row = wbReader.getRow("System Info", -1);
			assertNull(row);	
		} catch (Exception e) {	
			fail("Error");
		}
	}
	
	@Test
	void getValidCell() {
		try(WorkbookReader wbReader = new WorkbookReader(props.getWbInFullPath());) {			
			var cell = wbReader.getCell("System Info", 1, 1);
			assertEquals("Microsoft Windows 8.1", cell.getStringCellValue());	
		} catch (Exception e) {	
			fail("Error");
		}
	}
	
	@Test
	void getInvalidCell_becauseOfInvalidRowNum() {
		try(WorkbookReader wbReader = new WorkbookReader(props.getWbInFullPath());) {			
			var cell = wbReader.getCell("System Info", -1, 1);
			assertNull(cell);	
		} catch (Exception e) {	
			fail("Error");
		}
	}
	
	@Test
	void getInvalidCell_becauseOfInvalidCellNum() {
		try(WorkbookReader wbReader = new WorkbookReader(props.getWbInFullPath());) {			
			var cell = wbReader.getCell("System Info", 1, 9999);
			assertNull(cell);	
		} catch (Exception e) {
			fail("Error");
		}
	}
	
	@AfterAll
	public static void end() {
		System.out.println("FINISHED");
	}

}
