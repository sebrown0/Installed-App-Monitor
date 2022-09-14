/**
 * 
 */
package com.sebrown.app.service;

import java.util.List;
import java.util.Optional;

import com.sebrown.app.utils.OberverContinue;

/**
 * @author SteveBrown
 *
 */
public class VendorNameChecker {

	private static final int MAX_SIZE = 22;
	
	private boolean firstWord = true;
	
	private final List<String> invalidItems;	
	private final OberverContinue observer;
	
	public VendorNameChecker(List<String> invalidItems, OberverContinue observer) {		
		this.invalidItems = invalidItems;
		this.observer = observer;
	}

	public String checkWord(String word) {
		String result = word + " ";
		
		for(String item: invalidItems) {
			if(word.toLowerCase().contains(item.toLowerCase())) {
				
				var getUpTo = word.indexOf(item);// + 1;
				if(firstWord) {				
					result = word.substring(0, getUpTo);
					observer.setContinue(false);
				}else {					
					var wordLen = word.length();
					if(getUpTo > 3 && getUpTo + 1 == wordLen && item.length() == 1) {
						//keep the word & remove the invalid punctuation.
						result = word.substring(0, getUpTo);						
					}else {
						result = "";
						observer.setContinue(false);
					}			

				}				
				break;
			}
			firstWord = false;		
			
		}
		return result;
	}
//private String checkWord(String word) {
//for(String item: invalidItems) {
//	if(word.toLowerCase().contains(item.toLowerCase())) {
//		continueLookup = false;						
//		var getUpTo = word.indexOf(item);// + 1;
//		if(firstWord) {
//			return word.substring(0, getUpTo);
//		}else {					
//			if(getUpTo + 1 == word.length() && item.length() == 1) {
//				//keep the word & remove the invalid punctuation.
//				return word.substring(0, getUpTo);
//			}
//			return "";	
//		}				
//	}
//	firstWord = false;		
//}
//return word + " ";
//}
	
}
