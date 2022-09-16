/**
 * 
 */
package com.sebrown.app.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import com.sebrown.app.utils.OberverContinue;
import com.sebrown.app.utils.WorksheetName;

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
	 * 
	 * Create 'name getter/creator that will take the result of this
	 * and apply rules to get final name, i.e. if null, what? or if 1 char?.
	 * 
	 * Update the file, DAO.
	 * 
	 */
	
	private String name;
	private boolean continueLookup;
		
	private final WorksheetName sentenceOfMaxLen;
		
	public VendorNameByParts(WorksheetName sentenceOfMaxLen) {	
		this.sentenceOfMaxLen = sentenceOfMaxLen;
	}

	private void init() {		
		continueLookup = true;
		name = "";
	}
	
	@Lookup	
	public WordChecker wordChecker() { return null; }
	
	@Override
	public String getName(String fromString) {
		init();
		
		var wordChecker = wordChecker().setObserver(this);
		
		if(Objects.nonNull(fromString)) {
			String[] parts = fromString.split(" ");
			
			for(String word: parts) {
				name += wordChecker.checkWord(word);
				if(false == continueLookup) { break;	}
			}
			
		}		
		return getWholeWordsUnderMaxSize(name).trim();
	}

	private String getWholeWordsUnderMaxSize(String name) {
		return 
			sentenceOfMaxLen.getSentenceWithWholeWords(name);
	}
	
	@Override
	public void setContinue(boolean cntinue) {
		this.continueLookup = cntinue;		
	}

}
