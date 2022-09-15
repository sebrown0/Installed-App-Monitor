/**
 * 
 */
package com.sebrown.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.sebrown.app.utils.OberverContinue;

/**
 * @author SteveBrown
 *
 * Sanitise a given Vendor name.
 * Remove unwanted punctuation and words.
 * Only include whole words up to MAX_SIZE.
 * 
 */
@Service
public class VendorNameByParts implements VendorNameSanitiser, OberverContinue {
		
	/*
	 * TODO
	 * Pass MAX_SIZE & INVALID_ITEMS
	 * 
	 * Create 'name getter/creator that will take the result of this
	 * and apply rules to get final name, i.e. if null, what? or if 1 char?.
	 * 
	 * Update the file, DAO.
	 * 
	 */
	private static final int MAX_SIZE = 22;
	
	private static final List<String> INVALID_ITEMS = Arrays.asList(
			".", ",", "Ltd", "LLC", "Corporate", "Inc", "Incorporated");
	
	private VendorNameChecker nameChecker;
	private String name;
	
	private boolean continueLookup;
	
	private void init() {
		nameChecker = new VendorNameChecker(INVALID_ITEMS, this);
		continueLookup = true;
		name = "";
	}
	
	@Override
	public String generateName(String fromString) {
		init();
		
		if(Objects.nonNull(fromString)) {
			String[] parts = fromString.split(" ");
			
			for(String word: parts) {
				name += nameChecker.checkWord(word);
				if(false == continueLookup) { break;	}
			}
			
		}		
		return getWholeWordsUnderMaxSize(name).trim();
	}

	private String getWholeWordsUnderMaxSize(String name) {
		if(Objects.nonNull(name) && name.length() >= 1) {
			
			String[] parts = name.split(" ");		
			String temp = "";
			
			for(String w: parts) {
				if(temp.length() + w.length() > MAX_SIZE) {
					break;
				}else {
					temp += w + " ";
				}
			}
			return temp;
		}
		return name;
	}
	
	@Override
	public void setContinue(boolean cntinue) {
		this.continueLookup = cntinue;		
	}

}
