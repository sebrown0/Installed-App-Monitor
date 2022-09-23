/**
 * 
 */
package com.sebrown.app.model;

import java.util.List;
import java.util.Objects;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author SteveBrown
 *
 * Check if an acronym exists in list.
 */
@Component
public class ExistingAcronymChecker {
	
	private List<String> acrInRepo;
	private String acronym;
	
	private void init(List<String> acrInRepo, String acronym) throws IllegalArgumentException {
		if(Objects.nonNull(acrInRepo) && Objects.nonNull(acronym)) {
			this.acrInRepo = acrInRepo;
			this.acronym = acronym;		
		}else {
			throw new IllegalArgumentException(
					String.format(
							"Neither acronym:[%s] or list of existing: [%s] can be null", 
							acronym,  acrInRepo));
		}		
	}
	
	public boolean checkAcronym(
		List<String> acrInRepo, String acronym) {
			
		init(acrInRepo, acronym);
		
//			return acrInRepo.stream()
//				.map(ln -> ln.split(":"))
//				.filter(parts -> parts.length == 2)
//				.filter(parts -> {
//					var key = parts[0];
//					return key.equals(forName);
//				})
//				.map(parts -> Optional.ofNullable(parts[1]))
//				.findFirst()
//				.orElseGet(Optional::empty);
			return false;
		}
	
	private boolean isExisting() {
		return false;
	}
	
}
