package com.sebrown.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.sebrown.app.config.TestConfigProperties;


@SpringBootTest
@Profile("TEST")
class TestConfigTests {

	@Autowired
	TestConfigProperties testProps;
	
	@Test
	void getInstance_ofTestProps() {
		assertNotNull(testProps);
	}
		
	@Test
	void getUtWorkbookPath_shouldNotBeNull() {		
		assertNotNull(testProps.getUnitProps().get("wbpath"));
	}
	
	@Test
	void getIntegrationResourcePath_shouldNotBeNull() {		
		assertNotNull(testProps.getIntegrationProps().get("resource"));
	}
	
}
