package com.sebrown.app.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.config.UTConfig;
import com.sebrown.app.service.AssetIdService;

@UnitTest
class AssetIdServiceTests {
	
	@Autowired
	UTConfig testProps;
	
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
	 
		String id = idServ.getAssetId(
				Path.of(
						testProps.getResourcePath() + "/" +
						testProps.getWbInName() ));	
		assertEquals("BOARDROOM-4TH;", id);
	}
	
}
