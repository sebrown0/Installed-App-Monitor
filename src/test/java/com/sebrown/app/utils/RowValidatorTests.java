/**
 * 
 */
package com.sebrown.app.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sebrown.app.dto.InstalledAppRowData;
import com.sebrown.app.dto.AppRowData;

/**
 * @author SteveBrown
 *
 */
class RowValidatorTests {

	@Test
	void validRow() {
		AppRowData rd = new InstalledAppRowData.Builder()
				.setName("name")
				.setIdentifyingNumber("123")
				.getInstance();
		
		assertTrue(RowValidator.isValidRow(rd));
	}
	
	@Test
	void invalidRow_nameHasNoChars() {
		AppRowData rd = new InstalledAppRowData.Builder()
				.setName("")
				.setIdentifyingNumber("123")
				.getInstance();
		
		assertFalse(RowValidator.isValidRow(rd));
	}
	
	@Test
	void invalidRow_nameIsNull() {
		AppRowData rd = new InstalledAppRowData.Builder()
				.setName(null)
				.setIdentifyingNumber("123")
				.getInstance();
		
		assertFalse(RowValidator.isValidRow(rd));
	}
		
	@Test
	void invalidRow_idHasNoChars() {
		AppRowData rd = new InstalledAppRowData.Builder()
				.setName("name")
				.setIdentifyingNumber("")
				.getInstance();
		
		assertFalse(RowValidator.isValidRow(rd));
	}
	
	@Test
	void invalidRow_idIsNull() {
		AppRowData rd = new InstalledAppRowData.Builder()
				.setName("name")
				.setIdentifyingNumber(null)
				.getInstance();
		
		assertFalse(RowValidator.isValidRow(rd));
	}

}
