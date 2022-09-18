package com.sebrown.app.unit.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sebrown.app.utils.ExistingAsset;

@SpringBootTest
class ExistingAssetTests {

	@Test
	void assetNotInList_shouldReturnFalse() {
		assertFalse(ExistingAsset.isExistingAssetId("A1;A2", "A3"));
	}
	
	@Test
	void assetInList_shouldReturnTrue() {
		assertTrue(ExistingAsset.isExistingAssetId("A1;A2", "A2"));
	}

}
