/**
 * 
 */
package com.sebrown.app.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebrown.app.config.DefaultVendor;
import com.sebrown.app.utils.WorksheetName;

/**
 * @author SteveBrown
 *
 */
@Service
public class SimpleVendorName implements VendorNameRules {

	private String sanitisedName;
	
	@Autowired
	private WorksheetName wsName;
		
	private final DefaultVendor defaultVendor;
	private final VendorNameSanitiser sanitiser;

	public SimpleVendorName(
		VendorNameSanitiser sanitiser, 
		DefaultVendor defaultVendor) {
		
		this.sanitiser = sanitiser;
		this.defaultVendor = defaultVendor;
	}

	@Override
	public String applyRulesTo(String originalName) {
		if(originalNameInvalid(originalName)) {
			return defaultVendor.getName();
		}
		
		sanitisedName = sanitiser.getName(originalName);
		
		checkNull(originalName);
		checkTooShort(originalName);
		checkTooLong(originalName);
		
		return sanitisedName.trim();
	}
	
	private boolean originalNameInvalid(String originalName) {
		return 
				Objects.isNull(originalName) || 
				originalName.length() == 0 || 
				originalName.equals("");
	}
	
	private void checkNull(String originalName) {
		if(Objects.isNull(sanitisedName) || sanitisedName.equals("")) {
			sanitisedName = originalName;
		}
	}
	
	private void checkTooShort(String originalName) {
		if(sanitisedName.length() < wsName.getMinLen()) {
			getOnlyChars( 
					wsName.getSentenceWithWholeWords(originalName));
		}
	}
	
	private void checkTooLong(String originalName) {
		if(sanitisedName.length() > wsName.getMaxLen()) {
			getOnlyChars( 
					wsName.getSentenceWithWholeWords(originalName));
		}
	}
	
	private void getOnlyChars(String fromStr) {
		sanitisedName = fromStr.replaceAll("[^a-zA-Z ]", "");		
	}

}
