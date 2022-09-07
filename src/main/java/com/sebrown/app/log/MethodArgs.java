/**
 * 
 */
package com.sebrown.app.log;

import java.util.Objects;

import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 */
@Component
public class MethodArgs {
	
	public String getArgsAsString(Object[] args) {
		if(Objects.nonNull(args)) {
			StringBuilder sb = new StringBuilder();
			for(Object obj : args) {				
				sb.append(obj.toString() + ", ");							
			}
			String str = sb.toString();
			return str.substring(0, str.length() - 2);	
		}
		return "";
	}
	
}
