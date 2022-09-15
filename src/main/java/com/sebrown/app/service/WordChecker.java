/**
 * 
 */
package com.sebrown.app.service;

import com.sebrown.app.utils.OberverContinue;

/**
 * @author SteveBrown
 *
 * Check 'word' against criteria.
 * The criteria is the implementation.
 */
public interface WordChecker {

	WordChecker setObserver(OberverContinue observer);
	String checkWord(String word);
	
}
