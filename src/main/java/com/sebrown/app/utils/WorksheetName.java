/**
 * 
 */
package com.sebrown.app.utils;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.sebrown.app.config.AppProperties;

/**
 * @author SteveBrown
 *
 */
@Component
public class WorksheetName {
	
	private final AppProperties appProps;
	
	public WorksheetName(AppProperties appProps) {	
		this.appProps = appProps;
	}

	public String getSentenceWithWholeWords(String sentence) {
		if(Objects.nonNull(sentence) && sentence.length() >= 1) {
			
			String[] parts = sentence.split(" ");		
			String temp = "";
			
			for(String w: parts) {
				if(temp.length() + w.length() > appProps.getMaxSheetNameLen()) {
					break;
				}else {
					temp += w + " ";
				}
			}
			return temp;
		}
		return sentence;
	}
	
	public int getMaxLen() {
		return appProps.getMaxSheetNameLen();
	}
	
	public int getMinLen() {
		return appProps.getMinSheetNameLen();
	}
}
