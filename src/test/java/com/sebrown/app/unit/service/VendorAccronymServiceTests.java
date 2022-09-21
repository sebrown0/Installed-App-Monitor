/**
 * 
 */
package com.sebrown.app.unit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.annotations.UnitTest;
import com.sebrown.app.service.VendorAccronymService;

/**
 * @author SteveBrown
 *
 */
@UnitTest
class VendorAccronymServiceTests {

	@Autowired
	private VendorAccronymService accServ;
	
	@Test
	void getInstance() {
		assertNotNull(accServ);
	}

	@Test
	void jkjjkjkjkjkjk() {
		accServ.getAccronym("Microsoft");
	}
}
