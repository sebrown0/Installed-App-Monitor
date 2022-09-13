/**
 * 
 */
package com.sebrown.app.config;

import java.util.Map;

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
	AppProperties, MappingProperties,	WorkbookProperties {
	
	private Map<String, String> mappings;
	private Map<String, Workbook> workbooks;

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
		
}
