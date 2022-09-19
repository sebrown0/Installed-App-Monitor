package com.sebrown.app.unit.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sebrown.app.utils.CellValueGetter;
import com.sebrown.app.utils.IntegerType;

@SpringBootTest
@ActiveProfiles("test")
class CellDataTests {

	@Test
	void testValidNumericCell() {
		Cell cell = new SXSSFCell(null, CellType.NUMERIC);
		cell.setCellValue(20170615);
		
		CellValueGetter valGetter = 
				new CellValueGetter(cell, new IntegerType());
		
		assertTrue(valGetter.getValue() instanceof Integer);
		
		Integer val = valGetter.getValue();
		assertEquals(20170615, val);
	}
	
	@Test
	void unsupportedValue_returnsDefault() {
		Cell cell = new SXSSFCell(null, CellType.BOOLEAN);
		cell.setCellValue(true);
		
		CellValueGetter valGetter = 
				new CellValueGetter(cell, new IntegerType());

		assertEquals("", valGetter.getValue());
	}

	@Test
	void testStringCell() {
		Cell cell = new SXSSFCell(null, CellType.STRING);
		cell.setCellValue("A String");
		
		CellValueGetter valGetter = 
				new CellValueGetter(cell, new IntegerType());
		
		assertTrue(valGetter.getValue() instanceof String);
		assertEquals("A String", valGetter.getValue());
	}
}
