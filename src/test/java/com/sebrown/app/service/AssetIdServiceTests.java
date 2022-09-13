package com.sebrown.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.config.UTConfig;

@SpringBootTest
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
		var utProps = testProps.getUnitProps(); 
		String id = idServ.getAssetId(
				Path.of(
						utProps.get("wbpath") + 
						utProps.get("wbname") ));	
		assertEquals("Test-BOARDROOM-4TH", id);
	}
	
}
