package com.sebrown.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.sebrown.app.config.DevConfigProperties;


@SpringBootTest
@Profile("dev")
class DevConfigTests {

	@Autowired
	DevConfigProperties devProps;
	
	@Test
	void getInstance_ofTestProps() {
		assertNotNull(devProps);
	}
		
	@Test
	void getResourcePath() {
		assertEquals("src/main/resources/", devProps.getResourcePropGetter().getPath());
	}
}
