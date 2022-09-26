/**
 * 
 */
package com.sebrown.app.model;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.sebrown.app.config.VendorConfig;

/**
 * @author SteveBrown
 *
 * Check if an acronym exists in current vendors.
 * If it does...
 *  Start a sequence to append to the acronym, i.e. MS_1
 *  If there is already a sequence increment it.
 *   This goes to max 9. After this it becomes a,b, c etc.
 *   This keeps the length of the sequence to MAX_SEQ_LEN.
 */
@Component @Lazy
public class ExistingAcronymChecker {
	
	private static final int MAX_SEQ_LEN = 2;
	
	private String checkedAcronym;	
	
	private final VendorAcronyms venAcr;	
	private final VendorConfig config;
	
	public ExistingAcronymChecker(VendorAcronyms venAcr, VendorConfig config) {
		this.venAcr = venAcr;
		this.config = config;
	}
	
	public Optional<String> getAcronymForName(String name) {		
		return venAcr.getAcronymForName(name);
	}	
	
	public boolean isExisting(String acronym) {		
		return venAcr.isExisting(acronym);
	}	
	
	public String checkAcronym(String acronym) {
		checkedAcronym = acronym;
		if(isExisting(acronym)) {
			if(isNumbered(acronym)) {
				incrementSequence(acronym);
			}else {
				startSequence(acronym);
			}
		}
		return checkedAcronym;
	}
	
	private boolean isNumbered(String acronym) {
		return acronym.contains("_");
	}
	
	private void startSequence(String acronym) {
		int max = config.getMaxAcronymLen();
		int acrLen = acronym.length();
		int diff = MAX_SEQ_LEN - (max - acrLen);
		boolean reduce = diff > 0;
		
		if(reduce) {
			var acr = acronym.substring(0, max - (diff + 1)) + "_1"; 
			checkedAcronym = this.checkAcronym(acr);
		}else {
			checkedAcronym = acronym + "_1";
		}
		
	}
	
	private void incrementSequence(String acronym) {
		int pos = acronym.indexOf("_");
		int num = getNumFromSequence(acronym, pos);
		
		if(num > 0) {
			constructAcronym(acronym, pos, num);
			if(isExisting(checkedAcronym)) {
				constructAcronym(acronym, pos, num);
				incrementSequence(checkedAcronym);
			}	
		}		
	}
	
	private int getNumFromSequence(String acronym, int pos) {
		int num = -1;
		String seq = acronym.substring(pos + 1, pos + 2);
		try {
			num = Integer.parseInt(seq) + 1;
		} catch (NumberFormatException e) {
			num = getIntForNextCharInSeq(num, seq);
		}
		return num;
	}
	
	private int getIntForNextCharInSeq(int num, String seq) {		
		num = seq.charAt(0);
		if(num < 97 || num > 122) {
			num = -1;
		}else {
			num = (num - 87) + 1;
		}
		return num;
	}
	
	private void constructAcronym(String acronym, int pos, int num) {
		checkedAcronym = acronym.substring(0, pos + 1) + numOrChar(num);
	}
	
	private char numOrChar(int num) {
		if(num <= 9) {
			return (char) (num + 48); // 1 - 9
		}else {
			return (char) (num + 87); // a - ...
		}
	}
}
