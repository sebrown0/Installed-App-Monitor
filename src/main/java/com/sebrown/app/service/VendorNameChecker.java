/**
 * 
 */
package com.sebrown.app.service;

import java.util.List;

import com.sebrown.app.utils.OberverContinue;

/**
 * @author SteveBrown
 *
 */
public class VendorNameChecker {
		
	private boolean firstWord = true;
	private String result;
	private String currWord;
	
	private final List<String> invalidItems;	
	private final OberverContinue observer;
	
	public VendorNameChecker(List<String> invalidItems, OberverContinue observer) {		
		this.invalidItems = invalidItems;
		this.observer = observer;
	}

	public String checkWord(String word) {
		currWord = word;
		result = currWord + " ";
		
		for(String item: invalidItems) {
			if(wordContainsInvalidItem(item)) {				
				int getUpTo = currWord.indexOf(item);

				if(firstWord) {				
					resultIsEverythingUptoInvalid(getUpTo);
				}else {					
					checkSecondaryWords(item, getUpTo);
				}				
				break;
			}
			firstWord = false;					
		}
		return result;
	}
	
	private boolean wordContainsInvalidItem(String item) {
		return currWord.toLowerCase().contains(item.toLowerCase());
	}
		
	private void resultIsEverythingUptoInvalid(int getUpTo) {
		result = currWord.substring(0, getUpTo);
		notifyStop();
	}
	
	private void notifyStop() {
		observer.setContinue(false);
	}

	private void checkSecondaryWords(String item, int getUpTo) {
		int wordLen = currWord.length();
		if(isValidWordWithInvalidEnding(wordLen, item, getUpTo)) {
			resultIsEverythingUptoInvalid(getUpTo);		
		}else {
			wordInvalidResultEmpty();
		}	
	}
	
	private boolean isValidWordWithInvalidEnding(int wordLen, String item, int getUpTo) {
		return (getUpTo > 3 && getUpTo + 1 == wordLen && item.length() == 1);
	}
	
	private void wordInvalidResultEmpty() {
		result = "";
		notifyStop();
	}
	
}
