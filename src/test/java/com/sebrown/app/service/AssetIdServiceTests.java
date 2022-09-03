package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssetIdServiceTests {

	@Value("${testPath}")
	private String testPath;
	
	@Value("${testWorkbook}")
	private String testWorkbook;
	
	@Autowired
	AssetIdService idServ;

	@Test
	void getInstance() {
		assertNotNull(idServ);
	}

	@Test
	void getIdWithNullWb_shouldReturnNoIdValue() {
		String id = idServ.getAssetId(null);		
		assertTrue(id.startsWith("NO-ID-"));
	}
	
	@Test
	void getAssetId() throws IOException {
		String id = idServ.getAssetId(Path.of(testPath+testWorkbook));		
		assertEquals("Test-BOARDROOM-4TH", id);
	}
	
}
