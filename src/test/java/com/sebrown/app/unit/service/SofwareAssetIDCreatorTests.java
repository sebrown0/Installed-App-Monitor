/**
 * 
 */
package com.sebrown.app.unit.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.service.SofwareAssetIDCreator;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class SofwareAssetIDCreatorTests {

	@Autowired
	private SofwareAssetIDCreator idCreator;
	
	@Test
	void test() {
		assertNotNull(idCreator);
	}

}
