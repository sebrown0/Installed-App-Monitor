/**
 * 
 */
package com.sebrown.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sebrown.app.dao.VendorRepo;
import com.sebrown.app.model.ExistingVendorAccronym;

/**
 * @author SteveBrown
 *
 */
//@Service
public class VendorAccronymService {

	@Autowired
	private ExistingVendorAccronym existingAcc;
	
	private List<String> accsInRepo;
	private String forName;
	private String accronymForName;
	
	private final VendorRepo repo;
		
	
	public VendorAccronymService(VendorRepo repo) {	
		this.repo = repo;	
	}
	
	public Optional<String> getAccronym(String forName) {
		accsInRepo = repo.getList();
		this.forName = forName;
		
		
		
		
		return Optional.ofNullable(accronymForName);
	}
	
//	private boolean isExisting() {
//		for(String line : accsInRepo) {
//			String[] pair = line.split(":");
//			if(Objects.nonNull(pair) && pair.length == 2) {
//				String key = 	pair[0];
//				if(key.equals(forName)) {
//					accronymForName = pair[1];
//					System.out.println("=============================");
//					System.out.println("GOT IT>>>>>>>>>>>>>>>>" + accronymForName);
//					System.out.println("=============================");
//					return true;
//				}
//			}			
//		}
//		return false;
//	}
	
}
