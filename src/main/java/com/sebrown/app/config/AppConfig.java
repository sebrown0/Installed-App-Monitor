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
 */

@Configuration
@ComponentScan(basePackages = "com.sebrown.app")
@ConfigurationProperties(prefix = "app")
@EnableAspectJAutoProxy
public class AppConfig implements 
	AppProperties, MappingProperties, 
	WorkbookProperties, ResourceProperties {
	
	//rESOURCE
	private ResourcePropGetter resourceProps;	

	public void setResource(ResourceProps resourceProps) {
		this.resourceProps = resourceProps;
	}
	
	@Override
	public ResourcePropGetter getProps() {
		return resourceProps;
	}

	public static class ResourceProps implements ResourcePropGetter {
		private String path;

		public void setPath(String path) {
			this.path = path;
		}
		
		@Override
		public String getPath() {
			return path;
		}
	}
	//-------------------------------------
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
