/**
 * 
 */
package com.sebrown.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author SteveBrown
 *
 */
@Service
public class ResourceService {

	@Value("${resource.path}")
	String resourcePath;
	
	public String getPath() {
		return resourcePath;
	}
}
