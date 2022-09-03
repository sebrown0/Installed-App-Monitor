package com.sebrown.app.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidCellTests {

	private static final String WB_TEMP_PATH = "./src/test/resources/temp.xlsx";
	private static Workbook wb;
	private static Sheet ws;
	
	@BeforeAll
	public static void setup() throws EncryptedDocumentException, IOException {
		wb = WorkbookFactory.create(new File(WB_TEMP_PATH));
		ws = wb.createSheet("temp");
	}
	
	@Test
	void validStrCell() {
		Cell cell = ws.createRow(0).createCell(0);
		cell.setCellValue("");
		assertTrue(ValidCell.isValidStrCell(cell));
	}
	
	@Test
	void validNumCell() {
		Cell cell = ws.createRow(1).createCell(0);
		cell.setCellValue(2);
		assertTrue(ValidCell.isValidNumCell(cell));
	}
	
	@Test
	void validBolCell() {
		Cell cell = ws.createRow(1).createCell(0);
		cell.setCellValue(true);
		assertTrue(ValidCell.isValidBooleanCell(cell));
	}

	@AfterAll
	public static void cleanUp() throws IOException {
		if(Objects.nonNull(wb)) { wb.close(); }
	}
}
