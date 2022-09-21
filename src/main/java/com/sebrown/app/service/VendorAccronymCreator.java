/**
 * 
 */
package com.sebrown.app.service;

import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebrown.app.config.VendorAccronym;

/**
 * @author SteveBrown
 *
 */
@Service
public class VendorAccronymCreator {

	@Autowired
	private VendorAccronym venAcc;
	
	private int minLen, maxLen;
	private String accronym;
	private String[] parts;

	public boolean initOk(String forName) {		
		if(Objects.nonNull(forName) && Objects.nonNull(venAcc)) {			
			parts = forName.split(" ");
			minLen = venAcc.getMinAcnymLen();
			maxLen = venAcc.getMaxAcnymLen();
			
			return Objects.nonNull(parts);
		}
		return false;		
	}
	
	public String getAccronym(String forName) {
		
		if(initOk(forName)) {
			if(ruleOneFails(forName)) {
				if(ruleTwoFails(forName)) {
					getRandomAcc();
				}				
			}	
		}		
		return accronym;
	}
		
	/*
	 * If the name has the correct number of
	 * words get the first char of words
	 * between min and max.
	 */
	private boolean ruleOneFails(String forName) {
		LoopControl control = idx -> { 
			return (idx < parts.length && idx < maxLen); };
			
		return applyRule(control, parts);
	}

	/*
	 * The first word has min or more chars.
	 * Get the chars in sequence up to max.
	 */
	private boolean ruleTwoFails(String forName) {
		String[] firstWordParts = parts[0].split("");
		LoopControl control = idx -> { 
			return (idx < firstWordParts.length && idx < maxLen); };
		
		return applyRule(control, firstWordParts);
	}
	
	private boolean applyRule(LoopControl control, String[] arr) {
		accronym = "";
		if(arr.length >= minLen) {
			setAccronym(control, arr);			
			return false;
		}
		return true;
	}
	
	private void setAccronym(LoopControl control, String[] arr) {
		for(var idx = 0; control.apply(idx); idx++) {
			accronym += arr[idx].substring(0, 1).toUpperCase();
		}
	}
	
	private void getRandomAcc() {
		accronym = RandomStringUtils
				.randomAlphabetic(maxLen)
				.toUpperCase();
	}
		
	@FunctionalInterface
	private interface LoopControl {
		boolean apply(int idx);
	}
	
}
