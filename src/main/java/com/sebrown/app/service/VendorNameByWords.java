/**
 * 
 */
package com.sebrown.app.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sebrown.app.utils.OberverContinue;

/**
 * @author SteveBrown
 *
 * Get the vendor's name from the given words.
 * 
 * For example, CISCO, INC may become CISCO.
 */
@Service
@Scope("prototype")
public class VendorNameByWords implements WordChecker {
		
	private boolean firstWord = true;
	private String result;
	private String currWord;	
	private OberverContinue observer;
	
	private final InvalidVendorItems invalidItems;	
	
	public VendorNameByWords(InvalidVendorItems invalidItems) {		
		this.invalidItems = invalidItems;
	}

	public String checkWord(String word) {
		currWord = word;
		result = currWord + " ";
		
		for(String item: invalidItems.getItems()) {
			if(wordContainsInvalidItem(item)) {				
				int getUpTo = currWord.indexOf(item);

				if(firstWord) {				
					checkFirstWord(getUpTo);
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
		
	private void checkFirstWord(int getUpTo) {
		if(getUpTo == 0) {
			resultIsEverythingAfterInvalid(getUpTo);
		}else {
			resultIsEverythingUptoInvalid(getUpTo);	
		}		
	}
	
	private void resultIsEverythingAfterInvalid(int getUpTo) {
		result = currWord.substring(getUpTo + 1, currWord.length()) + " ";
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

	@Override
	public WordChecker setObserver(OberverContinue observer) {
		this.observer = observer;
		return this;
	}
	
}
