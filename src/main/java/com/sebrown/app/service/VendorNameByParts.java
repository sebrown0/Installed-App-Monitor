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
 */
@Service
public class VendorNameByParts implements VendorNameCreator, OberverContinue {
		
	private static final List<String> invalidItems = Arrays.asList(
			".", ",", "Ltd", "LLC", "Corporate", "Inc", "Incorporated");
	
	private VendorNameChecker nameChecker;
	private String name;
	
	private boolean continueLookup;
	
	private void init() {
		nameChecker = new VendorNameChecker(invalidItems, this);
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
		
		return name.trim();
	}

	@Override
	public void setContinue(boolean cntinue) {
		this.continueLookup = cntinue;		
	}


//	private String checkWord(String word) {
//		for(String item: invalidItems) {
//			if(word.toLowerCase().contains(item.toLowerCase())) {
//				continueLookup = false;						
//				var getUpTo = word.indexOf(item);// + 1;
//				if(firstWord) {
//					return word.substring(0, getUpTo);
//				}else {					
//					if(getUpTo + 1 == word.length() && item.length() == 1) {
//						//keep the word & remove the invalid punctuation.
//						return word.substring(0, getUpTo);
//					}
//					return "";	
//				}				
//			}
//			firstWord = false;		
//		}
//		return word + " ";
//	}
}
