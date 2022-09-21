/**
 * 
 */
package com.sebrown.app.model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sebrown.app.dao.VendorRepo;

/**
 * @author SteveBrown
 *
 */
@Component
public class ExistingVendorAccronym {

	private String accronymForName;
	
	public Optional<String> getExisting(List<String> accsInRepo, String forName) {
		
		
		if(Objects.nonNull(accsInRepo)) {
			for(String line : accsInRepo) {
				String[] pair = line.split(":");
				if(Objects.nonNull(pair) && pair.length == 2) {
					String key = 	pair[0];
					if(key.equals(forName)) {
						accronymForName = pair[1];
						System.out.println("=============================");
						System.out.println("GOT IT>>>>>>>>>>>>>>>>" + accronymForName);
						System.out.println("=============================");
						return Optional.ofNullable(pair[1]);
					}
				}			
			}	
		}
		
		return Optional.empty();
	}
}
