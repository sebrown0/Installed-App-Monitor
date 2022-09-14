package com.sebrown.app.dto;

import java.util.Arrays;
import java.util.List;

import com.sebrown.app.utils.DateFormatter;

/**
 * 
 * @author SteveBrown
 * 
 * A row in the audit work book.
 */
public final class InstalledAppRowData implements AppRowData {
	
	private final String description;
	private final String identifyingNumber;
	private final String installDate;
	private final String name;
	private final String productID;
	private final String regCompany;
	private final String regOwner;
	private final String vendor;
	private final String version;	

	@Override
	public List<String> validateData() {		
		return Arrays.asList(name, identifyingNumber);
	}
	
	private InstalledAppRowData(Builder builder) {
		this.description = builder.description;
		this.identifyingNumber=builder.identifyingNumber;
		this.installDate = builder.installDate;
		this.name = builder.name;
		this.productID = builder.productID;
		this.regCompany = builder.regCompany;
		this.regOwner = builder.regOwner;
		this.vendor = builder.vendor;
		this.version = builder.version;
	}
	
	public String getDescription() {
		return description;
	}
	public String getIdentifyingNumber() {
		return identifyingNumber;
	}	
	public String getName() {
		return name;
	}
	public String getProductID() {
		return productID;
	}
	public String getRegCompany() {
		return regCompany;
	}
	public String getRegOwner() {
		return regOwner;
	}	public String getVendor() {
		return vendor;
	}
	public String getVersion() {
		return version;
	}
	public String getInstallDate() {
		return installDate;
	}

	public static class Builder {
			
		private String name;
		private String vendor;
		private String version;		
		private String description;
		private String identifyingNumber;		
		private String productID;
		private String regCompany;
		private String regOwner;
		private String installDate;
		
		public InstalledAppRowData getInstance() {
			return new InstalledAppRowData(this);
		}
				
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setVendor(String vendor) {
			this.vendor = vendor;
			return this;
		}
		public <T> Builder setVersion(T version) {
			this.version = String.valueOf(version);
			return this;
		}
		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}
		public Builder setIdentifyingNumber(String identifyingNumber) {
			this.identifyingNumber = identifyingNumber;
			return this;
		}
		public Builder setInstallDate(int installDate) {
			this.installDate = DateFormatter.getDateFromDouble(installDate);
			return this;
		}
		public Builder setProductID(String productID) {
			this.productID = productID;
			return this;
		}
		public Builder setRegCompany(String regCompany) {
			this.regCompany = regCompany;
			return this;
		}
		public Builder setRegOwner(String regOwner) {
			this.regOwner = regOwner;
			return this;
		}				
				
	}

	@Override
	public String toString() {
		return "InstalledAppRowData [description=" + description + ", identifyingNumber=" + identifyingNumber
				+ ", installDate=" + installDate + ", name=" + name + ", productID=" + productID + ", regCompany=" + regCompany
				+ ", regOwner=" + regOwner + ", vendor=" + vendor + ", version=" + version + "]";
	}

}
