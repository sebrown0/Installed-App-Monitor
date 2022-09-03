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
public class ResourceConfig implements ResourcePropGetter {

	private ResourceProps resourceProps;	

	public void setResource(ResourceProps resourceProps) {
		this.resourceProps = resourceProps;
	}

	@Override
	public String getPath() {
		return resourceProps.getPath();
	}
	
	public static class ResourceProps {
		private String path;

		public void setPath(String path) {
			this.path = path;
		}

		public String getPath() {
			return path;
		}
	}
		
}
