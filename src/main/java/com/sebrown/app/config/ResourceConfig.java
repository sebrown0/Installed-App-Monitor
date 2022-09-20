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
@ConfigurationProperties(prefix = "resource")
public class ResourceConfig implements ResourcePath {
	
	private String path;
	
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return path;
	}
	
}
