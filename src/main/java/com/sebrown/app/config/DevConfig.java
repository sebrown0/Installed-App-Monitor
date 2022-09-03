/**
 * 
 */
package com.sebrown.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author SteveBrown
 *
 */

@Configuration
@ConfigurationProperties(prefix = "props")
public class DevConfig implements DevConfigProperties {
	
	private final ResourcePropGetter resourceProps;

	public DevConfig(ResourcePropGetter resourceProps) {
		this.resourceProps = resourceProps;
	}

	@Override
	public ResourcePropGetter getResourcePropGetter() {
		return resourceProps;
	}
	
	
}
