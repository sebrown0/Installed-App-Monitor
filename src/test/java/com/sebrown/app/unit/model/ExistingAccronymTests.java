/**
 * 
 */
package com.sebrown.app.unit.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.model.ExistingAcronymChecker;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class ExistingAccronymTests {

	@Autowired 
	private ExistingAcronymChecker acrChecker;
	
	private static List<String> acronyms;
	
	@BeforeAll
	public static void setup() {
		
	}
	
	@Test
	void getInstance() {
		assertNotNull(acrChecker);	
	}
	
	@Test
	void getAccronymForMicrosoft_shouldReturn_MS() {
//		assertEquals("MS", eva.getExisting(repo.getList(), "Microsoft").get());
	}

}
