/**
 * 
 */
package com.sebrown.app.unit.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.model.AcronymList;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class AcronymListTests {

	@Autowired
	private AcronymList acronymList;
	
	@Test
	void getInstance() {
		assertNotNull(acronymList);
	}

}
