/**
 * 
 */
package com.sebrown.app.config;

import java.util.Map;
import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author SteveBrown
 *
 * The config properties from application.yml
 */

@Configuration
@ComponentScan(basePackages = "com.sebrown.app")
@ConfigurationProperties(prefix = "app")
@EnableAspectJAutoProxy
public class AppConfig implements 
	AppProperties, MappingProperties,	WorkbookProperties, DefaultVendor {
	
	private Map<String, String> mappings;
	private Map<String, Workbook> workbooks;
	private String maxSheetNameLen;
	private String minSheetNameLen;
	
	public void setMaxSheetNameLen(String maxSheetNameLen) {
		this.maxSheetNameLen = maxSheetNameLen;
	}
	
	public void setMinSheetNameLen(String minSheetNameLen) {
		this.minSheetNameLen = minSheetNameLen;
	}

	public void setWorkbooks(Map<String, Workbook> workbooks) {
		this.workbooks = workbooks;
	}
	
	@Override
	public Map<String, Workbook> getWorkbooks() {
		return workbooks;
	}
	
	@Override
	public Map<String, String> getMappingProps() {
		return mappings;
	}
	public void setMappings(Map<String, String> mappings) {
		this.mappings = mappings;
	}

	@Override
	public MappingProperties getMappingProperties() {
		return () -> mappings;
	}	
	
	//Workbooks		
	public static class Workbook {
		private String name;
		private String nameStartsWith;
		private Map<String, Sheet> sheets;
		
		public void setName(String name) {
			this.name = name;
		}
		public void setNameStartsWith(String nameStartsWith) {
			this.nameStartsWith = nameStartsWith;
		}
		public void setSheets(Map<String, Sheet> sheets) {
			this.sheets = sheets;
		}
		public String getName() {
			return name;
		}
		public String getNameStartsWith() {
			return nameStartsWith;
		}
		public Map<String, Sheet> getSheets() {
			return sheets;
		}
						
	}
	
	//Sheets
	public static class Sheet {
		private String name;
		private Map<String, String> columnMappings;
		
		public void setName(String name) {
			this.name = name;
		}
		public void setColumnMappings(Map<String, String> columnMappings) {
			this.columnMappings = columnMappings;
		}
		public String getName() {
			return name;
		}
		public Map<String, String> getColumnMappings() {
			return columnMappings;
		}		
	}

	@Override //DefaultVendor
	public String getName() {
		var wb = workbooks.get("auditOut");
		if(Objects.nonNull(wb)) {
			var sht = wb.getSheets().get("vendorNotFound");
			if(Objects.nonNull(sht)) {
				var name = sht.getName();
				if(Objects.nonNull(name)) {
					return name;
				}
			}
		}
		return "Vendor Not Found";
	}

	@Override //AppProperties
	public int getMaxSheetNameLen() {
		return Integer.parseInt(maxSheetNameLen);
	}

	@Override //AppProperties
	public int getMinSheetNameLen() {
		return Integer.parseInt(minSheetNameLen);
	}
		
}
